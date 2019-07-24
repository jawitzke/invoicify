package com.ally.invoicify.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public abstract class BillingRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private User createdBy;
	
	private String description;
	
	@JsonManagedReference
	@OneToOne(mappedBy="billingRecord")
	private InvoiceLineItem lineItem;
	
	@ManyToOne
	private Company client;

	private String status;
	private String dueDate;
	
	public BillingRecord() {}
	
	public BillingRecord(String description, Company client, User createdBy, String status, String dueDate) {
		this();
		this.description = description;
		this.client = client;
		this.setCreatedBy(createdBy);
		this.status=status;
		this.dueDate=dueDate;

	}
	
	public abstract double getTotal();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InvoiceLineItem getLineItem() {
		return lineItem;
	}

	public void setLineItem(InvoiceLineItem lineItem) {
		this.lineItem = lineItem;
	}

	public Company getClient() {
		return client;
	}

	public void setClient(Company client) {
		this.client = client;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public String getDueDate(){
		return dueDate;
	}

	public void setDueDate(String dueDate){
		this.dueDate=dueDate;
	}
	
}
