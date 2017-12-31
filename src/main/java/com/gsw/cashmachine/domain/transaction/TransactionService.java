package com.gsw.cashmachine.domain.transaction;

import com.gsw.cashmachine.domain.account.Account;

/**
 * Created by eduardo on 30/12/17.
 */
public interface TransactionService {

    void addTransaction(Double value, Account account, TransactionType type);
}
