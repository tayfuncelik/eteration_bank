package com.eteration.simplebanking.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity
@DiscriminatorValue(value = "WithdrawalTransaction")
public class WithdrawalTransaction extends Transaction {

	public WithdrawalTransaction() {
		// TODO Auto-generated constructor stub
	}
	public WithdrawalTransaction(Date date) {
		super(date);
	}

	public WithdrawalTransaction(Double amount) {
		super(amount);
	}

	public WithdrawalTransaction(Integer amount) {
		super(new Date(), Double.valueOf(amount));
	}
}
