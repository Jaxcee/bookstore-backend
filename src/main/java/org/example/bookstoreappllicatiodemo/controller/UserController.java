package org.example.bookstoreappllicatiodemo.controller;

import org.example.bookstoreappllicatiodemo.dto.ForgetPasswordDto;
import org.example.bookstoreappllicatiodemo.dto.ResetPasswordDTO;
import org.example.bookstoreappllicatiodemo.service.userservices.UserService;
import org.example.bookstoreappllicatiodemo.util.UserJWT;
import org.example.bookstoreappllicatiodemo.dto.UserLoginDTO;
import org.example.bookstoreappllicatiodemo.entity.UserEntity;
import org.example.bookstoreappllicatiodemo.service.userservices.IUserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin("http://localhost:4200")
@ResponseStatus(HttpStatus.OK)

public class UserController {
    @Autowired
    IUserRegistration iUserRegistration;

    @Autowired
    private UserService userService;
    @Autowired
    UserJWT userJwt;

    @PostMapping("/registration")
    public ResponseEntity<?> userRegistration(@RequestBody UserEntity userEntity) {
        String registrationMessage = iUserRegistration.UserRegistration(userEntity);
        Map<String, String> response = new HashMap<>();
        response.put("message", registrationMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginDTO userLoginDto) {
        String response = iUserRegistration.UserLogin(userLoginDto);
        if(response.contains("Login successful")) {
            String token = response.split("JWT Token: ")[1];
            return ResponseEntity.ok(Map.of("message", "Login successful", "token", token));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid credentials"));
        }
    }


    @GetMapping("getByToken")
     public Optional<UserEntity> getUserByJWT(@RequestHeader String token) {
        return iUserRegistration.getUserByJWT(token);
}




    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestBody ForgetPasswordDto forgetPasswordDto) {
        String email = forgetPasswordDto.getEmail();
        userService.sendOtpToEmail(email);
        return ResponseEntity.ok("OTP sent to email for password reset.");
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        String email =resetPasswordDTO.getEmail();
        String otp =resetPasswordDTO.getOtp();
        String newPassword = resetPasswordDTO.getNewPassword();
        userService.resetPassword(email, otp, newPassword);
        return ResponseEntity.ok("Password reset successfully.");
    }

//    @GetMapping("getByToken")
//     public List<UserEntity> getUserByJWT(@RequestHeader String token) {
//        return iUserRegistration.getUserByJWT(token);
    }



