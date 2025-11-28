package com.productdock.rbc2024.integration.controller.bookcontroller;

import com.productdock.rbc2024.dto.BookDetailsDto;
import com.productdock.rbc2024.model.Book;

import java.util.List;

class BookControllerSetUp {

    private static Long BOOK_1_ID = 1L;
    private static final String BOOK_1_TITLE = "title1";
    private static final String BOOK_1_AUTHOR = "author1";
    private static final int BOOK_1_NUMBER_OF_PAGES = 120;

    static Long BOOK_2_ID = 2L;
    private static final String BOOK_2_TITLE = "title2";
    private static final String BOOK_2_AUTHOR = "author2";
    private static final int BOOK_2_NUMBER_OF_PAGES = 200;

    static List<Book> books() {
        var book1 = book(BOOK_1_ID, BOOK_1_TITLE, BOOK_1_AUTHOR, BOOK_1_NUMBER_OF_PAGES);
        var book2 = book(BOOK_2_ID, BOOK_2_TITLE, BOOK_2_AUTHOR, BOOK_2_NUMBER_OF_PAGES);
        return List.of(book1, book2);
    }

    private static Book book(Long id, String title, String author, Integer numberOfPages) {
        return Book.builder()
                .id(id)
                .title(title)
                .author(author)
                .numberOfPages(numberOfPages)
                .build();
    }

    static BookDetailsDto expectedBookDetailsDto() {
        return new BookDetailsDto(BOOK_2_ID, BOOK_2_TITLE, BOOK_2_AUTHOR, BOOK_2_NUMBER_OF_PAGES);
    }

}
