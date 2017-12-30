package com.gsw.cashmachine.domain.cashmachine;

import com.gsw.cashmachine.authentication.request.CashMachineRequest;

import java.util.List;

/**
 * Created by eduardo on 29/12/17.
 */
public interface CashService {

    List<Cash> process(final CashMachineRequest machineRequest);
}
