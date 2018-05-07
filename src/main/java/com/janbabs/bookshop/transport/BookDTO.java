package com.janbabs.bookshop.transport;

import com.janbabs.bookshop.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class BookDTO {

    private Long id;
    @Size(min = 5,max = 100, message = "Tytuł ksiązki może być dłuższy niż 100 znaków i nie krótszy niż 5")
    private String title;
    @Min(value = 1, message = "Cena ksiązki musi być wieksza od 0")
    private Integer price;
    @Size(max = 100, message = "Nazwa autora zbyt długa")
    private String author;
    @Size(max = 100, message = "Nazwa wydawnictwa jest zbyt długa")
    private String publisher;
    @Size(max = 2000, message = "Zbyt długi opis")
    private String description;
    @Size(max = 200, message = "Link url jest zbyt długi")
    private String urlPhoto;

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.description = book.getDescription();
        this.urlPhoto = book.getUrlPhoto();
    }

}
