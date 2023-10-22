package com.eteration.simplebanking.service.abstracts;


import com.eteration.simplebanking.exception.AccountNotFoundException;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.exception.UserAlreadyExistsException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;

public interface AccountService {


    Account findAccount(String accountNumber);

    Transaction credit(String accountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException;

    Transaction debit(String accountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException;

    Account insert(Account account) throws UserAlreadyExistsException;
}
