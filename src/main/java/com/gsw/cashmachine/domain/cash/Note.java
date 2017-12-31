package com.gsw.cashmachine.domain.cash;

/**
 * Created by eduardo on 30/12/17.
 */
public enum Note {

    NOTE_10(10), NOTE_20(20), NOTE_50(50), NOTE_100(100);

    private int value;

    Note(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
