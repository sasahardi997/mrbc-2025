package com.productdock.rbc2024.controller;

import com.productdock.rbc2024.dto.BookDetailsDto;
import com.productdock.rbc2024.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDetailsDto>> getAllBooks(){
        var books = bookService.getAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailsDto> getBookInfo(@PathVariable Long id){
        return new ResponseEntity<>(bookService.getBookDetailsById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createBook(@RequestBody BookDetailsDto bookDetailsDto){
        var bookId = bookService.createBook(bookDetailsDto);
        return new ResponseEntity<>(bookId, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
