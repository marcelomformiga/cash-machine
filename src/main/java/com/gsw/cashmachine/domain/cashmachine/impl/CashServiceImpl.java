package com.gsw.cashmachine.domain.cashmachine.impl;

import com.gsw.cashmachine.authentication.request.CashMachineRequest;
import com.gsw.cashmachine.domain.cashmachine.Cash;
import com.gsw.cashmachine.domain.cashmachine.CashService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eduardo on 29/12/17.
 */
@Service
public class CashServiceImpl  implements CashService {

    @Override
    public List<Cash> process(CashMachineRequest machineRequest) {
        return null;
    }
}
