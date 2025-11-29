package com.productdock.rbc2024.mapper;

import com.productdock.rbc2024.SpringContextTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.productdock.rbc2024.mapper.CommentMapperSetUp.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentMapperCT extends SpringContextTestBase {

    @Autowired
    private CommentMapper commentMapper;

    @Test
    void convertCommentDtoToModel() {
        var commentDto = commentDto();
        var bookDetailsDto = bookDetailsDto();

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
