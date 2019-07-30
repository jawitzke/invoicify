package com.ally.invoicify.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ally.invoicify.models.Company;
import com.ally.invoicify.models.FlatFeeBillingRecord;
import com.ally.invoicify.models.RateBasedBillingRecord;
import com.ally.invoicify.models.User;
import com.ally.invoicify.models.UserTypes;
import com.ally.invoicify.repositories.BillingRecordRepository;
import com.ally.invoicify.repositories.CompanyRepository;
import com.ally.invoicify.repositories.UserRepository;

@Configuration
public class SeedData {

	public SeedData(BillingRecordRepository recordRepository, CompanyRepository companyRepository,
			UserRepository userRepository, PasswordEncoder encoder) {
		User admin = userRepository.save(new User("admin", encoder.encode("admin"), UserTypes.admin));

		Company ajax = companyRepository.save(new Company("AJAX Ltd."));
		Company lomax = companyRepository.save(new Company("Lomax Brothers, LLC"));

		

		recordRepository.save(new FlatFeeBillingRecord(300, "Faxes", ajax, admin, "Unpaid", "08/10/2019"));
		recordRepository.save(new FlatFeeBillingRecord(1.75, "Socks", ajax, admin, "Unpaid", "07/25/2019"));
		recordRepository.save(new FlatFeeBillingRecord(500, "Paper", lomax, admin, "Unpaid", "06/10/2019"));
		recordRepository.save(new FlatFeeBillingRecord(72.33, "Stockings", lomax, admin, "Unpaid", "09/17/2019"));
		recordRepository.save(new FlatFeeBillingRecord(142.99, "Paint", lomax, admin, "Unpaid", "10/12/2019"));

		recordRepository.save(new RateBasedBillingRecord(500, 3.5, "Legal services", ajax, admin, "Unpaid", "08/05/2019"));
		recordRepository.save(new RateBasedBillingRecord(150, 2.5, "Painting", ajax, admin, "Unpaid", "07/27/2019"));
		recordRepository.save(new RateBasedBillingRecord(100, 4.25, "House cleaning", ajax, admin , "Unpaid","09/22/2019"));
		recordRepository.save(new RateBasedBillingRecord(700, 8, "Palm reading", lomax, admin, "Unpaid", "07/26/2019"));
		recordRepository.save(new RateBasedBillingRecord(1.57, 25, "Show shining", lomax, admin, "Unpaid","09/12/2019"));
	}
}