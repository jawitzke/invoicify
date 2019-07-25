package com.ally.invoicify.models;

public class Alert {

    private Company client;
    private String description;
    private String message;

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

}