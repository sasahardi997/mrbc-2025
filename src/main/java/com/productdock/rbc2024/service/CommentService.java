package com.productdock.rbc2024.service;

import com.productdock.rbc2024.domain.dto.CommentDto;
import com.productdock.rbc2024.exception.EntityNotFoundException;
import com.productdock.rbc2024.mapper.BookMapper;
import com.productdock.rbc2024.mapper.CommentMapper;
import com.productdock.rbc2024.repository.BookRepository;
import com.productdock.rbc2024.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final CommentMapper commentMapper;
    private final BookMapper bookMapper;

    public Long addComment(CommentDto commentDto, Long bookId) {
        var bookToAddComment = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id: " + bookId + " does not exist."));
        var bookDetailsDto = bookMapper.convertModelToBookDetailsDto(bookToAddComment);
        var comment = commentMapper.convertCommentDtoToModel(commentDto, bookDetailsDto);
        return commentRepository.save(comment).getId();
    }

    public CommentDto getCommentForBookById(Long bookId, Long commentId) {
        var bookToViewComment = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id: " + bookId + " does not exist."));

        return commentRepository.findByBookIdAndId(bookToViewComment.getId(), commentId)
                .map(commentMapper::convertModelToCommentDto)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id: " + commentId + " does not exist."));
    }

    public List<CommentDto> getAllCommentsForBook(Long bookId) {
        var bookToViewComments = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id: " + bookId + " does not exist."));
        return commentRepository.findByBook(bookToViewComments)
                .stream()
                .map(commentMapper::convertModelToCommentDto)
                .collect(Collectors.toList());
    }

}
