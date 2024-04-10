package org.example.bookstoreappllicatiodemo.repository;

import org.example.bookstoreappllicatiodemo.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    // Fetch non-canceled orders
    List<OrderEntity> findByCancelFalse();

    // Fetch orders by Cart's User ID and not canceled
    List<OrderEntity> findByCart_User_IdAndCancelFalse(Long userId);
}

