package com.example;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class Transaction implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private java.lang.String account;
	private java.lang.Double amount;
	private java.lang.String source;

	public Transaction() {
	}

	public java.lang.String getAccount() {
		return this.account;
	}

	public void setAccount(java.lang.String account) {
		this.account = account;
	}

	public java.lang.Double getAmount() {
		return this.amount;
	}

	public void setAmount(java.lang.Double amount) {
		this.amount = amount;
	}

	public java.lang.String getSource() {
		return this.source;
	}

	public void setSource(java.lang.String source) {
		this.source = source;
	}

	public Transaction(java.lang.String account, java.lang.Double amount,
			java.lang.String source) {
		this.account = account;
		this.amount = amount;
		this.source = source;
	}

}