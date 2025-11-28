package com.productdock.rbc2024.repository;

import com.productdock.rbc2024.domain.Book;
import com.productdock.rbc2024.domain.Comment;

public class CommentRepositorySetUp {

    static final Long NON_EXISTENT_BOOK_ID = 999L;
    static final Long NON_EXISTENT_COMMENT_ID = 999L;

    static final String FIRST_COMMENT_TEXT = "Great book!";
    static final String SECOND_COMMENT_TEXT = "Very insightful";
    static final String THIRD_COMMENT_TEXT = "Highly recommend";

    static final int EXPECTED_NUMBER_OF_COMMENTS_FOR_BOOK_2 = 2;
    static final int EXPECTED_NUMBER_OF_COMMENTS_FOR_BOOK_1 = 1;

    static Book createBook(String title) {
        var book = new Book();
        book.setTitle(title);
        book.setNumberOfPages(300);
        return book;
    }

    static Comment createComment(Book book, String text) {
        var comment = new Comment();
        comment.setBook(book);
        comment.setContent(text);
        return comment;
    }
}
