package com.vending.machine.service;

import com.vending.machine.dto.BuyRequest;
import com.vending.machine.dto.DepositRequest;
import com.vending.machine.exception.GlobalException;
import com.vending.machine.model.Product;
import com.vending.machine.model.User;
import com.vending.machine.repository.ProductRepository;
import com.vending.machine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private static final Set<Integer> VALID_COINS = Set.of(5, 10, 20, 50, 100);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String deposit(DepositRequest request, String username) {
        if (!VALID_COINS.contains(request.getCoin())) {
            throw new GlobalException("Invalid coin: must be one of 5, 10, 20, 50, 100.");
        }

        User user = getUser(username);
        user.setDeposit(user.getDeposit() + request.getCoin());
        userRepository.save(user);

        return "Deposited " + request.getCoin() + " successfully. New balance: " + user.getDeposit();
    }

    @Override
    public String buy(BuyRequest request, String username) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new GlobalException("Product not found"));

        if (request.getQuantity() <= 0) {
            throw new GlobalException("Quantity must be greater than zero.");
        }

        if (product.getAmountAvailable() < request.getQuantity()) {
            throw new GlobalException("Not enough product in stock.");
        }

        int totalCost = product.getCost().intValue() * request.getQuantity();
        User user = getUser(username);

        if (user.getDeposit() < totalCost) {
            throw new GlobalException("Insufficient deposit.");
        }

        // Perform transaction
        user.setDeposit(user.getDeposit() - totalCost);
        product.setAmountAvailable(product.getAmountAvailable() - request.getQuantity());
        userRepository.save(user);
        productRepository.save(product);

        return "Purchased " + request.getQuantity() + " of " + product.getProductName() +
                ". Remaining deposit: " + user.getDeposit();
    }

    @Override
    public String resetDeposit(String username) {
        User user = getUser(username);
        int deposit = user.getDeposit();
        user.setDeposit(0);
        userRepository.save(user);

        return "Deposit reset. Change returned: " + formatChange(deposit);
    }

    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new GlobalException("User not found"));
    }

    private String formatChange(int deposit) {
        int[] coins = {100, 50, 20, 10, 5};
        Map<Integer, Integer> changeMap = new LinkedHashMap<>();

        for (int coin : coins) {
            int count = deposit / coin;
            if (count > 0) {
                changeMap.put(coin, count);
                deposit %= coin;
            }
        }

        StringBuilder result = new StringBuilder();
        changeMap.forEach((coin, count) -> result.append(count).append(" x ").append(coin).append("c, "));
        return result.length() > 0 ? result.substring(0, result.length() - 2) : "No change";
    }
}