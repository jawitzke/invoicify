package com.ally.invoicify.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ally.invoicify.models.Alert;
import com.ally.invoicify.models.BillingRecord;
import com.ally.invoicify.repositories.BillingRecordRepository;

@RestController
@RequestMapping("/api/alert")
public class AlertPaymentController {

    @Autowired
    private BillingRecordRepository recordRepository;

    @GetMapping
    public List<Alert> getAlerts() throws ParseException {
        List<BillingRecord> billingRecords = recordRepository.findAll();
        Date now = new Date();
        List<Alert> alertList = new ArrayList<>();
        for (BillingRecord billingRecord : billingRecords) {
            Date billingDate = stringToDate(billingRecord.getDueDate());
            // if the current date is past the billing date
            if ((now.compareTo(billingDate) > 0) && (billingRecord.getStatus().equals("unpaid"))) {
                Alert alert = new Alert();
                alert.setClient(billingRecord.getClient());
                alert.setDescription(billingRecord.getDescription());
                alert.setMessage("No payment recorded since due date of " + billingRecord.getDueDate() + ".");
                alertList.add(alert);
            }
        }
        return alertList;
    }

    public Date stringToDate(String dateString) throws ParseException {
        Date date = new SimpleDateFormat("MM/dd/yyyy").parse(dateString);
        return date;
    }

}