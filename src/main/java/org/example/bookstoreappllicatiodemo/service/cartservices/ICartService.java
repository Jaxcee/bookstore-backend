package org.example.bookstoreappllicatiodemo.service.cartservices;

import org.example.bookstoreappllicatiodemo.dto.CartDTO;
import org.example.bookstoreappllicatiodemo.entity.CartEntity;

import java.util.List;

public interface ICartService {
//    void addToCart(CartDTO cartDTO);

     void addToCart(Long userId,Long bookId, long quantity);
    void removeFromCart(Long cartId);
    void removeByUserId(Long userId);
    void updateQuantity(Long cartId, Long quantity);
    List<CartDTO> getAllCartItemsForUser(Long userId);
    List<CartEntity> getAllCartItems(Long userId);
}
