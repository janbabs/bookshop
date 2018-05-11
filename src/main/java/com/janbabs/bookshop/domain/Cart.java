package com.janbabs.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @JoinColumn(name = "items_id")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CartItem> items = new ArrayList<>();

    public Cart(User user) {
        this.user = user;
    }

    public CartItem findInCartBookById(Long bookId) {
        for (CartItem cartItem : this.items) {
            if(cartItem.getBook().getId() == bookId) {
                return cartItem;
            }
        }
        return null;
    }

    //TODO fix
    public void addBookToCart(Book book, int quantity) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(new CartItem(book, quantity));
    }

    public void updateBasketItem(Long bookId, int quantity) {
        CartItem item = this.findInCartBookById(bookId);

        if (item != null){
            if(quantity <= 0) {
                this.items.remove(item);
            } else {
                item.setQuantity(quantity);
            }
        }
    }

    public void removeBookFromCart(Long bookId) {
        CartItem item = this.findInCartBookById(bookId);
        if (item != null) {
            this.items.remove(item);
        }
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public int getTotalPrice() {
        int total = 0;
        for (CartItem item: items) {
            total += item.getBook().getPrice() * item.getQuantity();
        }
        return total;
    }
}
