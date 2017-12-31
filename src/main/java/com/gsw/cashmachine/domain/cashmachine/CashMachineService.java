package com.gsw.cashmachine.domain.cashmachine;

import com.gsw.cashmachine.authentication.request.AccountRequest;
import com.gsw.cashmachine.authentication.response.AccountResponse;
import com.gsw.cashmachine.domain.user.User;

/**
 * @author Eduardo Alves
 * @version 1.0
 */
public interface CashMachineService {

    AccountResponse cashout(final AccountRequest accountRequest);

    AccountResponse deposit(final AccountRequest request);

    AccountResponse getBalance(final String username);

    AccountResponse loadTrasactionsByUsername(final String username);
}
