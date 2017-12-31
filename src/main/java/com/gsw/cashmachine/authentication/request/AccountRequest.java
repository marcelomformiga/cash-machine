package com.gsw.cashmachine.authentication.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountRequest implements Serializable {

    private static final long serialVersionUID = 6624726180748515507L;
    private Double value;
    private String username;

    public AccountRequest() {
    }

    public AccountRequest(Double value, String username) {
        this.value = value;
        this.username = username;
    }
}
