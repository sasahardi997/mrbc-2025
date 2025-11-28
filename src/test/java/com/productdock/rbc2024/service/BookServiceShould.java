package com.productdock.rbc2024.service;

import com.productdock.rbc2024.exception.EntityNotFoundException;
import com.productdock.rbc2024.mapper.BookMapper;
import com.productdock.rbc2024.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.productdock.rbc2024.service.BookServiceSetUp.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceShould {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    void returnBookById() {
        var book = createBook();
        var bookDto = createBookDto();
        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(book));
        when(bookMapper.convertModelToBookDto(book)).thenReturn(bookDto);

        var result = bookService.getById(BOOK_ID);

        var expectedBookDto = createExpectedBookDto();
        assertEquals(expectedBookDto, result);
    }

    @Test
    void catchEntityNotFoundException() {
        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.empty());

        var exception = assertThrows(EntityNotFoundException.class, () -> {
            bookService.getById(BOOK_ID);
        });

        var expectedMessage = "Book with id: " + BOOK_ID + " does not exist.";
        var actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(bookMapper, never()).convertModelToBookDto(any());
    }

}
