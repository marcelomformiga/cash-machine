package com.gsw.cashmachine.domain.user.impl;

import com.gsw.cashmachine.domain.transaction.TransactionRepository;
import com.gsw.cashmachine.domain.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * A classe UserService e reponsavel por gerenciar as operacoes de CRUD de usuario.
 *
 * @author Eduardo Alves
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User save(final User user) throws ExistUserException {
        User userEntity = this.userRepository.findByUsername(user.getUsername());
        if (userEntity != null) throw new ExistUserException();
        else {
            userEntity = new User(user.getUsername(), encoder.encode(user.getPassword()), user.getEmail(), user.getAuthorities(), user.getAccount());
            userRepository.save(userEntity);
        }
        return userEntity;
    }

    @Override
    public User edit(final User user) throws ExistUserException, UserNotFoundException {
        User userById = loadUserById(user.getId());
        User userEntity = this.userRepository.findByUsername(user.getUsername());
        if (userEntity != null) compareUsers(userById, userEntity);
        if (user.getPassword().equals("") || user.getPassword() == null) {
            user.setPassword(userById.getPassword());
            return userRepository.save(user);
        } else {
            return userRepository.save(new User(user.getId(), user.getUsername(), encoder.encode(user.getPassword()), user.getAuthorities(), user.getEmail(), user.getAccount()));
        }
    }

    private void compareUsers(final User user1, final User user2) throws ExistUserException {
        if (!Objects.equals(user1.getId(), user2.getId())) {
            throw new ExistUserException();
        }
    }

    @Override
    public void delete(final User user) throws UserNotFoundException {
        User byUsername = loadUserByUsername(user.getUsername());
        userRepository.delete(byUsername);
    }


    @Override
    public User loadUserByUsername(String username) throws UserNotFoundException {
        User userEntity = this.userRepository.findByUsername(username);
        if (userEntity == null) throw new UserNotFoundException();
        return userEntity;
    }

    @Override
    public User loadUserById(Long id) throws UserNotFoundException {
        User userEntity = userRepository.findById(id);
        if (userEntity == null) throw new UserNotFoundException();
        return userEntity;
    }

    @Override
    public List<User> getAllUsers() throws UserNotFoundException {
        List<User> userList = userRepository.findAll();
        if (userList == null) {
            throw new UserNotFoundException("Users not found");
        }
        return userList;
    }

}
