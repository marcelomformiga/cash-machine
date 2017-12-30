package com.gsw.cashmachine.authentication.service;

import com.gsw.cashmachine.authentication.request.AuthenticationSocketRequest;

/**
 * Created by eduardo on 29/12/17.
 */
public interface UserSocketService {

    void connectUser(final AuthenticationSocketRequest request);

    void disconnectUser(final AuthenticationSocketRequest request);
}
