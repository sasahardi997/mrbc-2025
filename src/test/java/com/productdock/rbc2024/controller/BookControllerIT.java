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
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookControllerIT extends SpringContextTestBase {

    private static final String BASE_URL = "/api/books";

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
                .uri(BASE_URL + "/" + BOOK_2_ID + "/book-details")
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

}
