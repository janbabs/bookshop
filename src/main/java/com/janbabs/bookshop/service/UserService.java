package com.janbabs.bookshop.service;

import com.janbabs.bookshop.domain.User;
import com.janbabs.bookshop.domain.userType;
import com.janbabs.bookshop.exceptions.ResourceNotFoundException;
import com.janbabs.bookshop.exceptions.UserAlreadyExistsException;
import com.janbabs.bookshop.repository.UserRepository;
import com.janbabs.bookshop.transport.UserDTO;
import com.janbabs.bookshop.transport.UserEditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public void save(UserDTO userDTO) {
        if (loginExists(userDTO.getLogin())) {
            throw new UserAlreadyExistsException( userDTO.getLogin());
        }
        User user = this.convertToUser(userDTO);
        if (user.getUserType().equals(userType.ADMIN)) user.setCart(null);
        userRepository.save(user);
    }

    private User convertToUser(UserDTO userDTO) {
        String hashPassword = passwordEncoder.encode(userDTO.getPassword());
        return new User(userDTO.getLogin(), hashPassword, userDTO.getFirstName(),
                        userDTO.getLastName(), userDTO.getEmail(), userDTO.getUserType());
    }

    public boolean loginExists(String login) {
        User user = userRepository.findByLogin(login);
        return user != null;
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public List<UserDTO> findAll() {
        List <User> userList = userRepository.findAll();
        List <UserDTO> userDTOS= new ArrayList<>();
        for (User user: userList) {
            userDTOS.add(new UserDTO(user));
        }
        return userDTOS;
    }

    public UserEditDTO findUserEditDTOById(Long id) {
        return new UserEditDTO(userRepository.getOne(id));
    }

    public void promoteToAdmin(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) throw new ResourceNotFoundException("Użytkownik nie istnieje");
        User user = userOptional.get();
        user.setUserType(userType.ADMIN);
        user.setCart(null);
        userRepository.saveAndFlush(user);
    }

    public boolean isUserAdmin(String login) {
        return userRepository.findByLogin(login).getUserType() == userType.ADMIN;
    }

    public void update(UserEditDTO userEditDTO) {
        Optional<User> optionalUser = userRepository.findById(userEditDTO.getId());
        if (!optionalUser.isPresent()) throw new ResourceNotFoundException("Użytkownik o podanych id nie istnieje");
        User user = optionalUser.get();
        user.setEmail(userEditDTO.getEmail());
        user.setFirstName(userEditDTO.getFirstName());
        user.setLastName(userEditDTO.getLastName());
        userRepository.saveAndFlush(user);
    }

    public UserEditDTO getUserIDTOLogin(String login) {
        return new UserEditDTO(userRepository.findByLogin(login));
    }
}
