package org.example.bookstoreappllicatiodemo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name="orders")
public class OrderEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
        private CartEntity cart;

        @Column(nullable = false)
        private LocalDateTime orderDate;

        @Column(nullable = false)
        private Boolean cancel;


        @Column(length = 500) // Adjust the length according to your needs
        private String address;
        // Constructors, getters, and setters
    }





