package com.eteration.simplebanking.service.conretes;

import com.eteration.simplebanking.dataAccess.abstracts.AccountDao;
import com.eteration.simplebanking.dataAccess.abstracts.TransactionDao;
import com.eteration.simplebanking.exception.AccountNotFoundException;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.exception.UserAlreadyExistsException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.service.abstracts.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManager implements AccountService {
    private final AccountDao accountDao;

    private final TransactionDao transactionDao;
    @Autowired
    public AccountManager(AccountDao accountDao , TransactionDao transactionDao) {
        this.accountDao = accountDao;
        this.transactionDao = transactionDao;
    }


    @Override
    public Account findAccount(String accountNumber) {
        return accountDao.findByAccountNumber(accountNumber);

    }

    @Override
    public Account insert(Account account) throws UserAlreadyExistsException{
        Account existingAccount = findAccount(account.getAccountNumber().toString());
        if(existingAccount != null)
            throw new UserAlreadyExistsException("User already exists with account number : " + account.getAccountNumber());
        return accountDao.save(account);
    }

    @Override
    public Transaction credit(String accountNumber , double amount) throws AccountNotFoundException, InsufficientBalanceException {
        Account account = findAccount(accountNumber);
        if(account == null)
            throw new AccountNotFoundException("Account not found with account number : " + account.getAccountNumber());
        Transaction depositTransaction = new DepositTransaction(amount);
        account.post(depositTransaction);
        accountDao.save(account);
        depositTransaction.setAccount(account);
        transactionDao.save(depositTransaction);
        return account.getTransactions().get(account.getTransactions().size()-1);
    }

    @Override
    public Transaction debit(String accountNumber , double amount) throws AccountNotFoundException, InsufficientBalanceException {
        Account account = findAccount(accountNumber);
        if(account == null)
            throw new AccountNotFoundException("Account not found with account number : " + account.getAccountNumber());

        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance for account number " + accountNumber);
        }
        Transaction withdrawalTransaction = new WithdrawalTransaction(amount);
        account.post(withdrawalTransaction);
        accountDao.save(account);
        withdrawalTransaction.setAccount(account);
        transactionDao.save(withdrawalTransaction);
        return account.getTransactions().get(account.getTransactions().size()-1);
    }

}
