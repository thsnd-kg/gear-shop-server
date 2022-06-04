package com.techshop.order.repository;

import com.techshop.order.entity.Order;
import com.techshop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByIsDeletedFalse();
    List<Order> findByUser(User user);
    List<Order> findByCreatedAtBetweenOrderByCreatedAt(LocalDateTime createdAt, LocalDateTime createdAt2);
}
