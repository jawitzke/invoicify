package com.ally.invoicify.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Alert {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

    private Company client;
    private String description;
    private String message;
    private Boolean overdue;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public Company getClient() {
        return client;
    }

    public void setClient(Company client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message=message;
    }

    public Boolean isOverdue(){
        return overdue;
    }

    public void setOverdue(Boolean overdue){
        this.overdue=overdue;
    }

}