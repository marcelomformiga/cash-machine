package com.gsw.cashmachine.domain.cashmachine;

/**
 * @author Eduardo Alves
 * @version 1.0
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
