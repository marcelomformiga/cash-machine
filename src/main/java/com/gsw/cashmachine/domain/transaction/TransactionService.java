package com.gsw.cashmachine.domain.transaction;

import com.gsw.cashmachine.domain.account.Account;
import com.gsw.cashmachine.domain.user.UserNotFoundException;

import java.util.List;

/**
 * Created by eduardo on 30/12/17.
 */
public interface TransactionService {

    void addTransaction(Double value, Account account, TransactionType type);

    List<Transaction> loadTransactionsByUsername(final String username) throws UserNotFoundException;
}
