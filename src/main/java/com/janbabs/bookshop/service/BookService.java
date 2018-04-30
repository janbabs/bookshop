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
        List<Book> books = this.bookRepository.findAll();
        List<BookDTO> booksTO = new ArrayList<>();
        for (Book book : books) {
            booksTO.add(new BookDTO(book));
        }
        return booksTO;
    }

    public void save(BookDTO bookDTO) {
        this.bookRepository.save(convertToBook(bookDTO));
    }

    public void delete(Long id) {
        this.bookRepository.deleteById(id);
    }

    public BookDTO findOne(Long id) {
        Optional<Book> bookOptional = this.bookRepository.findById(id);
        Book book = bookOptional.get();

        BookDTO bookDTO = new BookDTO(book);
        return bookDTO;
    }

    private Book convertToBook(BookDTO bookDTO) {
        return new Book(bookDTO.getTitle(), bookDTO.getAuthor(),
                bookDTO.getPrice(), bookDTO.getPublisher(), bookDTO.getDescription(), bookDTO.getUrlPhoto());
    }


}

