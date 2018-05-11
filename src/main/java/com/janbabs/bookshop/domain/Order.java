package com.janbabs.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "address_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(name = "orderDetail_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;
//    private List<OrderDetail> books = new ArrayList<>();
    private Date orderDate;
    @Column(length = 20)
    private String phonenumber;
    private int price;
    @Enumerated(value = EnumType.STRING)
    private orderStatus orderStatus;

    public Order(Address address, User user,
//                 List<OrderDetail> books
                 Book book
            , Date orderDate, String phonenumber, int price, com.janbabs.bookshop.domain.orderStatus orderStatus) {
        this.address = address;
        this.user = user;
        this.book = book;
//        this.books = books;

        this.orderDate = orderDate;
        this.phonenumber = phonenumber;
        this.price = price;
        this.orderStatus = orderStatus;
    }
}
