package com.vending.machine.controller;

import com.vending.machine.dto.BuyRequest;
import com.vending.machine.dto.DepositRequest;
import com.vending.machine.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositRequest request, Principal principal) {
        return ResponseEntity.ok(transactionService.deposit(request, principal.getName()));
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buy(@RequestBody BuyRequest request, Principal principal) {
        return ResponseEntity.ok(transactionService.buy(request, principal.getName()));
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetDeposit(Principal principal) {
        return ResponseEntity.ok(transactionService.resetDeposit(principal.getName()));
    }
}
