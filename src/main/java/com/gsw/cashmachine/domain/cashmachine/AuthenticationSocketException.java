package com.gsw.cashmachine.domain.cashmachine;

/**
 * Created by eduardo on 29/12/17.
 */
public class AuthenticationSocketException extends Exception {

    private static final long serialVersionUID = 1997753363232807009L;

    public AuthenticationSocketException() {
        super("There was a problem authentication user");
    }

    public AuthenticationSocketException(String message) {
        super(message);
    }
}