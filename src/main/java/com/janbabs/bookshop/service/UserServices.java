package com.janbabs.bookshop.service;


import com.janbabs.bookshop.domain.User;
import com.janbabs.bookshop.domain.userType;
import com.janbabs.bookshop.repository.UserRepository;
import com.janbabs.bookshop.transport.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServices {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(String login) {
        return userRepository.findByLogin(login);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void save(UserTO userTo) {
        userRepository.save(this.convertToUser(userTo));
    }

    private User convertToUser(UserTO userTo) {
        String hashPassword = passwordEncoder.encode(userTo.getPassword());
        return new User(userTo.getLogin(), hashPassword, userTo.getFirstName(), userTo.getLastName(), userTo.getEmail(), userTo.getUserType());
    }
}
