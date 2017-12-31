package com.gsw.cashmachine.domain.cashout.impl;

import com.gsw.cashmachine.authentication.request.AccountRequest;
import com.gsw.cashmachine.authentication.response.AccountResponse;
import com.gsw.cashmachine.domain.account.AccountException;
import com.gsw.cashmachine.domain.account.AccountService;
import com.gsw.cashmachine.domain.cash.Cash;
import com.gsw.cashmachine.domain.cash.CashService;
import com.gsw.cashmachine.domain.cashout.CashOutException;
import com.gsw.cashmachine.domain.cashout.CashOutService;
import com.gsw.cashmachine.domain.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eduardo on 30/12/17.
 */
@Service
public class CashOutOutServiceIml implements CashOutService {

    @Autowired
    private CashService cashService;

    @Autowired
    private AccountService accountService;

    @Override
    public AccountResponse process(AccountRequest accountRequest) {
        try {
            List<Cash> cash = cashService.processValue(accountRequest.getValue());
            accountService.cashOut(accountRequest);
            return new AccountResponse(cash, "SUCCESS");
        } catch (AccountException | UserNotFoundException | CashOutException e) {
            return new AccountResponse(e.getMessage());
        }
    }
}
