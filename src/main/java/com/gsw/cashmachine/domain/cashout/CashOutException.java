package com.gsw.cashmachine.domain.cashout;

/**
 * Created by eduardo on 30/12/17.
 */
public class CashOutException extends Exception {

    private static final long serialVersionUID = 1997753363232807009L;

    public CashOutException() {
        super("CashOut Exception!");
    }

    public CashOutException(String message) {
        super(message);
    }
}
