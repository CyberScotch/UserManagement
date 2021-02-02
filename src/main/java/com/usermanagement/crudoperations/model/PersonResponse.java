package com.usermanagement.crudoperations.model;

import javax.persistence.Column;

public class PersonResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailId;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void map(Person person)
    {
        this.firstName=person.getFirstName();
        this.lastName=person.getLastName();
        this.phoneNumber=person.getPhoneNumber();
        this.emailId=person.getEmailId();
    }
}
