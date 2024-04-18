package org.example.bookstoreappllicatiodemo.dto;

import lombok.Data;

import java.util.Set;
@Data
public class OrderRequestDTO {
    private Set<Long> bookIds;


}
