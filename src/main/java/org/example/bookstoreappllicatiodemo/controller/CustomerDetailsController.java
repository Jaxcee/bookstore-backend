package org.example.bookstoreappllicatiodemo.controller;

import org.example.bookstoreappllicatiodemo.entity.CustomerDetails;
import org.example.bookstoreappllicatiodemo.entity.UserEntity;
import org.example.bookstoreappllicatiodemo.repository.UserRepository;
import org.example.bookstoreappllicatiodemo.service.customerdetaiIservice.CustomerDetailService;
import org.example.bookstoreappllicatiodemo.util.UserJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin // Adjust this based on your actual CORS policy
public class CustomerDetailsController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerDetailService customerDetailService;
    @Autowired
    private UserJWT jwtTokenUtil;

    @PostMapping("/store")
    public ResponseEntity<CustomerDetails> storeCustomerDetails(@RequestBody CustomerDetails details,
                                                                @RequestHeader String token) {

        long userId = jwtTokenUtil.decodeToken(token);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        details.setUser(user); // Set the user to details

        CustomerDetails savedDetails = customerDetailService.saveCustomerDetails(details);
        return ResponseEntity.ok(savedDetails);
    }
}
