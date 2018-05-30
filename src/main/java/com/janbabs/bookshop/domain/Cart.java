package com.janbabs.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CartItem> cartItems = new ArrayList<>();

    private CartItem findInCartItemByBookId(Long bookId) {
        for (CartItem cartItem : this.cartItems) {
            if(cartItem.getBook().getId().equals(bookId)) {
                return cartItem;
            }
        }
        return null;
    }

    public void addBook(Book book, int quantity) {
        CartItem cartItem = this.findInCartItemByBookId(book.getId());

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setQuantity(0);
            cartItem.setBook(book);
            this.cartItems.add(cartItem);
            cartItem.setCart(this);
        }
        int newQuantity = cartItem.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartItems.remove(cartItem);
        } else {
            cartItem.setQuantity(newQuantity);
        }
    }

    public void removeBookFromCart(Long bookId) {
        CartItem item = this.findInCartItemByBookId(bookId);
        if (item != null) {
            this.cartItems.remove(item);
        }
    }
    public boolean isEmpty() {
        return this.cartItems.isEmpty();
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalCost = BigDecimal.ZERO;
        BigDecimal itemCost;
        for (CartItem item: cartItems) {
            itemCost = item.getBook().getPrice().multiply(new BigDecimal(item.getQuantity()));
            totalCost = totalCost.add(itemCost);
        }
        return totalCost;
    }

    public boolean hasCartItem(Long cartItemId) {
        return getCartItemById(cartItemId) != null;
    }

    public CartItem getCartItemById(Long cartItemId) {
        for (CartItem cartItem: cartItems) {
            if(cartItem.getId().equals(cartItemId)) {
                return cartItem;
            }
        }
        return null;
    }

    public void editCartItemQuantity(Long cartItemId, int quantity) {
        CartItem cartItem = this.getCartItemById(cartItemId);
        quantity+=cartItem.getQuantity();
        if (quantity <= 0) {
            removeBookFromCart(cartItem.getBook().getId());
        }
        else {
            cartItem.setQuantity(quantity);
        }
    }

    public void deleteAllItems() {
        cartItems.clear();
    }
}

