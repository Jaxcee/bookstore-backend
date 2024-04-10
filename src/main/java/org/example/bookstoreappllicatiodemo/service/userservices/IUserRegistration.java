package org.example.bookstoreappllicatiodemo.service.userservices;


import org.example.bookstoreappllicatiodemo.dto.UserLoginDTO;
import org.example.bookstoreappllicatiodemo.entity.UserEntity;

import java.util.List;

public interface IUserRegistration {
    String UserRegistration(UserEntity userEntity);
    String UserLogin(UserLoginDTO userLoginDto);



  List<UserEntity> getUserByJWT(String token);


}

