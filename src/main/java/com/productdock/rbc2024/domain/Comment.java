package com.productdock.rbc2024.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Comment)) {
            return false;
        }

        Comment comment = (Comment) object;
        return Objects.equals(id, comment.id) &&
                Objects.equals(content, comment.content) &&
                Objects.equals(book, comment.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, book);
    }

}
