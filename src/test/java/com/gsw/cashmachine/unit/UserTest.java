package com.gsw.cashmachine.unit;

import com.gsw.cashmachine.domain.account.Account;
import com.gsw.cashmachine.domain.user.ExistUserException;
import com.gsw.cashmachine.domain.user.User;
import com.gsw.cashmachine.domain.user.UserNotFoundException;
import com.gsw.cashmachine.domain.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    private User user;

    @Test
    public void save() {
        user = new User("thiago", "1234", "thiago@hotmail.com",
                "ROLE_USER", new Account("1236", "123658", 50.0));
        try {
            userService.save(user);
        } catch (ExistUserException e) {
            Assert.assertNull(e.getMessage());
        }
    }

    @Test
    public void existingUser() {
        user = new User("eduardo", "1234", "thiago@hotmail.com",
                "ROLE_USER", new Account("1236", "123658", 50.0));
        try {
            userService.save(user);
        } catch (ExistUserException e) {
            Assert.assertEquals("User Already Exists!", e.getMessage());
        }
    }

    @Test
    public void edit() {
        user = new User("thiago", "1234", "thiago@hotmail.com",
                "ROLE_USER", new Account("1236", "123658", 50.0));
        try {
            User save = userService.save(user);
            save.setEmail("email@email.com");
            userService.edit(save);
        } catch (ExistUserException | UserNotFoundException e) {
            Assert.assertNull(e.getMessage());
        }
    }

    @Test
    public void userNotFound() {
        user = new User("thiago", "1234", "thiago@hotmail.com",
                "ROLE_USER", new Account("1236", "123658", 50.0));
        try {
            userService.edit(user);
        } catch (ExistUserException | UserNotFoundException e) {
            Assert.assertEquals("User Not Found!", e.getMessage());
        }
    }

    @Test
    public void delete() {
        user = new User("thiago", "1234", "thiago@hotmail.com",
                "ROLE_USER", new Account("1236", "123658", 50.0));
        try {
            User save = userService.save(user);
            userService.delete(save);
        } catch (ExistUserException | UserNotFoundException e) {
            Assert.assertNull(e.getMessage());
        }
    }

    @Test
    public void loadUserByUsername() {
        try {
            User byUsername = userService.loadUserByUsername("eduardo");
            Assert.assertEquals("eduardo", byUsername.getUsername());
        } catch (UserNotFoundException e) {
            Assert.assertNull(e.getMessage());
        }
    }

    @Test
    public void getAllUsers() {
        try {
            List<User> allUsers = userService.getAllUsers();
            Assert.assertNotNull(allUsers);
        } catch (UserNotFoundException e) {
            Assert.assertNull(e.getMessage());
        }
    }

    @Test
    public void loadUserById() {
        try {
            userService.loadUserById(Long.valueOf(1));
        } catch (UserNotFoundException e) {
            Assert.assertNull(e.getMessage());
        }
    }
}
