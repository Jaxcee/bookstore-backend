package org.example.bookstoreappllicatiodemo.controller;


import org.example.bookstoreappllicatiodemo.dto.OrderDTO;
import org.example.bookstoreappllicatiodemo.entity.OrderEntity;
import org.example.bookstoreappllicatiodemo.service.orderservices.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    // Endpoint to place an order
    @PostMapping("/placeOrder")
    public ResponseEntity<OrderEntity> placeOrder(@RequestBody OrderDTO orderDTO) {
        try {
            OrderEntity order = orderService.placeOrder(orderDTO);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint to cancel an order
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) {
        try {
            boolean result = orderService.cancelOrder(orderId);
            if (result) {
                return ResponseEntity.ok().body("Order cancelled successfully.");
            } else {
                return ResponseEntity.badRequest().body("Order already cancelled or not found.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to retrieve all orders (not cancelled)
    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        List<OrderEntity> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Endpoint to retrieve all orders for a specific user (not cancelled)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderEntity>> getAllOrdersForUser(@PathVariable Long userId) {
        try {
            List<OrderEntity> orders = orderService.getAllOrdersForUser(userId);
            return ResponseEntity.ok(orders);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
