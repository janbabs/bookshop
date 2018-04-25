package com.janbabs.bookshop.transport;

import com.janbabs.bookshop.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class BookTO {

    private Long id;
    @Size(max = 100, message = "Tytuł ksiązki może być dłuższy niż 100 znaków")
    private String title;
    @Min(value = 1, message = "Cena ksiązki musi być wieksza od 0")
    private Integer price;
    @Size(max = 100, message = "Nazwa autora zbyt długa")
    private String author;
    @Size(max = 100, message = "Nazwa wydawnictwa jest zbyt długa")
    private String publisher;
    @Size(max = 1000, message = "Zbyt długi opis")
    private String description;
    private String urlPhoto;

    public BookTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.description = book.getDescription();
        this.urlPhoto = book.getUrlPhoto();
    }

}
