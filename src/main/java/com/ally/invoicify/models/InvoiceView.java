package com.ally.invoicify.models;

public class InvoiceView {
	
	private String invoiceDescription;
	private long[] recordIds;
	private String dueDate;

	public InvoiceView(){
		
	}
	
	public InvoiceView(String invoiceDescription, long[] recordIds, String dueDate) {
		this.invoiceDescription = invoiceDescription;
		this.recordIds = recordIds;
		this.dueDate=dueDate;
	}

	public String getInvoiceDescription() {
		return invoiceDescription;
	}

	public long[] getRecordIds() {
		return recordIds;
	}

	public String getDueDate(){
		return dueDate;
	}
	
	
	
}
