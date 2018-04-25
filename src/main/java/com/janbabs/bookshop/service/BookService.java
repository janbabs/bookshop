package com.janbabs.bookshop.service;

import com.janbabs.bookshop.domain.Book;
import com.janbabs.bookshop.repository.BookRepository;
import com.janbabs.bookshop.transport.BookTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookTO> findAll() {
        List<Book> books = this.bookRepository.findAll();
        List<BookTO> booksTO = new ArrayList<>();
        for (Book book : books) {
            booksTO.add(new BookTO(book));
        }
        return booksTO;
    }

    public void save(BookTO bookTO) {
        this.bookRepository.save(convertToBook(bookTO));
    }

    public void delete(Long id) {
        this.bookRepository.deleteById(id);
    }

    public BookTO findOne(Long id) {
        Optional<Book> bookOptional = this.bookRepository.findById(id);
        Book book = bookOptional.get();

        BookTO bookTO = new BookTO(book);
        return bookTO;
    }

    private Book convertToBook(BookTO bookTO) {
        return new Book(bookTO.getTitle(), bookTO.getAuthor(),
                bookTO.getPrice(), bookTO.getPublisher(), bookTO.getDescription(), bookTO.getUrlPhoto());
    }



}

