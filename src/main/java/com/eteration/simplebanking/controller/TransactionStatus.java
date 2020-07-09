package com.eteration.simplebanking.controller;

import java.util.UUID;

// This class is a place holder you can change the complete implementation

public class TransactionStatus {

	private String status;
	private UUID approvalCode;

	public String getStatus() {
		return status;
	}

	public TransactionStatus(String status, UUID uid) {
		this.status = status;
		this.approvalCode = uid;
	}

	public UUID getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(UUID approvalCode) {
		this.approvalCode = approvalCode;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
