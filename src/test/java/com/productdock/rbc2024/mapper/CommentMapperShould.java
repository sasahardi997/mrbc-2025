package com.productdock.rbc2024.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.productdock.rbc2024.mapper.CommentMapperSetUp.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentMapperShould {

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private CommentMapper commentMapper;

    @Test
    void convertCommentDtoToModel() {
        var commentDto = commentDto();
        var bookDetailsDto = bookDetailsDto();
        var book = book();
        when(bookMapper.convertBookDetailsDtoToModel(bookDetailsDto)).thenReturn(book);

        var result = commentMapper.convertCommentDtoToModel(commentDto, bookDetailsDto);

        var expectedComment = expectedComment();
        assertEquals(expectedComment, result);
    }

    @Test
    void convertModelToCommentDto() {
        var comment = comment();

        var result = commentMapper.convertModelToCommentDto(comment);

        var expectedCommentDto = expectedCommentDto();
        assertEquals(expectedCommentDto, result);
    }

}
