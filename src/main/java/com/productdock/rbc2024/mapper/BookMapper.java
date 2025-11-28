package com.productdock.rbc2024.mapper;

import com.productdock.rbc2024.dto.BookDetailsDto;
import com.productdock.rbc2024.dto.BookDto;
import com.productdock.rbc2024.dto.CommentDto;
import com.productdock.rbc2024.dto.EditBookDetailsDto;
import com.productdock.rbc2024.model.Book;
import com.productdock.rbc2024.model.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public BookDetailsDto convertModelToBookDetailsDto(Book book) {
        return new BookDetailsDto(
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getNumberOfPages()
        );
    }

    public BookDto convertModelToBookDto(Book book) {
        return new BookDto(
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getNumberOfPages()
        );
    }

    private List<CommentDto> convertCommentToDto(List<Comment> comments) {
        return comments.stream()
                .map(BookMapper::convertCommentToCommentDto)
                .collect(Collectors.toList());
    }

    private static CommentDto convertCommentToCommentDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getContent());
    }

    public Book convertBookDetailsDtoToModel(BookDetailsDto bookDetailsDto) {
        return new Book(bookDetailsDto.id(), bookDetailsDto.title(), bookDetailsDto.author(), bookDetailsDto.numberOfPages());
    }

    public Book convertEditBookDetailsDtoToModel(EditBookDetailsDto dto, Book bookFromDdb){
        bookFromDdb.setTitle(dto.title());
        bookFromDdb.setNumberOfPages(dto.numberOfPages());
        return bookFromDdb;
    }

}
