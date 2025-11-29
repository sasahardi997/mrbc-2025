package com.productdock.rbc2024.domain.dto;

import java.util.List;

public record BookDto(

    Long id,

    String title,

    String author,

    Integer numberOfPages,

    List<CommentDto> comments

) {

    public BookDto(Long id) {
        this(id, null, null, null, null);
    }

    public BookDto(Long id, String title, String author, Integer numberOfPages) {
        this(id, title, author, numberOfPages, null);
    }
}
