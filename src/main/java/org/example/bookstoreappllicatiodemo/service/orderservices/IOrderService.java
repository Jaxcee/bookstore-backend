package org.example.bookstoreappllicatiodemo.service.orderservices;

import org.example.bookstoreappllicatiodemo.dto.OrderDTO;
import org.example.bookstoreappllicatiodemo.dto.OrderSummaryDTO;
import org.example.bookstoreappllicatiodemo.entity.OrderEntity;

import java.util.List;
import java.util.Set;

public interface IOrderService {

    OrderEntity placeOrder(Set<Long> bookIds, long userId);


    }


