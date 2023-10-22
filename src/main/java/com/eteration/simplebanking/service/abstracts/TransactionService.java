package com.eteration.simplebanking.service.abstracts;

import com.eteration.simplebanking.model.Transaction;

public interface TransactionService {

    Transaction insert(Transaction depositTransaction);
}
