package com.eteration.simplebanking.dataAccess.abstracts;

import com.eteration.simplebanking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDao extends JpaRepository<Transaction , Integer> {
}
