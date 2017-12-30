package com.gsw.cashmachine.authentication.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CashMachineRequest implements Serializable {

    private static final long serialVersionUID = 6624726180748515507L;
    private Double value;

    public CashMachineRequest() {
        super();
    }

    public CashMachineRequest(Double value) {
        this.value = value;
    }
}
