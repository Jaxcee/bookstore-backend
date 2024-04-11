package org.example.bookstoreappllicatiodemo.service.userservices;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.example.bookstoreappllicatiodemo.util.EmailSender;
import org.example.bookstoreappllicatiodemo.util.UserJWT;
import org.example.bookstoreappllicatiodemo.dto.UserLoginDTO;
import org.example.bookstoreappllicatiodemo.entity.UserEntity;
import org.example.bookstoreappllicatiodemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class UserService implements IUserRegistration {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserJWT userJwt;

    @Autowired
    EmailSender emailSender;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public String UserRegistration(UserEntity userEntity) {
//        BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
        String encryptedPass = bCryptPasswordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encryptedPass);
        userRepository.save(userEntity);
        String body = "Thanks for registering in BookStore Application" + "One step ahead Click to verify" + " " + "http://localhost:8080/login";
        String subject = "You have been successfully registered";
        System.out.println(userEntity.getEmail());
        emailSender.sendEmail(userEntity.getEmail(), subject, body);
        return "User register successfully";
    }


    public String UserLogin(UserLoginDTO userLoginDto) {
        UserEntity userEntity = userRepository.findByEmailId(userLoginDto.getEmail());
//        BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();

        if (userEntity != null && bCryptPasswordEncoder.matches(userLoginDto.getPassword(), userEntity.getPassword())) {
            String token = userJwt.createToken(userEntity.getId());
            String body = "Successfully registered";
            String subject = "account verified";
            emailSender.sendEmail(userLoginDto.getEmail(), subject, body);
            return "Login successful. JWT Token: " + token;
        } else {
            return "Invalid credentials. Login failed.";
        }
    }
    @Override
    public Optional<UserEntity> getUserByJWT(String token) {
        long userId = userJwt.decodeToken(token);
        System.out.println(" service"+userId);
        return userRepository.findById(userId);
    }



    public void sendOtpToEmail(String email) {
        // Generate OTP
        String otp = generateOtp();

        // Send OTP to Email
        String body = "Your OTP for password reset is: " + otp;
        String subject = "Password Reset OTP";
        emailSender.sendEmail(email, subject, body);

        // Store OTP in the database
        UserEntity userEntity = userRepository.findByEmailId(email);
        if (userEntity != null) {
            userEntity.setOtp(otp);
            userRepository.save(userEntity);

        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    public void resetPassword(String email, String otp, String newPassword) {
        UserEntity userEntity = userRepository.findByEmailId(email);
        if (userEntity != null && validateOtp(email, otp)) {
            //here i am doing password reset logic
            String encryptedPass = bCryptPasswordEncoder.encode(newPassword);
            userEntity.setPassword(encryptedPass);
            // in this i am doing clear OTP after successful password reset
            userEntity.setOtp(null);
            userRepository.save(userEntity);
            String body = "Your password has been changed sucessfully";
            String subject = "Password Changed";
            emailSender.sendEmail(email, subject, body);
        } else {
            throw new RuntimeException("Invalid OTP or email address.");
        }
    }
//here otp is checked
    private boolean validateOtp(String email, String otp) {
        UserEntity userEntity = userRepository.findByEmailId(email);
        if (userEntity != null && userEntity.getOtp() != null && userEntity.getOtp().equals(otp)) {

            return true;
        } else {

            return false;
        }
    }

    private String generateOtp() {
        Random random = new Random();
        int otpNumber = 100000 + random.nextInt(900000);
        return String.valueOf(otpNumber);
    }


}
