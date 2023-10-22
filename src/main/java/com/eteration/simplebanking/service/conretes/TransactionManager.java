package com.eteration.simplebanking.service.conretes;

import com.eteration.simplebanking.dataAccess.abstracts.TransactionDao;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.service.abstracts.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionManager implements TransactionService {

    TransactionDao transactionDao;

    @Autowired
    public TransactionManager(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public Transaction insert(Transaction transaction) {
        return transactionDao.save(transaction);
    }

}
