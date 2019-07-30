package com.ally.invoicify.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;


import com.ally.invoicify.models.BillingRecord;
import com.ally.invoicify.repositories.BillingRecordRepository;
import com.ally.invoicify.repositories.CompanyRepository;
import com.ally.invoicify.models.Company;


@RestController
@RequestMapping("/api/billing-record")
public class BillingRecordController {

	private BillingRecordRepository recordRepository;

	@Autowired
	private CompanyRepository companyRepository;

	public BillingRecordController(BillingRecordRepository recordRepository, CompanyRepository companyRepository) {
		this.recordRepository = recordRepository;
	}

	@GetMapping
	public List<BillingRecord> list() {
		return recordRepository.findAll();
	}

	@GetMapping("/company/{clientId}")
	public List<BillingRecord> getCompanyRecords(@PathVariable long clientId) {
		List<BillingRecord> billingRecords = recordRepository.findAll();
		List<BillingRecord> companyRecords = new ArrayList<BillingRecord>();
		Company company = companyRepository.findOne(clientId);
		for (BillingRecord billingRecord : billingRecords) {
			if (billingRecord.getClient().getId().equals(clientId)) {
				companyRecords.add(billingRecord);
			}
		}
		return companyRecords;
	}

	@DeleteMapping("{recordId}")
	public BillingRecord voidRecord(@PathVariable long recordId, Authentication auth) {
		BillingRecord billingRecord = recordRepository.findOne(recordId);
		recordRepository.delete(billingRecord);
		return billingRecord;
	}

}