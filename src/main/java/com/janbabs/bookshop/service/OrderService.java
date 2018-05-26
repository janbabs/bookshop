package com.janbabs.bookshop.service;

import com.janbabs.bookshop.domain.*;
import com.janbabs.bookshop.repository.OrderRepository;
import com.janbabs.bookshop.repository.UserRepository;
import com.janbabs.bookshop.transport.OrderDTO;
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

    public void save(OrderDTO orderDTO, String userLogin, Cart cart) {
        User user = userRepository.findByLogin(userLogin);
        Date date = new Date();
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        Order order = convertOrderTOtoOrder(orderDTO);
        order.setOrderDate(dateSql);
        order.setUser(user);
        order.setOrderStatus(orderStatus.PRZYJETE);
        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderItems(cart.getCartItems());
        cart.deleteAllItems();
        orderRepository.save(order);
    }

    private Order convertOrderTOtoOrder(OrderDTO orderDTO) {
        Order order = new Order();
        Address address = new Address(orderDTO.getStreet(), orderDTO.getCity(), orderDTO.getZipcode());
        order.setAddress(address);
        order.setPhonenumber(orderDTO.getPhonenumber());
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