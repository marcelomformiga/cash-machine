package com.gsw.cashmachine.domain.account;

import com.gsw.cashmachine.authentication.request.AccountRequest;
import com.gsw.cashmachine.domain.user.UserNotFoundException;

/**
 * Created by eduardo on 30/12/17.
 */
public interface AccountService {

    Account checkBalanceByUsername(final AccountRequest accountRequest) throws AccountException, UserNotFoundException;

    void cashOut(final AccountRequest accountRequest) throws AccountException, UserNotFoundException;

    void deposit(final AccountRequest accountRequest) throws UserNotFoundException;

    Double getBalance(final String username) throws UserNotFoundException;

    Account findAccountByUsername(final String username) throws UserNotFoundException;
}
