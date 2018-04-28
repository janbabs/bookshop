package com.janbabs.bookshop.repository;

import com.janbabs.bookshop.domain.Order;
import com.janbabs.bookshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
