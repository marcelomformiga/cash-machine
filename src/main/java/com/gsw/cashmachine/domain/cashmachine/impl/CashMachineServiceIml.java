package com.gsw.cashmachine.domain.cashmachine.impl;

import com.gsw.cashmachine.authentication.request.AccountRequest;
import com.gsw.cashmachine.authentication.response.AccountResponse;
import com.gsw.cashmachine.domain.account.AccountException;
import com.gsw.cashmachine.domain.account.AccountService;
import com.gsw.cashmachine.domain.cash.Cash;
import com.gsw.cashmachine.domain.cash.CashService;
import com.gsw.cashmachine.domain.cashmachine.CashMachineException;
import com.gsw.cashmachine.domain.cashmachine.CashMachineService;
import com.gsw.cashmachine.domain.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eduardo on 30/12/17.
 */
@Service
public class CashMachineServiceIml implements CashMachineService {

    @Autowired
    private CashService cashService;

    @Autowired
    private AccountService accountService;

    @Override
    public AccountResponse cashout(AccountRequest accountRequest) {
        try {
            List<Cash> cash = cashService.processValue(accountRequest.getValue());
            accountService.cashOut(accountRequest);
            return new AccountResponse(cash, "SUCCESS");
        } catch (AccountException | UserNotFoundException | CashMachineException e) {
            return new AccountResponse(e.getMessage());
        }
    }

    @Override
    public AccountResponse deposit(AccountRequest request) {
        try {
            accountService.deposit(request);
            return new AccountResponse("SUCCESS");
        } catch (UserNotFoundException e) {
            return new AccountResponse(e.getMessage());
        }
    }

    @Override
    public AccountResponse getBalance(String username) {
        try {
            return new AccountResponse("SUCCESS", accountService.getBalance(username));
        } catch (UserNotFoundException e) {
            return new AccountResponse(e.getMessage());
        }
    }
}
