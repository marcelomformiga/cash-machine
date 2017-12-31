package com.gsw.cashmachine.domain.transaction.impl;

import com.gsw.cashmachine.domain.account.Account;
import com.gsw.cashmachine.domain.transaction.Transaction;
import com.gsw.cashmachine.domain.transaction.TransactionRepository;
import com.gsw.cashmachine.domain.transaction.TransactionService;
import com.gsw.cashmachine.domain.transaction.TransactionType;
import com.gsw.cashmachine.domain.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by eduardo on 30/12/17.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Override
    public void addTransaction(Double value, Account account, TransactionType type) {
        Transaction transaction = new Transaction(account, value,
                new GregorianCalendar(), type);
        account.addTransaction(transaction);
    }

    public List<Transaction> loadTransactionsByUsername(final String username) throws UserNotFoundException {
        List<Transaction> transactions = repository.findTransactionsByUsername(username);
        if(transactions == null) {
            throw new UserNotFoundException("Cannot find transactions by User");
        }
        return transactions;
    }
}
