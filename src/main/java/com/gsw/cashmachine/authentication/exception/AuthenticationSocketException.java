package com.gsw.cashmachine.authentication.exception;

/**
 * @author Eduardo Alves
 * @version 1.0
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