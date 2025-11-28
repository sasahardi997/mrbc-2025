package com.productdock.rbc2024.repository;

import static com.productdock.rbc2024.repository.BookRepositorySetUp.*;
import static org.junit.jupiter.api.Assertions.*;

import com.productdock.rbc2024.SpringContextTestBase;
import com.productdock.rbc2024.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class BookRepositoryCT extends SpringContextTestBase {


    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void existsByTitleShouldReturnTrueWhenBookExists() {
        var book = new Book();
        book.setTitle(THE_GREAT_GATSBY);
        book.setNumberOfPages(180);
        bookRepository.save(book);

        boolean exists = bookRepository.existsByTitle(THE_GREAT_GATSBY);

        assertTrue(exists);
    }

    @Test
    void existsByTitleShouldReturnFalseWhenBookDoesNotExist() {
        boolean exists = bookRepository.existsByTitle(NON_EXISTENT_BOOK);

        assertFalse(exists);
    }

    @Test
    void findByTitleContainingIgnoreCaseShouldReturnBooksWhenTitleMatches() {
        bookRepository.saveAll(List.of(getJavaBook(), getAdvancedJavaBook(), getPythonBook()));

        List<Book> result = bookRepository.findByTitleContainingIgnoreCase("java");

        assertEquals(EXPECTED_NUMBER_OF_BOOKS_WITH_JAVA_IN_TITLE, result.size());
    }

    @Test
    void findByTitleContainingIgnoreCaseShouldReturnEmptyListWhenNoMatch() {
        bookRepository.save(getJavaBook());

        List<Book> result = bookRepository.findByTitleContainingIgnoreCase(NON_EXISTENT_BOOK);

        assertTrue(result.isEmpty());
    }

    @Test
    void findByNumberOfPagesBetweenShouldReturnBooksWhenPagesInRange() {
        bookRepository.saveAll(List.of(getJavaBook(), getAdvancedJavaBook(), getPythonBook()));

        List<Book> result = bookRepository.findByNumberOfPagesBetween(250, 350);

        assertEquals(EXPECTED_NUMBER_OF_BOOKS_WITH_250_TO_350_PAGES, result.size());
        assertEquals(PYTHON_BASICS, result.getFirst().getTitle());
    }

    @Test
    void findByNumberOfPagesBetween_shouldIncludeBoundaries() {
        bookRepository.saveAll(List.of(getJavaBook(), getAdvancedJavaBook(), getPythonBook()));

        List<Book> result = bookRepository.findByNumberOfPagesBetween(
            PYTHON_BASICS_NUMBER_OF_PAGES,
            ADVANCED_JAVA_NUMBER_OF_PAGES);

        assertEquals(EXPECTED_NUMBER_OF_BOOKS_WITH_300_TO_450_PAGES, result.size());
    }

    @Test
    void findByNumberOfPagesBetween_shouldReturnEmptyList_whenNoMatch() {
        bookRepository.saveAll(List.of(getJavaBook(), getAdvancedJavaBook(), getPythonBook()));

        List<Book> result = bookRepository.findByNumberOfPagesBetween(
            UNEXISTING_MIN_NUMBER_OF_PAGES,
            UNEXISTING_MAX_NUMBER_OF_PAGES);

        assertTrue(result.isEmpty());
    }
}
