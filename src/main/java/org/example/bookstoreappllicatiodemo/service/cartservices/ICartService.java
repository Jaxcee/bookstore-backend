package org.example.bookstoreappllicatiodemo.service.cartservices;

import org.example.bookstoreappllicatiodemo.dto.CartDTO;

import java.util.List;

public interface ICartService {
    void addToCart(CartDTO cartDTO);
    void removeFromCart(Long cartId);
    void removeByUserId(Long userId);
    void updateQuantity(Long cartId, Long quantity);
    List<CartDTO> getAllCartItemsForUser(Long userId);
    List<CartDTO> getAllCartItems();
}
