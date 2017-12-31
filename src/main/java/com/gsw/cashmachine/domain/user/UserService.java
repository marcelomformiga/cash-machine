package com.gsw.cashmachine.domain.user;

import java.util.List;

/**
 * Created by eduardo on 29/12/17.
 */
public interface UserService {

    User save(final User user) throws ExistUserException;

    User edit(final User user) throws UserNotFoundException;

    void delete(final User user) throws UserNotFoundException;

    User loadUserByUsername(String username) throws UserNotFoundException;

    List<User> getAllUsers() throws UserNotFoundException;

    User loadUserById(Long id) throws UserNotFoundException;
}
