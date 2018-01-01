package com.gsw.cashmachine.domain.user;

import java.util.List;

/**
 * @author Eduardo Alves
 * @version 1.0
 */
public interface UserService {

    User save(final User user) throws ExistUserException;

    User edit(final User user) throws ExistUserException, UserNotFoundException;

    void delete(final User user) throws UserNotFoundException;

    User loadUserByUsername(String username) throws UserNotFoundException;

    List<User> getAllUsers() throws UserNotFoundException;

    User loadUserById(Long id) throws UserNotFoundException;
}
