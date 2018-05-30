package com.janbabs.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bookOrder_id")
    private BookOrder bookOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    private int quantity;

    public OrderItem(CartItem cartItem) {
        this.bookOrder = new BookOrder(cartItem.getBook());
        this.quantity = cartItem.getQuantity();
    }
}
