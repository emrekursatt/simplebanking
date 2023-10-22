package com.eteration.simplebanking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "bill_payment_transaction")
@JsonTypeName("BillPaymentTransaction")
public class BillPaymentTransaction extends Transaction {

    @Column(name = "payee")
    String payee;
    public BillPaymentTransaction(double amount){
        super(amount);
    }

    public BillPaymentTransaction(String payee ,double amount) {
        super(amount);
        this.payee = payee;
    }

    public BillPaymentTransaction(double amount , String payee) {
        super(amount);
        this.payee = payee;
    }

    public BillPaymentTransaction(@JsonProperty("approvalCode")Date date , @JsonProperty("approvalCode")double amount , @JsonProperty("approvalCode")Account account , @JsonProperty("approvalCode")UUID approvalCode , @JsonProperty("approvalCode")String type , String payee) {
        super(date , amount , account , approvalCode , type);
        this.payee = payee;
    }

    public BillPaymentTransaction(String payee) {
        this.payee = payee;
    }

    public BillPaymentTransaction() {

    }
}
