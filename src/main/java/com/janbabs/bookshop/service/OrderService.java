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
import java.util.List;
import java.util.Optional;

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

    public List<Order> findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user.getUserType() == userType.ADMIN) {
            return orderRepository.findAll();
        }
        return orderRepository.findAllByUser(user);
    }

    public void changeOrderStatus(Long id, String status) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent()) {
            return;
        }
        Order order = orderOptional.get();
        if (status.equals("zrealizowane")) {
            order.setOrderStatus(orderStatus.ZREALIZOWANE);
        }
        if (status.equals("anuluj")) {
            order.setOrderStatus(orderStatus.ANULOWANE);
        }
    }

    public Order findById(Long id) {
        return orderRepository.getOne(id);
    }
}
