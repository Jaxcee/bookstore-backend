package org.example.bookstoreappllicatiodemo.repository;

import org.example.bookstoreappllicatiodemo.entity.CartEntity;
import org.example.bookstoreappllicatiodemo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {


    List<CartEntity> findByUserId(Long userId);
}