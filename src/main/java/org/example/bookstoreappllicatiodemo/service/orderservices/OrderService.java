package org.example.bookstoreappllicatiodemo.service.orderservices;

import org.example.bookstoreappllicatiodemo.entity.BookEntity;
import org.example.bookstoreappllicatiodemo.entity.OrderEntity;
import org.example.bookstoreappllicatiodemo.entity.UserEntity;
import org.example.bookstoreappllicatiodemo.repository.BookRepository;
import org.example.bookstoreappllicatiodemo.repository.OrderRepository;
import org.example.bookstoreappllicatiodemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderEntity placeOrder(Set<Long> bookIds, long userId)  {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Set<BookEntity> books = new HashSet<>();
        for (Long bookId : bookIds) {
            BookEntity book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
            books.add(book);
        }

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setBooks(books);
        order.setOrderTime(LocalDateTime.now());
        return orderRepository.save(order);
    }
}





































    //    @Transactional
//    @Override
//    public boolean cancelOrder(Long orderId) {
//        Optional<OrderEntity> orderOptional = orderRepository.findById(orderId);
//        if (!orderOptional.isPresent()) {
//            throw new RuntimeException("Order not found.");
//        }
//
//        OrderEntity order = orderOptional.get();
//        if (!order.getCancel()) {
//            order.setCancel(true);
//            orderRepository.save(order);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public List<OrderEntity> getAllOrders() {
//        return orderRepository.findByCancelFalse();
//    }
//
//    @Override
//    public List<OrderEntity> getAllOrdersForUser(Long userId) {
//        return orderRepository.findByCart_User_IdAndOrderCancelFalse(userId);
//    }

