package com.vending.machine.service;

import com.vending.machine.dto.BuyRequest;
import com.vending.machine.dto.DepositRequest;

public interface TransactionService {
    String deposit(DepositRequest request, String username);
    String buy(BuyRequest request, String username);
    String resetDeposit(String username);
}
