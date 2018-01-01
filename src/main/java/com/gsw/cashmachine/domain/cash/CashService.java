package com.gsw.cashmachine.domain.cash;

import com.gsw.cashmachine.domain.cashmachine.CashMachineException;

import java.util.List;

/**
 * @author Eduardo Alves
 * @version 1.0
 */
public interface CashService {

    List<Cash> processValue(final Double value) throws CashMachineException;
}
