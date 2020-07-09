package com.eteration.simplebanking.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity
@DiscriminatorValue(value="DepositTransaction")
public class DepositTransaction extends Transaction {
	
	public DepositTransaction() {
		// TODO Auto-generated constructor stub
	}

	public DepositTransaction(Date date) {
		super(date);
	}

	public DepositTransaction(Double amount) {
		super(amount);
	}

	public DepositTransaction(Integer amount) {
		super(new Date(), Double.valueOf(amount));
	}

}
