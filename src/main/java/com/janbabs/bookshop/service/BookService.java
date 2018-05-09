package com.janbabs.bookshop.service;

import com.janbabs.bookshop.domain.Book;
import com.janbabs.bookshop.repository.BookRepository;
import com.janbabs.bookshop.transport.BookDTO;
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

    public List<BookDTO> findAll() {
        List<Book> books = bookRepository.findAll();
        return this.convertToListBookDto(books);
    }

    public void save(BookDTO bookDTO) {
        bookRepository.save(convertToBook(bookDTO));
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public BookDTO findOne(Long id) {
        Book book = bookRepository.getOne(id);
        BookDTO bookDTO = new BookDTO(book);
        return bookDTO;
    }

    private Book convertToBook(BookDTO bookDTO) {
        return new Book(bookDTO.getTitle(), bookDTO.getAuthor(),
                bookDTO.getPrice(), bookDTO.getPublisher(), bookDTO.getDescription(), bookDTO.getUrlPhoto());
    }


    public void update(BookDTO bookDTO) {
        Book book = bookRepository.findById(bookDTO.getId()).get();
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setPrice(bookDTO.getPrice());
        book.setPublisher(bookDTO.getPublisher());
        book.setUrlPhoto(bookDTO.getUrlPhoto());
        book.setTitle(bookDTO.getTitle());
        bookRepository.saveAndFlush(book);
    }

    public List<BookDTO> findByCriteria(String criteria, String value) {
        if (criteria.equals("title")) {
            List<Book> booksList = bookRepository.findAllByTitleContainingIgnoreCase(value);
            return this.convertToListBookDto(booksList);
        }
        if (criteria.equals("author")) {
            List<Book> booksList = bookRepository.findAllByAuthorContainingIgnoreCase(value);
            return this.convertToListBookDto(booksList);
        }
        return new ArrayList<>();
    }

    private List<BookDTO> convertToListBookDto(List<Book> bookList) {
        List<BookDTO> booksTO = new ArrayList<>();
        for (Book book : bookList) {
            booksTO.add(new BookDTO(book));
        }
        return booksTO;
    }
}

