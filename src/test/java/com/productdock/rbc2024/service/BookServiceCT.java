package com.productdock.rbc2024.service;

import static com.productdock.rbc2024.service.BookServiceSetUp.*;
import static org.junit.jupiter.api.Assertions.*;

import com.productdock.rbc2024.SpringContextTestBase;
import com.productdock.rbc2024.domain.Book;
import com.productdock.rbc2024.dto.BookDetailsDto;
import com.productdock.rbc2024.dto.EditBookDetailsDto;
import com.productdock.rbc2024.exception.BookTitleAlreadyExistsException;
import com.productdock.rbc2024.exception.EntityNotFoundException;
import com.productdock.rbc2024.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class BookServiceCT extends SpringContextTestBase {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void getAllShouldReturnAllBooks() {
        var book1 = Book.builder().title(BOOK_1_TITLE).author(BOOK_1_AUTHOR).numberOfPages(BOOK_1_PAGES).build();
        var book2 = Book.builder().title(BOOK_2_TITLE).author(BOOK_2_AUTHOR).numberOfPages(BOOK_2_PAGES).build();
        bookRepository.saveAll(List.of(book1, book2));

        var result = bookService.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void getByIdShouldReturnBookWhenExists() {
        var book = Book.builder().title(TEST_BOOK_TITLE).author(TEST_AUTHOR).numberOfPages(TEST_PAGES).build();
        var savedBook = bookRepository.save(book);

        var result = bookService.getById(savedBook.getId());

        assertEquals(savedBook.getId(), result.id());
        assertEquals(savedBook.getTitle(), result.title());
        assertNull(result.comments());
    }

    @Test
    void getByIdShouldThrowExceptionWhenNotExists() {
        assertThrows(EntityNotFoundException.class, () -> bookService.getById(NON_EXISTENT_ID));
    }

    @Test
    void createBookShouldSaveBook() {
        var bookDto = new BookDetailsDto(null, NEW_BOOK_TITLE, NEW_AUTHOR, NEW_PAGES);

        var bookId = bookService.createBook(bookDto);

        var savedBook = bookRepository.findById(bookId);
        assertTrue(savedBook.isPresent());
        assertEquals(NEW_BOOK_TITLE, savedBook.get().getTitle());
    }

    @Test
    void createBookShouldThrowExceptionWhenTitleExists() {
        var book = Book.builder().title(EXISTING_BOOK_TITLE).author(BOOK_AUTHOR).numberOfPages(BOOK_1_PAGES).build();
        bookRepository.save(book);

        var bookDto = new BookDetailsDto(null, EXISTING_BOOK_TITLE, ANOTHER_AUTHOR, BOOK_2_PAGES);

        assertThrows(BookTitleAlreadyExistsException.class, () -> bookService.createBook(bookDto));
    }

    @Test
    void updateBookShouldUpdateExistingBook() {
        var book = Book.builder().title(ORIGINAL_TITLE).author(BOOK_AUTHOR).numberOfPages(BOOK_1_PAGES).build();
        var savedBook = bookRepository.save(book);

        var editDto = new EditBookDetailsDto(UPDATED_PAGES, UPDATED_TITLE);
        bookService.updateBook(savedBook.getId(), editDto);

        var updatedBook = bookRepository.findById(savedBook.getId());
        assertTrue(updatedBook.isPresent());
        assertEquals(UPDATED_TITLE, updatedBook.get().getTitle());
        assertEquals(UPDATED_PAGES, updatedBook.get().getNumberOfPages());
        assertEquals(BOOK_AUTHOR, updatedBook.get().getAuthor());
    }

    @Test
    void deleteBookShouldRemoveBook() {
        var book = Book.builder().title(DELETE_BOOK_TITLE).author(BOOK_AUTHOR).numberOfPages(BOOK_1_PAGES).build();
        var savedBook = bookRepository.save(book);

        bookService.deleteBook(savedBook.getId());

        assertFalse(bookRepository.findById(savedBook.getId()).isPresent());
    }

    @Test
    void searchByTitleShouldReturnMatchingBooks() {
        var book1 = Book.builder().title(JAVA_PROGRAMMING_TITLE).author(AUTHOR_1).numberOfPages(BOOK_1_PAGES).build();
        var book2 = Book.builder().title(ADVANCED_JAVA_TITLE).author(AUTHOR_2).numberOfPages(BOOK_2_PAGES).build();
        var book3 = Book.builder().title(PYTHON_BASICS_TITLE).author(AUTHOR_3).numberOfPages(TEST_PAGES).build();
        bookRepository.saveAll(List.of(book1, book2, book3));

        var result = bookService.searchByTitle(SEARCH_TERM);

        assertEquals(EXPECTED_SEARCH_RESULTS, result.size());
        assertTrue(result.stream().allMatch(b -> b.title().toLowerCase().contains(SEARCH_TERM)));
    }

    @Test
    void filterByNumberOfPagesShouldReturnBooksInRange() {
        var book1 = Book.builder().title(SHORT_BOOK_TITLE).author(AUTHOR_1).numberOfPages(SHORT_PAGES).build();
        var book2 = Book.builder().title(MEDIUM_BOOK_TITLE).author(AUTHOR_2).numberOfPages(MEDIUM_PAGES).build();
        var book3 = Book.builder().title(LONG_BOOK_TITLE).author(AUTHOR_3).numberOfPages(LONG_PAGES).build();
        bookRepository.saveAll(List.of(book1, book2, book3));

        var result = bookService.filterByNumberOfPages(FILTER_MIN_PAGES, FILTER_MAX_PAGES);

        assertEquals(EXPECTED_FILTER_RESULTS, result.size());
        assertEquals(MEDIUM_BOOK_TITLE, result.get(0).title());
    }
}