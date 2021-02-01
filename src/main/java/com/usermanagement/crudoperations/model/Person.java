package com.usermanagement.crudoperations.model;

import javax.persistence.*;

@Entity
@Table(name="person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="user_name", unique=true)
    private String userName;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;

    //validate phone number
    /*@Pattern("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$")*/
    @Column(name="mobile_number", unique=true)
    private String phoneNumber;
    //validate email ID
    @Column(name="email_id" , unique=true)
    private String emailId;
    @Column(name="address_1")
    private String address1;
    @Column(name="address_2")
    private String address2;

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getAddress1() {
        return address1;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Override
    public String toString()
    {
        return "Person{" + "id=" + id + ", username='" + userName + '\''+ ", firstName='" + firstName + '\'' + ", lastName='" + lastName
                + '\'' + "emailID= "+ emailId + "phoneNumber= " + phoneNumber + "address1= "+  address1 + "address2= "+ address2 +'}';
    }
}
