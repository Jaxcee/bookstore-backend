package org.example.bookstoreappllicatiodemo.dto;

import lombok.*;

@Data
public class CartDTO {

    private Long userId;
    private Long bookId;
    private Long quantity;
    private Long totalPrice;
}