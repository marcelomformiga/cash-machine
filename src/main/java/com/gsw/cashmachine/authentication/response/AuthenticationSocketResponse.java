package com.gsw.cashmachine.authentication.response;

import lombok.Data;

import java.io.Serializable;

/**
 * A classe AuthenticationResponse e responsavel por encapsular os atributos autenticados do usuario
 *
 * @author Eduardo Alves
 * @version 1.0
 */
@Data
public class AuthenticationSocketResponse implements Serializable {

    private static final long serialVersionUID = -6624726180748515507L;
    private String sessionId;
    private String message;

    public AuthenticationSocketResponse() {}

    public AuthenticationSocketResponse(String sessionId, String message) {
        this.sessionId = sessionId;
        this.message = message;
    }
}
