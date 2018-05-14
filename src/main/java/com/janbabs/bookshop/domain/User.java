package com.janbabs.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @Enumerated(EnumType.STRING)
    private userType userType;
    private boolean isActive;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;


    public User(String login, String password, String firstName, String lastName, String email, com.janbabs.bookshop.domain.userType userType) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
        this.isActive = true;
        this.cart = new Cart();
    }


}
