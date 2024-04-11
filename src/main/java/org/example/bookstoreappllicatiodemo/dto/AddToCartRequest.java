package org.example.bookstoreappllicatiodemo.dto;


import lombok.Data;

@Data
public class AddToCartRequest {
    private long bookId;
    private long quantity;

}
