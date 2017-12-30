package com.gsw.cashmachine.domain.user;

import java.util.List;

/**
 * Created by eduardo on 29/12/17.
 */
public interface UserService {

    User save(final User user) throws ExistUserException;

    User edit(final User user);

    void delete(final User user);

    User loadUserByUsername(String username);

    List<User> getAllUsers();
}
