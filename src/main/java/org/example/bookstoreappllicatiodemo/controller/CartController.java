package org.example.bookstoreappllicatiodemo.controller;

import org.example.bookstoreappllicatiodemo.dto.AddToCartRequest;
import org.example.bookstoreappllicatiodemo.dto.CartDTO;

import org.example.bookstoreappllicatiodemo.entity.CartEntity;
import org.example.bookstoreappllicatiodemo.service.cartservices.CartService;
import org.example.bookstoreappllicatiodemo.service.cartservices.ICartService;
import org.example.bookstoreappllicatiodemo.util.UserJWT;
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
    @Autowired
    UserJWT userJWT;

    @Autowired
    ICartService iCartService;



    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addToCart(@RequestBody AddToCartRequest request, @RequestHeader String token) {
        long userId = userJWT.decodeToken(token);
        try {
            iCartService.addToCart(userId, request.getBookId(), request.getQuantity());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Item added successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("error", "Failed to add item: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
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

    @GetMapping("/allcartitems")
    public ResponseEntity <List<CartEntity> >getAllCartItems(@RequestHeader String token) {
        long userId = userJWT.decodeToken(token);
        List<CartEntity> cartItems = iCartService.getAllCartItems(userId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }
}
