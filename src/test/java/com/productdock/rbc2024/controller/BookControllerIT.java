package com.productdock.rbc2024.controller;

import com.productdock.rbc2024.dto.BookDetailsDto;
import com.productdock.rbc2024.SpringContextTestBase;
import com.productdock.rbc2024.repository.BookRepository;
import com.productdock.rbc2024.repository.CommentRepository;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.productdock.rbc2024.controller.BookControllerSetUp.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

class BookControllerIT extends SpringContextTestBase {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    void getBookInfo() {
        var savedBooks = bookRepository.saveAll(books());
        var book2Id = savedBooks.get(1).getId();

        webClient.get()
            .uri(API_BASE_URL + "/" + book2Id)
            .exchange()
            .expectStatus().isOk()
            .expectBody(BookDetailsDto.class)
            .consumeWith(response -> {
                var responseBody = response.getResponseBody();

                assertThat(responseBody).isNotNull();

                var expectedData = expectedBookDetailsDto();
                assertThat(responseBody)
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(expectedData);
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
    void deleteBookShouldReturnNoContent() {
        var savedBooks = bookRepository.saveAll(books());
        var book1Id = savedBooks.getFirst().getId();

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

    /**
     *  ParameterizedTest marks a test method to run multiple times with different parameters
     */
    @ParameterizedTest
    @MethodSource("bookCreationData")
    @DisplayName("POST /api/books should create books with different data")
    void createBookShouldAcceptVariousInputs(String title, String author, Integer pages) {
        var bookDto = new BookDetailsDto(null, title, author, pages);

        webClient.post()
            .uri(API_BASE_URL)
            .bodyValue(bookDto)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(Long.class)
            .consumeWith(response -> {
                var bookId = response.getResponseBody();
                assertThat(bookId).isNotNull();
            });
    }

    private static Stream<Arguments> bookCreationData() {
        return Stream.of(
            Arguments.of(BOOK_ONE, AUTHOR_ONE, 100),
            Arguments.of(BOOK_TWO, AUTHOR_TWO, 500),
            Arguments.of(BOOK_THREE, AUTHOR_THREE, 1000)
        );
    }
}