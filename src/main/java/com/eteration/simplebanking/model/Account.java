package com.eteration.simplebanking.model;


import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "owner")
    private  String owner;
    @Column(name = "account_number")
    private  String accountNumber;
    @Column(name = "balance")
    private  double balance;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "account")
    @JsonIgnoreProperties({"account"})
    private  List<Transaction> transactions;



    public Account(String owner , String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<Transaction>();
    }

    public Account(Integer id , String owner , String accountNumber , double balance , List<Transaction> transactions) {
        this.id = id;
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = transactions;
    }
    public Account() {

    }

    public void post(Transaction transactions) throws InsufficientBalanceException {
        if(transactions == null) return;

        if(transactions instanceof DepositTransaction){
            debit(transactions.getAmount());
        } else if (transactions instanceof  WithdrawalTransaction) {
            withdraw(transactions.getAmount());
        }else if (transactions instanceof  PhoneBillPaymentTransaction) {
            withdraw(transactions.getAmount());
        }
        this.transactions.add(transactions);
    }

    public void credit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void debit(double amount)  {
        if (amount > 0 ) {
            balance += amount;
        }
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            throw   new InsufficientBalanceException("Invalid withdrawal amount");
        }
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
