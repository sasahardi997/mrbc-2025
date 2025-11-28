package com.productdock.rbc2024.service;

import com.productdock.rbc2024.dto.BookDetailsDto;
import com.productdock.rbc2024.dto.BookDto;
import com.productdock.rbc2024.dto.EditBookDetailsDto;
import com.productdock.rbc2024.domain.Book;

public class BookServiceSetUp {

    static final long BOOK_ID = 1L;
    static final long NON_EXISTENT_ID = 999L;

    static final String BOOK_TITLE = "title";
    static final String BOOK_AUTHOR = "author";
    static final int BOOK_NUMBER_OF_PAGES = 120;

    static final String BOOK_1_TITLE = "Book 1";
    static final String BOOK_1_AUTHOR = "Author 1";
    static final int BOOK_1_PAGES = 100;

    static final String BOOK_2_TITLE = "Book 2";
    static final String BOOK_2_AUTHOR = "Author 2";
    static final int BOOK_2_PAGES = 200;

    static final String TEST_BOOK_TITLE = "Test Book";
    static final String TEST_AUTHOR = "Test Author";
    static final int TEST_PAGES = 150;

    static final String NEW_BOOK_TITLE = "New Book";
    static final String NEW_AUTHOR = "New Author";
    static final int NEW_PAGES = 300;

    static final String EXISTING_BOOK_TITLE = "Existing Book";
    static final String ANOTHER_AUTHOR = "Another Author";

    static final String ORIGINAL_TITLE = "Original Title";
    static final String UPDATED_TITLE = "Updated Title";
    static final int UPDATED_PAGES = 150;

    static final String DELETE_BOOK_TITLE = "Book to Delete";

    static final String JAVA_PROGRAMMING_TITLE = "Java Programming";
    static final String ADVANCED_JAVA_TITLE = "Advanced Java";
    static final String PYTHON_BASICS_TITLE = "Python Basics";
    static final String AUTHOR_1 = "Author 1";
    static final String AUTHOR_2 = "Author 2";
    static final String AUTHOR_3 = "Author 3";
    static final String SEARCH_TERM = "java";
    static final int EXPECTED_SEARCH_RESULTS = 2;

    static final String SHORT_BOOK_TITLE = "Short Book";
    static final String MEDIUM_BOOK_TITLE = "Medium Book";
    static final String LONG_BOOK_TITLE = "Long Book";
    static final int SHORT_PAGES = 50;
    static final int MEDIUM_PAGES = 150;
    static final int LONG_PAGES = 300;
    static final int FILTER_MIN_PAGES = 100;
    static final int FILTER_MAX_PAGES = 200;
    static final int EXPECTED_FILTER_RESULTS = 1;

    static final String EXPECTED_ENTITY_NOT_FOUND_EXCEPTION_MESSAGE = "Book with id: " + BOOK_ID + " does not exist.";

    static Book createBook() {
        return Book.builder()
            .id(BOOK_ID)
            .title(BOOK_TITLE)
            .author(BOOK_AUTHOR)
            .numberOfPages(BOOK_NUMBER_OF_PAGES)
            .build();
    }

    static Book createBook1() {
        return Book.builder()
            .title(BOOK_1_TITLE)
            .author(BOOK_1_AUTHOR)
            .numberOfPages(BOOK_1_PAGES)
            .build();
    }

    static Book createBook2() {
        return Book.builder()
            .title(BOOK_2_TITLE)
            .author(BOOK_2_AUTHOR)
            .numberOfPages(BOOK_2_PAGES)
            .build();
    }

    static Book createExistingBook() {
        return Book.builder()
            .id(BOOK_ID)
            .title(ORIGINAL_TITLE)
            .author(BOOK_AUTHOR)
            .numberOfPages(BOOK_1_PAGES)
            .build();
    }

    static Book createUpdatedBook() {
        return Book.builder()
            .id(BOOK_ID)
            .title(UPDATED_TITLE)
            .author(BOOK_AUTHOR)
            .numberOfPages(UPDATED_PAGES)
            .build();
    }

    static Book createJavaProgrammingBook() {
        return Book.builder()
            .title(JAVA_PROGRAMMING_TITLE)
            .author(AUTHOR_1)
            .numberOfPages(BOOK_1_PAGES)
            .build();
    }

    static Book createAdvancedJavaBook() {
        return Book.builder()
            .title(ADVANCED_JAVA_TITLE)
            .author(AUTHOR_2)
            .numberOfPages(BOOK_2_PAGES)
            .build();
    }

    static Book createMediumBook() {
        return Book.builder()
            .title(MEDIUM_BOOK_TITLE)
            .author(AUTHOR_2)
            .numberOfPages(MEDIUM_PAGES)
            .build();
    }

    static BookDto createBookDto() {
        return new BookDto(BOOK_ID, BOOK_TITLE, BOOK_AUTHOR, BOOK_NUMBER_OF_PAGES);
    }

    static BookDto createExpectedBookDto() {
        return new BookDto(BOOK_ID, BOOK_TITLE, BOOK_AUTHOR, BOOK_NUMBER_OF_PAGES);
    }

    static BookDto createBookDto1() {
        return new BookDto(null, BOOK_1_TITLE, BOOK_1_AUTHOR, BOOK_1_PAGES);
    }

    static BookDto createBookDto2() {
        return new BookDto(null, BOOK_2_TITLE, BOOK_2_AUTHOR, BOOK_2_PAGES);
    }

    static BookDto createJavaProgrammingDto() {
        return new BookDto(null, JAVA_PROGRAMMING_TITLE, AUTHOR_1, BOOK_1_PAGES);
    }

    static BookDto createAdvancedJavaDto() {
        return new BookDto(null, ADVANCED_JAVA_TITLE, AUTHOR_2, BOOK_2_PAGES);
    }

    static BookDto createMediumBookDto() {
        return new BookDto(null, MEDIUM_BOOK_TITLE, AUTHOR_2, MEDIUM_PAGES);
    }

    static EditBookDetailsDto createEditDto() {
        return new EditBookDetailsDto(UPDATED_PAGES, UPDATED_TITLE);
    }
}