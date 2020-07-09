package com.eteration.simplebanking.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eteration.simplebanking.dao.DepositTransactionRepository;
import com.eteration.simplebanking.dao.WithDrawalRepository;
import com.eteration.simplebanking.dto.AccountDto;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;

import io.swagger.annotations.ApiParam;

// This class is a place holder you can change the complete implementation
@RestController
@RequestMapping("/account/v1")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private DepositTransactionRepository depositRepo;

	@Autowired
	private WithDrawalRepository withDrawalRepo;

	@PostMapping("/credit/{accountNumber}")
	public ResponseEntity<TransactionStatus> creditByAccountNumber(
			@PathVariable(value = "accountNumber") String accountNumber, @RequestBody Amount amount) {

		Account account = this.accountService.findAccount(accountNumber);
		account.deposit(amount.getAmount());
		DepositTransaction t = new DepositTransaction(amount.getAmount());
		t.setAccount(account);
		depositRepo.save(t);
		return new ResponseEntity<>(new TransactionStatus("OK", t.getApprovalCode()), HttpStatus.OK);
	}

	@PostMapping("/debit/{accountNumber}")
	public ResponseEntity<TransactionStatus> debitByAccountNumber(
			@PathVariable(value = "accountNumber") String accountNumber, @RequestBody Amount amount)
			throws InsufficientBalanceException {

		Account account = this.accountService.findAccount(accountNumber);
		account.withdraw(amount.getAmount());
		
		WithdrawalTransaction t = new WithdrawalTransaction(amount.getAmount());
		t.setAccount(account);
		withDrawalRepo.save(t);
		return new ResponseEntity<>(new TransactionStatus("OK", t.getApprovalCode()), HttpStatus.OK);
	}

	public ResponseEntity<TransactionStatus> credit(String accountNumber, Transaction transaction) {

		Account account = this.accountService.findAccount(accountNumber);
		account.deposit(transaction.getAmount());

		return new ResponseEntity<>(new TransactionStatus("OK", UUID.randomUUID()), HttpStatus.OK);
	}

	public ResponseEntity<TransactionStatus> debit(String accountNumber, Transaction transaction)
			throws InsufficientBalanceException {

		Account account = this.accountService.findAccount(accountNumber);
		account.withdraw(transaction.getAmount());
		return new ResponseEntity<>(new TransactionStatus("OK", UUID.randomUUID()), HttpStatus.OK);
	}

	@GetMapping("/get/{accountNumber}")
	public ResponseEntity<Account> getAccount(@PathVariable(value = "accountNumber") String accountNumber) {
		Account account = this.accountService.findAccount(accountNumber);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Account> addAccount(@ApiParam("Company object ") @RequestBody AccountDto accountDto) {
		this.accountService.addAccount(accountDto.getOwner(), accountDto.getAccountNumber());
		Account account = this.accountService.findAccount(accountDto.getAccountNumber());
		return new ResponseEntity<>(account, HttpStatus.OK);
	}
}