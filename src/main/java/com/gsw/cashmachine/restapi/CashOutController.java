package com.gsw.cashmachine.restapi;

import com.gsw.cashmachine.authentication.request.AccountRequest;
import com.gsw.cashmachine.authentication.response.AccountResponse;
import com.gsw.cashmachine.domain.cash.Cash;
import com.gsw.cashmachine.domain.cashout.CashOutService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by eduardo on 29/12/17.
 */
@Log4j
@RestController
@RequestMapping("/api")
public class CashOutController {

    @Autowired
    private CashOutService cashOutService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/cash", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cash>> process(@RequestBody AccountRequest request) {
        AccountResponse process = cashOutService.process(request);
        return new ResponseEntity(process, HttpStatus.OK);
    }
}
