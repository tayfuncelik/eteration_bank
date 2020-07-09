package com.eteration.simplebanking.dto;

public class AccountDto {

	private String owner;
	private String accountNumber;

	public AccountDto() {
		// TODO Auto-generated constructor stub
	}

	public AccountDto(String owner, String accountNumber) {
		super();
		this.owner = owner;
		this.accountNumber = accountNumber;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
