package com.productdock.rbc2024.service;

import static com.productdock.rbc2024.service.BookServiceSetUp.BOOK_ID;
import static com.productdock.rbc2024.service.BookServiceSetUp.createBookWithId;
import static com.productdock.rbc2024.service.BookServiceSetUp.createExpectedBookWithIdDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.productdock.rbc2024.SpringContextTestBase;
import com.productdock.rbc2024.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class BookServiceCT extends SpringContextTestBase {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void beforeEach() {
        bookRepository.deleteAll();
    }

    @Test
    void getById() {
        var book = createBookWithId();
        bookRepository.save(book);

        var actual = bookService.getById(BOOK_ID);

        var expectedBookDto = createExpectedBookWithIdDto();
        assertEquals(expectedBookDto, actual);
    }

}
