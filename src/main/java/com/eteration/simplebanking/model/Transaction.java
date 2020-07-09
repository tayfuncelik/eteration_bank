package com.eteration.simplebanking.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "OPERATION", discriminatorType = DiscriminatorType.STRING)
public abstract class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Date date = new Date();
	private Double amount;

	@Column(columnDefinition = "BINARY(32)")
	private UUID approvalCode = UUID.randomUUID();

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;

	@Column(name = "OPERATION", insertable = false, updatable = false)
	private String type;

	public Transaction() {
	}

	public Transaction(Date date) {
		this.date = date;
	}

	public Transaction(Double amount) {
		this.amount = amount;
	}

	public Transaction(Date date, Double amount) {
		this.date = date;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setApprovalCode(UUID approvalCode) {
		this.approvalCode = approvalCode;
	}

	public UUID getApprovalCode() {
		return approvalCode;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public String getType() {
		return type;
	}
}
