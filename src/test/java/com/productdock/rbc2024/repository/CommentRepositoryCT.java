package com.productdock.rbc2024.repository;

import static com.productdock.rbc2024.repository.CommentRepositorySetUp.*;
import static org.junit.jupiter.api.Assertions.*;

import com.productdock.rbc2024.SpringContextTestBase;
import com.productdock.rbc2024.domain.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

class CommentRepositoryCT extends SpringContextTestBase {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        commentRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    void findByBookShouldReturnCommentsWhenBookHasComments() {
        var book = createBook("Test Book");
        bookRepository.save(book);

        var comment1 = createComment(book, FIRST_COMMENT_TEXT);
        var comment2 = createComment(book, SECOND_COMMENT_TEXT);
        commentRepository.saveAll(List.of(comment1, comment2));

        List<Comment> result = commentRepository.findByBook(book);

        assertEquals(EXPECTED_NUMBER_OF_COMMENTS_FOR_BOOK_2, result.size());
        assertTrue(result.stream().allMatch(c -> c.getBook().getId().equals(book.getId())));
    }

    @Test
    void findByBookShouldReturnEmptyListWhenBookHasNoComments() {
        var book = createBook("Empty Book");
        bookRepository.save(book);

        List<Comment> result = commentRepository.findByBook(book);

        assertTrue(result.isEmpty());
    }

    @Test
    void findByBookShouldNotReturnCommentsFromOtherBooks() {
        var book1 = createBook("Book 1");
        var book2 = createBook("Book 2");
        bookRepository.saveAll(List.of(book1, book2));

        var comment1 = createComment(book1, FIRST_COMMENT_TEXT);
        var comment2 = createComment(book2, SECOND_COMMENT_TEXT);
        commentRepository.saveAll(List.of(comment1, comment2));

        List<Comment> result = commentRepository.findByBook(book1);

        assertEquals(EXPECTED_NUMBER_OF_COMMENTS_FOR_BOOK_1, result.size());
        assertEquals(FIRST_COMMENT_TEXT, result.getFirst().getContent());
    }

    @Test
    void findByBookIdAndIdShouldReturnCommentWhenExists() {
        var book = createBook("Test Book");
        bookRepository.save(book);

        var comment = createComment(book, FIRST_COMMENT_TEXT);
        commentRepository.save(comment);

        Optional<Comment> result = commentRepository.findByBookIdAndId(book.getId(), comment.getId());

        assertTrue(result.isPresent());
        assertEquals(FIRST_COMMENT_TEXT, result.get().getContent());
        assertEquals(book.getId(), result.get().getBook().getId());
    }

    @Test
    void findByBookIdAndIdShouldReturnEmptyWhenCommentDoesNotExist() {
        var book = createBook("Test Book");
        bookRepository.save(book);

        Optional<Comment> result = commentRepository.findByBookIdAndId(book.getId(), NON_EXISTENT_COMMENT_ID);

        assertTrue(result.isEmpty());
    }

    @Test
    void findByBookIdAndIdShouldReturnEmptyWhenBookDoesNotExist() {
        Optional<Comment> result = commentRepository.findByBookIdAndId(NON_EXISTENT_BOOK_ID, NON_EXISTENT_COMMENT_ID);

        assertTrue(result.isEmpty());
    }

    @Test
    void findByBookIdAndIdShouldReturnEmptyWhenCommentBelongsToDifferentBook() {
        var book1 = createBook("Book 1");
        var book2 = createBook("Book 2");
        bookRepository.saveAll(List.of(book1, book2));

        var comment = createComment(book2, FIRST_COMMENT_TEXT);
        commentRepository.save(comment);

        Optional<Comment> result = commentRepository.findByBookIdAndId(book1.getId(), comment.getId());

        assertTrue(result.isEmpty());
    }
}
