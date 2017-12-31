package com.gsw.cashmachine.domain.account.impl;

import com.gsw.cashmachine.authentication.request.AccountRequest;
import com.gsw.cashmachine.domain.account.*;
import com.gsw.cashmachine.domain.transaction.TransactionService;
import com.gsw.cashmachine.domain.transaction.TransactionType;
import com.gsw.cashmachine.domain.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by eduardo on 28/12/17.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    @Override
    public Account checkBalanceByUsername(final AccountRequest accountRequest) throws AccountException, UserNotFoundException {
        Account account = findAccountByUsername(accountRequest.getUsername());
        if (account.getBalance() < accountRequest.getValue()) {
            throw new AccountException("Insufficient funds");
        }
        return account;
    }

    @Override
    @Transactional
    public void cashOut(final AccountRequest accountRequest) throws AccountException, UserNotFoundException {
        Account account = checkBalanceByUsername(accountRequest);
        account.cashOut(accountRequest.getValue());
        saveTransaction(accountRequest.getValue(), account, TransactionType.CASHOUT);
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void deposit(final AccountRequest accountRequest) throws UserNotFoundException {
        Account account = findAccountByUsername(accountRequest.getUsername());
        account.deposit(accountRequest.getValue());
        saveTransaction(accountRequest.getValue(), account, TransactionType.DEPOSIT);
        accountRepository.save(account);
    }

    @Override
    public Double getBalance(final String username) throws UserNotFoundException {
        Account account = findAccountByUsername(username);
        return account.getBalance();
    }

    @Override
    public Account findAccountByUsername(final String username) throws UserNotFoundException {
        Account account = accountRepository.findAccountByUsername(username);
        if(account == null) {
            throw new UserNotFoundException("Cannot find account by User");
        }
        return account;
    }

    private void saveTransaction(final Double value, final Account account, final TransactionType type) {
        transactionService.addTransaction(value, account, type);
    }
}
