package com.janbabs.bookshop.service;

import com.janbabs.bookshop.domain.*;
import com.janbabs.bookshop.repository.BookRepository;
import com.janbabs.bookshop.repository.OrderRepository;
import com.janbabs.bookshop.repository.UserRepository;
import com.janbabs.bookshop.transport.OrderTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public void save(OrderTO orderTO, String userLogin, Long bookId) {
        User user = userRepository.findByLogin(userLogin);
        Book book = bookRepository.getOne(bookId);
        Date date = new Date();
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        Order order = convertOrderTOtoOrder(orderTO);
        order.setOrderDate(dateSql);
        order.setUser(user);
        order.setBook(book);
        order.setOrderStatus(orderStatus.PRZYJETE);
        order.setPrice(book.getPrice());
        orderRepository.save(order);

    }

    private Order convertOrderTOtoOrder(OrderTO orderTO) {
        Order order = new Order();
        Address address = new Address(orderTO.getStreet(), orderTO.getCity(), orderTO.getZipcode());
        order.setAddress(address);
        order.setPhonenumber(orderTO.getPhonenumber());
        return order;
    }
}
