package org.example.bookstoreappllicatiodemo.service.orderservices;

import org.example.bookstoreappllicatiodemo.dto.OrderDTO;
import org.example.bookstoreappllicatiodemo.entity.OrderEntity;

import java.util.List;

public interface IOrderService {



        OrderEntity placeOrder(OrderDTO orderDTO);
        boolean cancelOrder(Long orderId);
        List<OrderEntity> getAllOrders();
        List<OrderEntity> getAllOrdersForUser(Long userId);
    }


