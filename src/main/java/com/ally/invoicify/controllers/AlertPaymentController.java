package com.ally.invoicify.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.LocalDate;

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
            Alert alert = new Alert();
            LocalDate localNow = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dueNow = billingDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate compareDue = dueNow.minusDays(2);
            // if the current date is past the billing date
            if ((now.compareTo(billingDate) > 0) && (billingRecord.getStatus().equals("Unpaid"))) {
                
                alert.setClient(billingRecord.getClient());
                alert.setDescription(billingRecord.getDescription());
                alert.setMessage("No payment recorded since due date of " + billingRecord.getDueDate() + ".");
                alert.setOverdue(true);
                alertList.add(alert);
            } else if((compareDue.isBefore(localNow)) && billingRecord.getStatus().equals("Unpaid")){
                // add if to see if within two days of  billDate
                alert.setClient(billingRecord.getClient());
                alert.setDescription(billingRecord.getDescription());
                alert.setMessage("A Payment will be due soon on the date of " + billingRecord.getDueDate() + ".");
                alert.setOverdue(false);
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