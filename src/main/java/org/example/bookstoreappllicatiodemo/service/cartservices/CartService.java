package org.example.bookstoreappllicatiodemo.service.cartservices;

import org.example.bookstoreappllicatiodemo.dto.CartDTO;
import org.example.bookstoreappllicatiodemo.entity.BookEntity;
import org.example.bookstoreappllicatiodemo.entity.CartEntity;
import org.example.bookstoreappllicatiodemo.entity.UserEntity;
import org.example.bookstoreappllicatiodemo.repository.BookRepository;
import org.example.bookstoreappllicatiodemo.repository.CartRepository;
import org.example.bookstoreappllicatiodemo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void addToCart(CartDTO cartDTO) {


        UserEntity user  = userRepository.findById(cartDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        BookEntity book = bookRepository.findById(cartDTO.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));


        CartEntity cartEntity = new CartEntity();

        cartEntity.setBook(book);
        cartEntity.setUser(user);
        cartEntity.setQuantity(cartDTO.getQuantity());
        cartEntity.setTotalPrice(book.getBookPrice() * cartDTO.getQuantity()); // Using book price directly from the BookEntity

        cartRepository.save(cartEntity);
    }

    @Override
    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public void removeByUserId(Long userId) {
        List<CartEntity> cartEntities = cartRepository.findByUserId(userId);
        cartRepository.deleteAll(cartEntities);
    }

    @Override
    public void updateQuantity(Long cartId, Long quantity) {
        CartEntity cartEntity = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        BookEntity book = cartEntity.getBook();
        int bookPrice = book.getBookPrice(); // Using book price directly from the BookEntity

        cartEntity.setQuantity(quantity);
        cartEntity.setTotalPrice(bookPrice * quantity);
        cartRepository.save(cartEntity);
    }

    @Override
    public List<CartDTO> getAllCartItemsForUser(Long userId) {
        List<CartEntity> cartEntities = cartRepository.findByUserId(userId);
        return cartEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CartDTO> getAllCartItems() {
        List<CartEntity> cartEntities = cartRepository.findAll();
        return cartEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CartDTO convertToDTO(CartEntity cartEntity) {
        CartDTO cartDTO = new CartDTO();


        cartDTO.setBookId(cartEntity.getBook().getBookId());
        cartDTO.setQuantity(cartEntity.getQuantity());
        cartDTO.setTotalPrice(cartEntity.getTotalPrice());
        return cartDTO;
    }
}
