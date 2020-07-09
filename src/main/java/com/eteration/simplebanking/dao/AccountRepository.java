package com.eteration.simplebanking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eteration.simplebanking.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByaccountNumber(String accountNumber);

}
