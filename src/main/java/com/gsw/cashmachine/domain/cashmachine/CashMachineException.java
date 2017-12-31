package com.gsw.cashmachine.domain.cashmachine;

/**
 * Created by eduardo on 30/12/17.
 */
public class CashMachineException extends Exception {

    private static final long serialVersionUID = 1997753363232807009L;

    public CashMachineException() {
        super("CashMachine Exception!");
    }

    public CashMachineException(String message) {
        super(message);
    }
}
