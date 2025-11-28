package com.productdock.rbc2024.controller;

import com.productdock.rbc2024.dto.BookDetailsDto;
import com.productdock.rbc2024.SpringContextTestBase;
import com.productdock.rbc2024.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.productdock.rbc2024.controller.BookControllerSetUp.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookControllerIT extends SpringContextTestBase {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void getBookInfo() {
        var books = books();
        bookRepository.saveAll(books);

        webClient.get()
            .uri(API_BASE_URL + "/" + BOOK_2_ID)
            .exchange()
            .expectStatus().isOk()
            .expectBody(BookDetailsDto.class)
            .consumeWith(response -> {
                var responseBody = response.getResponseBody();

                assertThat(responseBody).isNotNull();
                var expectedCsvData = expectedBookDetailsDto();
                assertEquals(expectedCsvData, responseBody);
            });
    }

    @Test
    void getBookInfoShouldReturnNotFoundWhenBookDoesNotExist() {
        webClient.get()
            .uri(API_BASE_URL + "/" + NON_EXISTENT_ID)
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    void createBookShouldReturnCreated() {
        var bookDetailsDto = createBookDetailsDto();

        webClient.post()
            .uri(API_BASE_URL)
            .bodyValue(bookDetailsDto)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(Long.class)
            .consumeWith(response -> {
                var bookId = response.getResponseBody();
                assertThat(bookId).isNotNull();

                var savedBook = bookRepository.findById(bookId);
                assertTrue(savedBook.isPresent());
                assertEquals(NEW_BOOK_TITLE, savedBook.get().getTitle());
            });
    }

    @Test
    void deleteBookShouldReturnNoContent() {
        var savedBooks = bookRepository.saveAll(books());
        var book1Id = savedBooks.get(0).getId();

        webClient.delete()
            .uri(API_BASE_URL + "/" + book1Id)
            .exchange()
            .expectStatus().isNoContent();

        var deletedBook = bookRepository.findById(BOOK_1_ID);
        assertFalse(deletedBook.isPresent());
    }

    @Test
    void deleteBookShouldReturnNotFoundWhenBookDoesNotExist() {
        webClient.delete()
            .uri(API_BASE_URL + "/" + NON_EXISTENT_ID)
            .exchange()
            .expectStatus().isNotFound();
    }
}