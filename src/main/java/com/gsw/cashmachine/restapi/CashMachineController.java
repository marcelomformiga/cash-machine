package com.gsw.cashmachine.restapi;

import com.gsw.cashmachine.authentication.request.AccountRequest;
import com.gsw.cashmachine.authentication.response.AccountResponse;
import com.gsw.cashmachine.domain.cash.Cash;
import com.gsw.cashmachine.domain.cashmachine.CashMachineService;
import com.gsw.cashmachine.domain.user.User;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by eduardo on 29/12/17.
 */
@Log4j
@RestController
@RequestMapping("/api")
public class CashMachineController {

    @Autowired
    private CashMachineService cashMachineService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/cashout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> cashout(@RequestBody AccountRequest request) {
        AccountResponse process = cashMachineService.cashout(request);
        return new ResponseEntity(process, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/deposit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> deposit(@RequestBody AccountRequest request) {
        AccountResponse process = cashMachineService.deposit(request);
        return new ResponseEntity(process, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public ResponseEntity<AccountResponse> balance(@RequestParam("username") String username) {
        AccountResponse balance = cashMachineService.getBalance(username);
        return new ResponseEntity(balance, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/transaction", method = RequestMethod.GET)
    public ResponseEntity<AccountResponse> transaction(@RequestParam("username") String username) {
        AccountResponse transaction = cashMachineService.loadTrasactionsByUsername(username);
        return new ResponseEntity(transaction, HttpStatus.OK);
    }

}
