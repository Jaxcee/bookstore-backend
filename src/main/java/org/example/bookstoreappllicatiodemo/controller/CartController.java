package org.example.bookstoreappllicatiodemo.controller;

import org.example.bookstoreappllicatiodemo.dto.CartDTO;

import org.example.bookstoreappllicatiodemo.service.cartservices.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@CrossOrigin
@RequestMapping("/cart")

public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping ("/add")
    public ResponseEntity<?> addToCart(@RequestBody CartDTO cartDTO) {
        System.out.println("Received cartDTO: " + cartDTO);  // Add this line to log the cartDTO
        try {
            cartService.addToCart(cartDTO);
            return ResponseEntity.ok().body(Map.of("message", "Item added to cart successfully."));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Failed to add item to cart."));
        }
    }




    @DeleteMapping("/remove/{cartId}")
    public void removeFromCart(@PathVariable Long cartId) {
        cartService.removeFromCart(cartId);
    }

    @DeleteMapping("/removeByUser/{userId}")
    public void removeByUserId(@PathVariable Long userId) {
        cartService.removeByUserId(userId);
    }

    @PutMapping("/updateQuantity/{cartId}/{quantity}")
    public void updateQuantity(@PathVariable Long cartId, @PathVariable Long quantity) {
        cartService.updateQuantity(cartId, quantity);
    }

    @GetMapping("/allForUser/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<CartDTO> getAllCartItemsForUser(@PathVariable Long userId) {
        return cartService.getAllCartItemsForUser(userId);
    }

    @GetMapping("/all")
    public List<CartDTO> getAllCartItems() {
        return cartService.getAllCartItems();
    }
}
