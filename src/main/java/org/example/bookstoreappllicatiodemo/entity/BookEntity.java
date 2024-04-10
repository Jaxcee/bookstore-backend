package org.example.bookstoreappllicatiodemo.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "bookDetails")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    private String bookName;
    private String authorName;
    private String bookDescription;
    private Integer bookPrice;
    private String bookLogo;
    private Integer bookQuantity;

    // Constructors, getters, setters, and toString method
}
