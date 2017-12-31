package com.gsw.cashmachine.authentication.service;

import com.gsw.cashmachine.authentication.request.AuthenticationSocketRequest;

/**
 * @author Eduardo Alves
 * @version 1.0
 */
public interface UserSocketService {

    void connectUser(final AuthenticationSocketRequest request);

    void disconnectUser(final AuthenticationSocketRequest request);
}
