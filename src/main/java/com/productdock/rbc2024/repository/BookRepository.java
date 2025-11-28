package com.productdock.rbc2024.repository;

import com.productdock.rbc2024.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitle(String title);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByNumberOfPagesBetween(Integer from, Integer to);

}
