package com.janbabs.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();

    private CartItem findInCartItemByBookId(Long bookId) {
        for (CartItem cartItem : this.cartItems) {
            if(cartItem.getBook().getId() == bookId) {
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

    public int getTotalPrice() {
        int total = 0;
        for (CartItem item: cartItems) {
            total += item.getBook().getPrice() * item.getQuantity();
        }
        return total;
    }

    public boolean hasCartItem(Long cartItemId) {
        for (CartItem cartItem: cartItems) {
            if(cartItem.getId() == cartItemId) {
                 return true;
            }
        }
        return false;
    }
}

