package com.janbabs.bookshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String title;
    private BigDecimal price;
    @Column(length = 200)
    private String urlPhoto;

    public BookOrder(Book book) {
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.urlPhoto = book.getUrlPhoto();
    }
}
