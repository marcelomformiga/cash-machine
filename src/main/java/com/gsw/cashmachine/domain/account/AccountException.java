package com.gsw.cashmachine.domain.account;

/**
 * @author Eduardo Alves
 * @version 1.0
 */
public class AccountException extends Exception {

    private static final long serialVersionUID = 1997753363232807009L;

    public AccountException() {
        super("Account Exception!");
    }

    public AccountException(String message) {
        super(message);
    }
}
