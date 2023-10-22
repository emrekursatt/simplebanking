package com.eteration.simplebanking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "phone_bill_payment_transaction")
@JsonTypeName("PhoneBillPaymentTransaction")
public class PhoneBillPaymentTransaction extends BillPaymentTransaction{

    @Column(name = "phone_number")
    private String phoneNumber;

    public PhoneBillPaymentTransaction(@JsonProperty("payee") String payee ,  @JsonProperty("phoneNumber") String phoneNumber,@JsonProperty("amount")  double amount) {
        super(payee , amount);
        this.phoneNumber = phoneNumber;
    }

    public PhoneBillPaymentTransaction() {

    }

    public PhoneBillPaymentTransaction(String payee , String phoneNumber) {
        super(payee);
        this.phoneNumber = phoneNumber;
    }

    public PhoneBillPaymentTransaction(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneBillPaymentTransaction(double amount , String phoneNumber) {
        super(amount);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
