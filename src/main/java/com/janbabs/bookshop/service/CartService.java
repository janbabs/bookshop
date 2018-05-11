package com.janbabs.bookshop.service;

import com.janbabs.bookshop.domain.Cart;
import com.janbabs.bookshop.domain.Book;
import com.janbabs.bookshop.repository.CartRepository;
import com.janbabs.bookshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public Cart getOne(Long id) {
        return cartRepository.getOne(id);
    }

    public void addCartItemToCart(Long cart_id, Book book, int quantity) {
        Cart cart = getOne(cart_id);
        cart.addBookToCart(book, quantity);
        cartRepository.saveAndFlush(cart);
    }

    public void removeFromCart(Long cartId, Long bookId) {
        Cart cart = getOne(cartId);
        cart.removeBookFromCart(bookId);
    }

    public Cart getCartByUserLogin(String userLogin) {
        return cartRepository.findByUser(userRepository.findByLogin(userLogin));
    }

    public int getCartTotalPrice(Long cartId) {
        return getOne(cartId).getTotalPrice();
    }
}
