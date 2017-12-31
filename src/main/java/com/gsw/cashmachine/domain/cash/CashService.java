package com.gsw.cashmachine.domain.cash;

import com.gsw.cashmachine.domain.cashout.CashOutException;

import java.util.List;

/**
 * Created by eduardo on 29/12/17.
 */
public interface CashService {

    List<Cash> processValue(final Double value) throws CashOutException;
}
