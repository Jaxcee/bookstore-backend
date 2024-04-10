package org.example.bookstoreappllicatiodemo.repository;


import org.example.bookstoreappllicatiodemo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    UserEntity findByEmailId(@Param("email") String email);

    @Query("SELECT u FROM UserEntity u WHERE u.firstName = :firstName")
    List<UserEntity> findByFirstName(@Param("firstName") String firstName);

}

