package org.example.bookstoreappllicatiodemo.service.orderservices;

import org.example.bookstoreappllicatiodemo.dto.OrderDTO;
import org.example.bookstoreappllicatiodemo.entity.CartEntity;
import org.example.bookstoreappllicatiodemo.entity.OrderEntity;
import org.example.bookstoreappllicatiodemo.repository.CartRepository;
import org.example.bookstoreappllicatiodemo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    @Override
    public OrderEntity placeOrder(OrderDTO orderDTO) {
        Optional<CartEntity> cartOptional = cartRepository.findById (orderDTO.getCartId());
        if (!cartOptional.isPresent()) {
            throw new RuntimeException("Cart not found.");
        }

        CartEntity cart = cartOptional.get();



        OrderEntity order = new OrderEntity();
        order.setCart(cart);
        order.setOrderDate(LocalDateTime.now());
        order.setCancel(false);
        order.setAddress(orderDTO.getAddress());



        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public boolean cancelOrder(Long orderId) {
        Optional<OrderEntity> orderOptional = orderRepository.findById(orderId);
        if (!orderOptional.isPresent()) {
            throw new RuntimeException("Order not found.");
        }

        OrderEntity order = orderOptional.get();
        if (!order.getCancel()) {
            order.setCancel(true);
            orderRepository.save(order);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findByCancelFalse();
    }

    @Override
    public List<OrderEntity> getAllOrdersForUser(Long userId) {
        return orderRepository.findByCart_User_IdAndCancelFalse(userId);
    }
}
