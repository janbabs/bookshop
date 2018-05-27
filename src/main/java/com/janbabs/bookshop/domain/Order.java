package com.janbabs.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 12)
    private String phonenumber;
    @JoinColumn(name = "address_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();
    private Date orderDate;
    private BigDecimal totalPrice;
    @Enumerated(value = EnumType.STRING)
    private orderStatus orderStatus;

    public void setOrderItems(List<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem(cartItem);
            orderItem.setOrder(this);
            orderItems.add(orderItem);
        }
    }
}
