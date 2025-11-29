package com.productdock.rbc2024.mapper;

import com.productdock.rbc2024.domain.Comment;
import com.productdock.rbc2024.domain.dto.BookDetailsDto;
import com.productdock.rbc2024.domain.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public interface CommentMapper {

    @Mapping(target = "book", source = "bookDto")
    @Mapping(target = "id", source = "commentDto.id")
    @Mapping(target = "content", source = "commentDto.content")
    Comment convertCommentDtoToModel(CommentDto commentDto, BookDetailsDto bookDto);

    CommentDto convertModelToCommentDto(Comment comment);
}
