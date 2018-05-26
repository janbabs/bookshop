package com.janbabs.bookshop.service;

import com.janbabs.bookshop.domain.Book;
import com.janbabs.bookshop.domain.Cart;
import com.janbabs.bookshop.exceptions.ResourceNotFoundException;
import com.janbabs.bookshop.repository.CartRepository;
import com.janbabs.bookshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public Cart getOne(Long id) {
        return cartRepository.getOne(id);
    }

    public void addBook(Long cartId, Book book, int quantity) {
        Cart cart = cartRepository.getOne(cartId);
        cart.addBook(book, quantity);
        cartRepository.saveAndFlush(cart);
    }

    public void removeFromCart(Long cartId, Long bookId) {
        Cart cart = getOne(cartId);
        cart.removeBookFromCart(bookId);
        cartRepository.saveAndFlush(cart);
    }

    public Cart getCartByUserLogin(String userLogin) {
        return userRepository.findByLogin(userLogin).getCart();
    }

    public Cart getCart(Long cartId) {
        return cartRepository.getOne(cartId);
    }

    public int getCartTotalPrice(Long cartId) {
        return getOne(cartId).getTotalPrice();
    }

    public void editQuantity(Long cartId, Long cartItemId, int quantity) {
        Cart cart = cartRepository.getOne(cartId);
        cart.editCartItemQuantity(cartItemId, quantity);
        cartRepository.saveAndFlush(cart);
    }

    public void deleteAll(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (!cartOptional.isPresent()) throw new ResourceNotFoundException("Modyfikowany koszyk nie istnieje");
        Cart cart = cartOptional.get();
        cart.deleteAllItems();
        cartRepository.saveAndFlush(cart);
    }
}
