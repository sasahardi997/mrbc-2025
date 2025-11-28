package com.productdock.rbc2024.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.productdock.rbc2024.mapper.BookMapperSetUp.createBook;
import static com.productdock.rbc2024.mapper.BookMapperSetUp.createExpectedBookDetailsDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookMapperShould {

    private BookMapper bookMapper;

    @BeforeEach
    void setUp() {
        bookMapper = new BookMapper();
    }

    @Test
    void convertModelToBookDetailsDto() {
        var book = createBook();

        var bookDetailsDto = bookMapper.convertModelToBookDetailsDto(book);

        var expected = createExpectedBookDetailsDto();
        assertEquals(expected, bookDetailsDto);
    }

}
