package com.productdock.rbc2024.mapper;

import com.productdock.rbc2024.SpringContextTestBase;
import com.productdock.rbc2024.domain.dto.EditBookDetailsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.productdock.rbc2024.mapper.BookMapperSetUp.createBook;
import static com.productdock.rbc2024.mapper.BookMapperSetUp.createExpectedBookDetailsDto;
import static org.junit.jupiter.api.Assertions.*;

class BookMapperShould extends SpringContextTestBase {

    @Autowired
    private BookMapper bookMapper;

    @Test
    void convertModelToBookDetailsDto() {
        var book = createBook();

        var bookDetailsDto = bookMapper.convertModelToBookDetailsDto(book);

        var expected = createExpectedBookDetailsDto();
        assertEquals(expected, bookDetailsDto);
    }

    @Test
    void convertModelToBookDto() {
        var book = createBook();

        var bookDto = bookMapper.convertModelToBookDto(book);

        assertEquals(book.getId(), bookDto.id());
        assertEquals(book.getTitle(), bookDto.title());
        assertEquals(book.getAuthor(), bookDto.author());
        assertEquals(book.getNumberOfPages(), bookDto.numberOfPages());
        assertNull(bookDto.comments());
    }

    @Test
    void convertBookDetailsDtoToModel() {
        var bookDetailsDto = createExpectedBookDetailsDto();

        var book = bookMapper.convertBookDetailsDtoToModel(bookDetailsDto);

        assertEquals(bookDetailsDto.id(), book.getId());
        assertEquals(bookDetailsDto.title(), book.getTitle());
        assertEquals(bookDetailsDto.author(), book.getAuthor());
        assertEquals(bookDetailsDto.numberOfPages(), book.getNumberOfPages());
    }

    @Test
    void convertEditBookDetailsDtoToModel() {
        var existingBook = createBook();
        var editDto = new EditBookDetailsDto( 200, "Updated Title");

        var updatedBook = bookMapper.convertEditBookDetailsDtoToModel(editDto, existingBook);

        assertEquals(existingBook.getId(), updatedBook.getId());
        assertEquals(existingBook.getAuthor(), updatedBook.getAuthor());
        assertEquals(editDto.title(), updatedBook.getTitle());
        assertEquals(editDto.numberOfPages(), updatedBook.getNumberOfPages());
    }
}