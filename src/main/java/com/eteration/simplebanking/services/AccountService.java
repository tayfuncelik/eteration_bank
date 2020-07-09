package com.eteration.simplebanking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eteration.simplebanking.dao.AccountRepository;
import com.eteration.simplebanking.model.Account;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account findAccount(String accountNumber) {
		return accountRepository.findByaccountNumber(accountNumber);
	}

	public void addAccount(String owner, String accountNumber) {
		Account account =new Account(owner, accountNumber);
		accountRepository.save(account);
	}
	public void updateAccount(Account account) {
		accountRepository.save(account);
	}

}
