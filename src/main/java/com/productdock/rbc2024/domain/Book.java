package com.productdock.rbc2024.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    private String author;

    private Integer numberOfPages;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Book)) {
            return false;
        }

        Book book = (Book) object;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(numberOfPages, book.numberOfPages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, numberOfPages);
    }

}
