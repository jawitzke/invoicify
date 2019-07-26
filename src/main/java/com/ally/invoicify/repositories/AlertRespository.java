package com.ally.invoicify.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ally.invoicify.models.Alert;

public interface AlertRespository extends JpaRepository<Alert, Long> {

}
