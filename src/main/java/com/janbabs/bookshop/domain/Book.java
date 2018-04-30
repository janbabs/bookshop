package com.janbabs.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sun.security.util.Length;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String title;
    private Integer price;
    @Column(length = 100)
    private String author;
    @Column(length = 100)
    private String publisher;
    @Column(length = 1000)
    private String description;
    @Column(length = 100)
    private String urlPhoto;

    /**
     * Konstruktor obiektu book
     *
     * @param title tytuł
     * @param author    autor
     * @param price cena
     * @param publisher nazwa wydawnictwa
     * @param description   opis
     * @param urlPhoto  link url do zdjęcia okładki
     */
    public Book(String title, String author, Integer price, String publisher, String description, String urlPhoto) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.publisher = publisher;
        this.description = description;
        this.urlPhoto = urlPhoto;
    }
}
