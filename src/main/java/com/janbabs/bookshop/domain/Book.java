package com.janbabs.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String title;
    private BigDecimal price;
    @Column(length = 100)
    private String author;
    @Column(length = 100)
    private String publisher;
    @Column(length = 2000)
    private String description;
    @Column(length = 200)
    private String urlPhoto;

    public Book(String title, String author, BigDecimal price, String publisher, String description, String urlPhoto) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.publisher = publisher;
        this.description = description;
        this.urlPhoto = urlPhoto;
    }
}
