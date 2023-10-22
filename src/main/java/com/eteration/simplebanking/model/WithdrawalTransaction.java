package com.eteration.simplebanking.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "withdrawal_transaction")
@JsonTypeName("WithdrawalTransaction")
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    @JsonCreator
    public WithdrawalTransaction(@JsonProperty("date") Date date , @JsonProperty("amount")double amount , @JsonProperty("account")Account account , @JsonProperty("approvalCode") UUID approvalCode , @JsonProperty("type")String type) {
        super(date , amount , account , approvalCode , type);
    }

    public WithdrawalTransaction() {

    }
}


