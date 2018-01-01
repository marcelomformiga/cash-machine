package com.gsw.cashmachine.unit;

import com.gsw.cashmachine.authentication.request.AccountRequest;
import com.gsw.cashmachine.authentication.response.AccountResponse;
import com.gsw.cashmachine.domain.cashmachine.CashMachineService;
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
public class CashMachineTest {

    @Autowired
    private CashMachineService machineService;

    private AccountRequest request;

    @Test
    public void cashout() {
        request = new AccountRequest(50.0, "eduardo");
        AccountResponse response = machineService.cashout(request);
        Assert.assertEquals("SUCCESS", response.getMessage());
    }

    @Test
    public void invalidValue() {
        request = new AccountRequest(56.0, "eduardo");
        AccountResponse response = machineService.cashout(request);
        Assert.assertEquals("Invalid value", response.getMessage());
    }

    @Test
    public void deposit() {
        request = new AccountRequest(500.0, "eduardo");
        AccountResponse deposit = machineService.deposit(request);
        Assert.assertEquals("SUCCESS", deposit.getMessage());
    }

    @Test
    public void userNotFound() {
        request = new AccountRequest(500.0, "eduardooo");
        AccountResponse deposit = machineService.deposit(request);
        Assert.assertEquals("Cannot find account by User", deposit.getMessage());
    }

    @Test
    public void getBalance() {
        AccountResponse balance = machineService.getBalance("eduardo");
        Assert.assertEquals("SUCCESS", balance.getMessage());
    }

    @Test
    public void loadTrasactionsByUsername() {
        AccountResponse response = machineService.loadTrasactionsByUsername("username");
        Assert.assertEquals("SUCCESS", response.getMessage());
    }
}
