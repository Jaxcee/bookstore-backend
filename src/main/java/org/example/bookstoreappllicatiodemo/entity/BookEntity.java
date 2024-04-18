package org.example.bookstoreappllicatiodemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "bookDetails")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    private String bookName;
    private String authorName;
    private String bookDescription;
    private Long bookPrice;
    private String bookLogo;
    private Long bookQuantity;


    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<CartEntity> cart;



    // Constructors, getters, setters, and toString method
}
