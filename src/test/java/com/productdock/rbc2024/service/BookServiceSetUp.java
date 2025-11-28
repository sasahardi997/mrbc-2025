package com.productdock.rbc2024.service;

import com.productdock.rbc2024.dto.BookDto;
import com.productdock.rbc2024.domain.Book;

public class BookServiceSetUp {

    static final long BOOK_ID = 1L;
    private static final String BOOK_TITLE = "title";
    private static final String BOOK_AUTHOR = "author";
    private static final int BOOK_NUMBER_OF_PAGES = 120;

    static Book createBook() {
        return Book.builder()
            .id(BOOK_ID)
            .title(BOOK_TITLE)
            .author(BOOK_AUTHOR)
            .numberOfPages(BOOK_NUMBER_OF_PAGES)
            .build();
    }

    static BookDto createBookDto() {
        return new BookDto(BOOK_ID, BOOK_TITLE ,BOOK_AUTHOR, BOOK_NUMBER_OF_PAGES);
    }

    static BookDto createExpectedBookDto() {
        return new BookDto(BOOK_ID, BOOK_TITLE, BOOK_AUTHOR, BOOK_NUMBER_OF_PAGES);
    }

    static Book createBookWithId() {
        return Book.builder()
            .id(BOOK_ID)
            .build();
    }

    static BookDto createExpectedBookWithIdDto() {
        return new BookDto(BOOK_ID);
    }

}
