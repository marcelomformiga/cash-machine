package com.gsw.cashmachine.domain.cashout;

import com.gsw.cashmachine.authentication.request.AccountRequest;
import com.gsw.cashmachine.authentication.response.AccountResponse;

/**
 * Created by eduardo on 29/12/17.
 */
public interface CashOutService {

    AccountResponse process(final AccountRequest accountRequest);
}
