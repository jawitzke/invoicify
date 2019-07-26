package com.ally.invoicify.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.Authentication;


import com.ally.invoicify.models.BillingRecord;
import com.ally.invoicify.repositories.BillingRecordRepository;
import com.ally.invoicify.repositories.CompanyRepository;

@RestController
@RequestMapping("/api/billing-record")
public class BillingRecordController {
	
	private BillingRecordRepository recordRepository;

	public BillingRecordController(BillingRecordRepository recordRepository, CompanyRepository companyRepository) {
		this.recordRepository = recordRepository;
	}

	@GetMapping
	public List<BillingRecord> list() {
		return recordRepository.findAll();
	}

	@DeleteMapping("{recordId}")
	public String voidRecord(@PathVariable long recordId, Authentication auth){
		BillingRecord billingRecord = recordRepository.findOne(recordId);
		recordRepository.delete(billingRecord);
		return "Successfully deleted billing record with id: " + recordId;
	}
	
}