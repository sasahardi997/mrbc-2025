package com.productdock.rbc2024.repository;

import com.productdock.rbc2024.domain.Book;

public class BookRepositorySetUp {

    static final String THE_GREAT_GATSBY = "The Great Gatsby";
    static final String NON_EXISTENT_BOOK = "Non-existent Book";

    static final String JAVA_PROGRAMMING = "Java Programming";
    private static final int JAVA_PROGRAMMING_NUMBER_OF_PAGES = 500;
    static final String ADVANCED_JAVA = "Advanced Java";
    static final int ADVANCED_JAVA_NUMBER_OF_PAGES = 450;
    static final String PYTHON_BASICS = "Python Basics";
    static final int PYTHON_BASICS_NUMBER_OF_PAGES = 300;

    static final int EXPECTED_NUMBER_OF_BOOKS_WITH_JAVA_IN_TITLE = 2;
    static final int EXPECTED_NUMBER_OF_BOOKS_WITH_250_TO_350_PAGES = 1;
    static final int EXPECTED_NUMBER_OF_BOOKS_WITH_300_TO_450_PAGES = 2;

    static final int UNEXISTING_MAX_NUMBER_OF_PAGES = 50;
    static final int UNEXISTING_MIN_NUMBER_OF_PAGES = 1;

    static Book getJavaBook() {
        var book = new Book();
        book.setTitle(JAVA_PROGRAMMING);
        book.setNumberOfPages(JAVA_PROGRAMMING_NUMBER_OF_PAGES);
        return book;
    }

    static Book getAdvancedJavaBook() {
        var book = new Book();
        book.setTitle(ADVANCED_JAVA);
        book.setNumberOfPages(ADVANCED_JAVA_NUMBER_OF_PAGES);
        return book;
    }

    static Book getPythonBook() {
        var book = new Book();
        book.setTitle(PYTHON_BASICS);
        book.setNumberOfPages(PYTHON_BASICS_NUMBER_OF_PAGES);
        return book;
    }
}
