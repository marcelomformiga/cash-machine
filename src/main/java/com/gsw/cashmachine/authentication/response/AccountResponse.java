package com.gsw.cashmachine.authentication.response;

import com.gsw.cashmachine.domain.cash.Cash;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AccountResponse implements Serializable {

    private static final long serialVersionUID = 6624726180748515507L;

    private List<Cash> cashList;
    private String message;
    private Double value;

    public AccountResponse() {
    }

    public AccountResponse(List<Cash> cashList, String message) {
        this.cashList = cashList;
        this.message = message;
    }

    public AccountResponse(String message, Double value) {
        this.message = message;
        this.value = value;
    }

    public AccountResponse(String message) {
        this.message = message;
    }
}
