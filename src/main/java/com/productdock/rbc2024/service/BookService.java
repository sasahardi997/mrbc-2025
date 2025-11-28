package com.productdock.rbc2024.service;

import com.productdock.rbc2024.dto.BookDetailsDto;
import com.productdock.rbc2024.dto.BookDto;
import com.productdock.rbc2024.dto.EditBookDetailsDto;
import com.productdock.rbc2024.exception.BookTitleAlreadyExistsException;
import com.productdock.rbc2024.exception.EntityNotFoundException;
import com.productdock.rbc2024.mapper.BookMapper;
import com.productdock.rbc2024.domain.Book;
import com.productdock.rbc2024.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDetailsDto> getAll() {
        var books = bookRepository.findAll();
        return books
                .stream()
                .map(bookMapper::convertModelToBookDetailsDto)
                .collect(Collectors.toList());
    }

    public BookDto getById(Long id) {
        var book = bookRepository.findById(id);
        return book.map(bookMapper::convertModelToBookDto)
                .orElseThrow(() -> new EntityNotFoundException("Book with id: " + id + " does not exist."));
    }

    public BookDetailsDto getBookDetailsById(Long id) {
        var book = bookRepository.findById(id);
        return book.map(bookMapper::convertModelToBookDetailsDto)
                .orElseThrow(() -> new EntityNotFoundException("Book with id: " + id + " does not exist."));
    }

    public List<BookDetailsDto> searchByTitle(String title) {
        var filteredBooks = bookRepository.findByTitleContainingIgnoreCase(title);
        return filteredBooks.stream()
                .map(bookMapper::convertModelToBookDetailsDto)
                .collect(Collectors.toList());
    }

    public List<BookDetailsDto> filterByNumberOfPages(Integer from, Integer to) {
        var filteredBooks = bookRepository.findByNumberOfPagesBetween(from, to);
        return filteredBooks.stream()
                .map(bookMapper::convertModelToBookDetailsDto)
                .collect(Collectors.toList());
    }

    public Long createBook(BookDetailsDto bookDetailsDto) {
        var title = bookDetailsDto.title();
        if (bookRepository.existsByTitle(title)) {
            throw new BookTitleAlreadyExistsException("Book with title: " + title + " already exists.");
        }
        var book = bookMapper.convertBookDetailsDtoToModel(bookDetailsDto);
        return bookRepository.save(book).getId();
    }

    public void updateBook(Long id, EditBookDetailsDto editBookDetailsDto) {
        var book = bookRepository.findById(id);
        book.map(bookToUpdate -> bookRepository.save(bookMapper.convertEditBookDetailsDtoToModel(editBookDetailsDto, bookToUpdate)))
                .orElseThrow(() -> new EntityNotFoundException("Book with id: " + id + " you are trying to edit does not exist."));
    }

    public void deleteBook(Long id) {
        var book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book with id: " + id + " you are trying to edit does not exist."));
        bookRepository.delete(book);
    }

    public Integer getNumberOfPages(String title) {
        var filteredBooks = bookRepository.findByTitleContainingIgnoreCase(title);
        return filteredBooks.stream()
                .map(Book::getNumberOfPages)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

}
