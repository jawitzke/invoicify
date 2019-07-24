package com.ally.invoicify.models;

import javax.persistence.Entity;

@Entity
public class FlatFeeBillingRecord extends BillingRecord {

	private double amount;
	
	public FlatFeeBillingRecord() {}
	
	public FlatFeeBillingRecord(double amount, String description,  Company client, User createdBy, String status, String dueDate) {
		super(description, client, createdBy, status, dueDate );
		this.amount = amount;
	}
	
	@Override
	public double getTotal() {
		return amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
