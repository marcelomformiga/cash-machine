package com.gsw.cashmachine.domain.cash;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Eduardo Alves
 * @version 1.0
 */
@Data
public class Cash implements Serializable {

    private static final long serialVersionUID = 2353528370345499815L;

    private Note note;

    private Integer value;

    private Integer amount;

    public Cash() {}

    public Cash(Note note, Integer value, Integer amount) {
        this.note = note;
        this.value = value;
        this.amount = amount;
    }
}
