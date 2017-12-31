package com.gsw.cashmachine.unit;

import com.gsw.cashmachine.authentication.request.AccountRequest;
import com.gsw.cashmachine.domain.account.Account;
import com.gsw.cashmachine.domain.account.AccountException;
import com.gsw.cashmachine.domain.account.AccountService;
import com.gsw.cashmachine.domain.user.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountTest {

    @Autowired
    private AccountService accountService;

    private AccountRequest request;

    @Test
    public void failFindAccountByUserTest() {
        try {
            request = new AccountRequest(50.0, "eduardooo");
            accountService.checkBalanceByUsername(request);
        } catch (AccountException ignored) {
        } catch (UserNotFoundException e) {
            Assert.assertEquals("Cannot find account by User", e.getMessage());
        }
    }

    @Test
    public void insufficientFundsTest() {
        try {
            request = new AccountRequest(500000.0, "eduardo");
            accountService.checkBalanceByUsername(request);
        } catch (AccountException e) {
            Assert.assertEquals("Insufficient funds", e.getMessage());
        } catch (UserNotFoundException ignored) {
        }
    }

    @Test
    public void accountTest() {
        try {
            request = new AccountRequest(5.0, "eduardo");
            accountService.cashOut(request);
        } catch (AccountException | UserNotFoundException e) {
            Assert.assertNull(e.getMessage());
        }
    }

    @Test
    public void depositTest() {
        try {
            request = new AccountRequest(500.0, "eduardo");
            accountService.deposit(request);
        } catch (UserNotFoundException e) {
            Assert.assertNull(e.getMessage());
        }
    }

    @Test
    public void depositNotFountUserTest() {
        try {
            request = new AccountRequest(500.0, "eduardooo");
            accountService.deposit(request);
        } catch (UserNotFoundException e) {
            Assert.assertEquals("Cannot find account by User", e.getMessage());
        }
    }

    @Test
    public void getBalance() {
        try {
            accountService.getBalance("eduardo");
        } catch (UserNotFoundException e) {
            Assert.assertNull(e.getMessage());
        }
    }

    @Test
    public void findAccountByUsername() {
        try {
            Account account = accountService.findAccountByUsername("eduardo");
            Assert.assertEquals("eduardo", account.getUser().getUsername());
        } catch (UserNotFoundException e) {
            Assert.assertNull(e.getMessage());
        }
    }
}
