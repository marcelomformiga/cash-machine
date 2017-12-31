package com.gsw.cashmachine.authentication.response;

import com.gsw.cashmachine.domain.cash.Cash;
import com.gsw.cashmachine.domain.transaction.Transaction;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AccountResponse implements Serializable {

    private static final long serialVersionUID = 6624726180748515507L;

    private List<Cash> cashList;
    private String message;
    private Double value;
    private List<Transaction> transactionList;

    public AccountResponse() {
    }

    public AccountResponse(List<Cash> cashList, String message) {
        this.cashList = cashList;
        this.message = message;
    }

    public AccountResponse(String message, Double value) {
        this.message = message;
        this.value = value;
    }

    public AccountResponse(String message, List<Transaction> transactionList) {
        this.message = message;
        this.transactionList = transactionList;
    }

    public AccountResponse(String message) {
        this.message = message;
    }
}
