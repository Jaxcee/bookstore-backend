package org.example.bookstoreappllicatiodemo.controller;


import org.example.bookstoreappllicatiodemo.dto.OrderRequestDTO;
import org.example.bookstoreappllicatiodemo.entity.OrderEntity;
import org.example.bookstoreappllicatiodemo.service.orderservices.IOrderService;
import org.example.bookstoreappllicatiodemo.util.UserJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private UserJWT userJWT; // Assuming this is a component for handling JWT token operations


    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDTO orderRequest, @RequestHeader String token) {
        try {
            long userId = userJWT.decodeToken(token); // Decode the JWT to get the user ID
            OrderEntity order = orderService.placeOrder(orderRequest.getBookIds(), userId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order placed successfully");
            response.put("orderId", order.getOrderId());
            return ResponseEntity.ok().body(response);
        } catch (ResponseStatusException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error placing order");
            errorResponse.put("reason", e.getReason());
            return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error placing order");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}



































    // Endpoint to cancel an order
//    @PutMapping("/{orderId}/cancel")
//    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) {
//        try {
//            boolean result = orderService.cancelOrder(orderId);
//            if (result) {
//                return ResponseEntity.ok().body("Order cancelled successfully.");
//            } else {
//                return ResponseEntity.badRequest().body("Order already cancelled or not found.");
//            }
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    // Endpoint to retrieve all orders (not cancelled)
//    @GetMapping
//    public ResponseEntity<List<OrderEntity>> getAllOrders() {
//        List<OrderEntity> orders = orderService.getAllOrders();
//        return ResponseEntity.ok(orders);
//    }
//
//    // Endpoint to retrieve all orders for a specific user (not cancelled)
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<OrderEntity>> getAllOrdersForUser(@PathVariable Long userId) {
//        try {
//            List<OrderEntity> orders = orderService.getAllOrdersForUser(userId);
//            return ResponseEntity.ok(orders);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }


