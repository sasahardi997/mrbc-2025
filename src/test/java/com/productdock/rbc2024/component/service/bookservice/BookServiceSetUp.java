package com.productdock.rbc2024.component.service.bookservice;

import com.productdock.rbc2024.dto.BookDto;
import com.productdock.rbc2024.model.Book;

class BookServiceSetUp {

    static Long BOOK_ID = 1L;

    static Book createBook() {
        return Book.builder()
                .id(BOOK_ID)
                .build();
    }

    static BookDto createExpectedBookDto() {
        return new BookDto(BOOK_ID);
    }

}
