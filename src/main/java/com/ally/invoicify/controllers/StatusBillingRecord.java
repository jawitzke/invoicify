package com.ally.invoicify.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ally.invoicify.models.BillingRecord;
import com.ally.invoicify.models.FlatFeeBillingRecord;
import com.ally.invoicify.models.User;
import com.ally.invoicify.repositories.BillingRecordRepository;

@RestController
@RequestMapping("/api/billing-record/status/")
public class StatusBillingRecord {

    @Autowired
    private BillingRecordRepository recordRepository;

    private BillingRecord billingRecord;

    @GetMapping("{recordId}")
    public String getStatus(@PathVariable long recordId, Authentication auth) {
        billingRecord = recordRepository.findOne(recordId);
        return billingRecord.getStatus();
    }

    @PutMapping("{recordId}")
    public BillingRecord updateStatus(@PathVariable long recordId, @RequestHeader String status) {
        billingRecord = recordRepository.findOne(recordId);
        billingRecord.setStatus(status);
        recordRepository.save(billingRecord);
        return billingRecord;
    }

}