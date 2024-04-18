package org.example.bookstoreappllicatiodemo.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class OrderSummaryDTO {
    private Long orderId;
    private LocalDate orderData;
    private int orderPrice;
    private int orderQuantity;
    private Boolean orderCancel;
    private Long userID;
    private Long cartID;
    private Boolean cartOrderedBoolean;
}
