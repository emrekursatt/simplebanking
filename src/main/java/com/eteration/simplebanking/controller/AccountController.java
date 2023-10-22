package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.core.TransactionStatus;
import com.eteration.simplebanking.dataAccess.abstracts.TransactionDao;
import com.eteration.simplebanking.exception.AccountNotFoundException;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.exception.UserAlreadyExistsException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.service.conretes.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account/v1")
public class AccountController {

    AccountManager accountService;

    @Autowired
    public AccountController(AccountManager accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/credit/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        Account account = accountService.findAccount(accountNumber);
        if(account == null)
            return new ResponseEntity<Account>(account , HttpStatus.NO_CONTENT);
        else
        return new ResponseEntity<Account>(account , HttpStatus.OK);
    }



    @PostMapping("/insert")
    public ResponseEntity<Account> insertAccount(@RequestBody Account account) throws AccountNotFoundException, UserAlreadyExistsException {
        Account account2 = accountService.insert(account);
        if (account2 == null) {
            return new ResponseEntity<Account>(account2,HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<Account>(account2, HttpStatus.OK);
        }
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction depositTransaction) throws AccountNotFoundException, InsufficientBalanceException {
        if (depositTransaction == null || depositTransaction.getAmount() <= 0) {
            return new ResponseEntity<TransactionStatus>(HttpStatus.BAD_REQUEST);
        }
        return getTransactionStatusResponseEntity(accountNumber , depositTransaction);

    }

    private ResponseEntity<TransactionStatus> getTransactionStatusResponseEntity(String accountNumber , Transaction transactions) throws AccountNotFoundException, InsufficientBalanceException {
        Transaction transaction;
        if (transactions.getType().equals("DepositTransaction"))
            transaction = accountService.credit(accountNumber , transactions.getAmount());
        else transaction = accountService.debit(accountNumber , transactions.getAmount());

        if (transaction != null) {
            TransactionStatus response = new TransactionStatus(HttpStatus.OK.name(), transaction.getApprovalCode());
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<TransactionStatus>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction withdrawalTransaction) throws AccountNotFoundException, InsufficientBalanceException {
        return getTransactionStatusResponseEntity(accountNumber , withdrawalTransaction);
    }


}