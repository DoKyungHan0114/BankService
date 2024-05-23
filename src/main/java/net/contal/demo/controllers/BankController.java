package net.contal.demo.controllers;

import net.contal.demo.modal.CustomerAccount;
import net.contal.demo.services.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/banks")
public class BankController {
    private final Logger logger = LoggerFactory.getLogger(BankController.class);
    private final BankService dataService;

    @Autowired
    public BankController(BankService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createBankAccount(@RequestBody CustomerAccount account) {
        try {
            logger.info("Creating bank account for: {}", account);
            String accountNumber = dataService.createAnAccount(account);
            return ResponseEntity.ok(Long.parseLong(accountNumber));
        } catch (Exception e) {
            logger.error("Error creating bank account: {}", e.toString());
            return ResponseEntity.badRequest().body(-1L);
        }
    }

    @PostMapping("/transaction")
    public ResponseEntity<String> addTransaction(@RequestParam("accountNumber") int accountNumber, @RequestParam("amount") Double amount) {
        try {
            logger.info("Bank Account number is :{}, Transaction Amount {}", accountNumber, amount);
            boolean result = dataService.addTransactions(accountNumber, amount);
            if (result) {
                return ResponseEntity.ok("Transaction successfully added");
            } else {
                return ResponseEntity.badRequest().body("Failed to add transaction");
            }
        } catch (Exception e) {
            logger.error("Error adding transaction: {}", e.toString());
            return ResponseEntity.badRequest().body("Failed to add transaction due to an error");
        }
    }

    @PostMapping("/balance")
    public ResponseEntity<Double> getBalance(@RequestParam("accountNumber") int accountNumber) {
        try {
            logger.info("Retrieving balance for account number: {}", accountNumber);
            double balance = dataService.getBalance(accountNumber);
            return ResponseEntity.ok(balance);
        } catch (Exception e) {
            logger.error("Error retrieving balance: {}", e.toString());
            return ResponseEntity.badRequest().body(0.0);
        }
    }

}
