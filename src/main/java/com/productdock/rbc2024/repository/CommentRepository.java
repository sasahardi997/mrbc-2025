package com.productdock.rbc2024.repository;

import com.productdock.rbc2024.domain.Book;
import com.productdock.rbc2024.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBook(Book book);

    Optional<Comment> findByBookIdAndId(Long bookId, Long commentId);

}
