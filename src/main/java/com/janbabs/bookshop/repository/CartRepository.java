package com.janbabs.bookshop.repository;

import com.janbabs.bookshop.domain.Cart;
import com.janbabs.bookshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository <Cart, Long> {
    Cart findByUser(User user);
}
