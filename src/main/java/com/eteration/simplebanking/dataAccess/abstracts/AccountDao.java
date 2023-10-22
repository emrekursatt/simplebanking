package com.eteration.simplebanking.dataAccess.abstracts;

import com.eteration.simplebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {
    Account findByAccountNumber(String accountNumber);

}
