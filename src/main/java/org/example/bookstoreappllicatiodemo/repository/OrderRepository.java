package org.example.bookstoreappllicatiodemo.repository;

import org.example.bookstoreappllicatiodemo.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
