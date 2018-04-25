package com.janbabs.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private Integer price;
    private String publisher;
    @Column(length = 1000)
    private String description;

    private String urlPhoto;

    public Book(String title, String author, Integer price, String publisher, String description, String urlPhoto) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.publisher = publisher;
        this.description = description;
        this.urlPhoto = urlPhoto;
    }
}
