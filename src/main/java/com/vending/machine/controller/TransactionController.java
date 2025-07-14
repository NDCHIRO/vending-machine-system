package com.vending.machine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vending.machine.dto.BuyRequest;
import com.vending.machine.dto.DepositRequest;
import com.vending.machine.dto.ProductResponse;
import com.vending.machine.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositRequest request, Principal principal) throws JsonProcessingException {
        log.info("[TransactionService][deposit] Request: {}", mapper.writeValueAsString(request));
        ResponseEntity<String> response =  new ResponseEntity<>(transactionService.deposit(request, principal.getName()), HttpStatus.OK);
        log.info("[TransactionService][deposit] Response: {}", mapper.writeValueAsString(response));
        return response;
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buy(@RequestBody BuyRequest request, Principal principal) throws JsonProcessingException {
        log.info("[TransactionService][buy] Request: {}", mapper.writeValueAsString(request));
        ResponseEntity<String> response =  new ResponseEntity<>(transactionService.buy(request, principal.getName()), HttpStatus.OK);
        log.info("[TransactionService][buy] Response: {}", mapper.writeValueAsString(response));
        return response;
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetDeposit(Principal principal) throws JsonProcessingException {
        log.info("[TransactionService][reset Deposit] Request: {}", mapper.writeValueAsString(principal.getName()));
        ResponseEntity<String> response =  new ResponseEntity<>(transactionService.resetDeposit(principal.getName()), HttpStatus.OK);
        log.info("[TransactionService][reset Deposit] Response: {}", mapper.writeValueAsString(response));
        return response;
    }
}
