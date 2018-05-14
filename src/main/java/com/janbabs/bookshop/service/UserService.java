package com.janbabs.bookshop.service;

import com.janbabs.bookshop.domain.User;
import com.janbabs.bookshop.domain.userType;
import com.janbabs.bookshop.repository.UserRepository;
import com.janbabs.bookshop.transport.UserDTO;
import com.janbabs.bookshop.transport.UserEditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public UserDTO findUserDT0ById(Long id) {
        return new UserDTO(userRepository.getOne(id));
    }

    public UserEditDTO findUserEditDTOById(Long id) {
        return new UserEditDTO(userRepository.getOne(id));
    }

    //TODO fix disabling of a user
    public void disableUser(Long id) {
        User user = userRepository.getOne(id);
        user.setActive(false);
    }

    public void save(UserDTO userDTO) {
        if (loginExists(userDTO.getLogin())) {
            return;
        }
        User user = this.convertToUser(userDTO);
//        Cart cart = new Cart(user);
//        user.setCart(cart);
        userRepository.save(user);
    }

    private User convertToUser(UserDTO userDTO) {
        String hashPassword = passwordEncoder.encode(userDTO.getPassword());
        return new User(userDTO.getLogin(), hashPassword, userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getUserType());
    }

    public void promoteToAdmin(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        user.setUserType(userType.ADMIN);
        userRepository.saveAndFlush(user);
    }
    public boolean loginExists(String login) {
        User user = userRepository.findByLogin(login);
        if (user == null)
            return false;
        return true;
    }

    public void update(UserEditDTO userEditDTO) {
        User user = userRepository.getOne(userEditDTO.getId());
        user.setEmail(userEditDTO.getEmail());
        user.setFirstName(userEditDTO.getFirstName());
        user.setLastName(userEditDTO.getLastName());
        userRepository.saveAndFlush(user);
    }
}