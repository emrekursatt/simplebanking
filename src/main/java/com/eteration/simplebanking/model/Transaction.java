package com.eteration.simplebanking.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DepositTransaction.class, name = "DepositTransaction"),
        @JsonSubTypes.Type(value = WithdrawalTransaction.class, name = "WithdrawalTransaction"),
        @JsonSubTypes.Type(value = BillPaymentTransaction.class, name = "BillPaymentTransaction"),
        @JsonSubTypes.Type(value = BillPaymentTransaction.class, name = "PhoneBillPaymentTransaction"),
})
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    @Column(name = "id")
    private int id;


    @Column(name = "date")
	private Date date;

    @Column(name = "amount")
    private double amount;
    @ManyToOne()
    @JoinColumn(name = "account_id" , nullable = false)
    private Account account;

    @Type(type = "uuid-char")
    @Column(name = "approval_code")
    private UUID approvalCode;

    @Column(name = "type")
    private String type;

    public Transaction(double amount){
        this.amount = amount;
        this.date = new Date();
        this.approvalCode = UUID.randomUUID();
        this.type = this.getClass().getSimpleName();
    }

    public Transaction(Date date , double amount , Account account , UUID approvalCode , String type) {
        this.date = date;
        this.amount = amount;
        this.account = account;
        this.approvalCode = UUID.randomUUID();
        this.type = this.getClass().getSimpleName();
    }

    public Transaction() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public UUID getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(UUID approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                '}';
    }
}
