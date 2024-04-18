package org.example.bookstoreappllicatiodemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "user_id")

    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id")

    private BookEntity book;

    private Long quantity;
    private Long totalPrice;
    private boolean ordered;







}
