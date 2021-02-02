package com.usermanagement.crudoperations.service;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.usermanagement.crudoperations.controller.PersonController;
import com.usermanagement.crudoperations.model.GeneralResponse;
import com.usermanagement.crudoperations.model.Person;
import com.usermanagement.crudoperations.model.PersonResponse;
import com.usermanagement.crudoperations.repository.PersonRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    Logger logger= LoggerFactory.getLogger(PersonService.class);

    //add a user in DB
    @Transactional
    public GeneralResponse addPerson(Person person) {
        GeneralResponse gr=new GeneralResponse();
        gr.setStatus("FAILED");
        try {
            //code for Validation of already existing user
            String emailId = person.getEmailId();
            String userName = person.getUserName();
            String phoneNumber = person.getPhoneNumber();

            logger.trace("Adding person");
            //adding user if no duplicate data
            if(personRepository.findByUserName(userName) != null)
            {
                gr.setMessage("Username exists");
                logger.trace("Existing username");
            }
            else if(personRepository.findByPhoneNumber(phoneNumber) != null) {
                gr.setMessage("Phone Number exists");
                logger.trace("Existing Phone Number");
            }
            else if(personRepository.findByEmailId(emailId) != null) {
                gr.setMessage("Email Exists");
                logger.trace("Existing EmailId");
            }
            else{
                //validating phone number
                if(!Pattern.matches("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$",phoneNumber))
                {
                    gr.setMessage("Invalid PhoneNumber");
                    logger.trace(" Invalid PhoneNumber");
                    return gr;
                }
                //validating email
                if(!Pattern.matches("(?!.*\\.\\.)(^[^\\.][^@\\s]+@[^@\\s]+\\.[^@\\s\\.]+$)",emailId)) {
                    gr.setMessage("Invalid EmailId");
                    logger.trace(" Invalid EmailId");
                    return gr;
                }

                //saving user details
                personRepository.save(person);
                gr.setMessage("User saved");
                gr.setStatus("STATUS: OK");
                logger.trace("User Saved");
            }
                //returning responseObject
                return gr;
        }
        catch(Exception e)
        {
            String str=e.getMessage();
            gr.setMessage("Exception Occurred "+ str);
            logger.trace(" Exception occurred");
            return gr;
        }
    }



    //update a user in DB if ID already exists
    @Transactional
    public GeneralResponse updatePerson(Integer userId, Person person) {
        GeneralResponse gr=new GeneralResponse();
        gr.setStatus("FAILED");

        try {
            //checking if user is present
            Optional<Person> temp = personRepository.findById(userId);
            if (temp.isPresent()) {
                person.setId(userId);
                personRepository.save(person);
                gr.setMessage("User Updated");
                gr.setStatus("Status: OK");
                logger.trace(" User Updated");
                return gr;
            }
            gr.setMessage("User Not exists");
            logger.trace(" Non existing user");
            return gr;
        }
        catch(Exception e)
        {
            String str=e.getMessage();
            gr.setMessage("Exception Occurred "+ str);
            return gr;
        }
    }

    //deleting a user info
    @Transactional
    public GeneralResponse deletePerson(Integer userId) {

        GeneralResponse gr=new GeneralResponse();
        gr.setStatus("FAILED");
        try {
            //checking if person exists
            Optional<Person> temp = personRepository.findById(userId);
            if (temp.isPresent()) {
                Person callPerson = (Person) temp.get();
                personRepository.delete(callPerson);
                gr.setMessage("User Deleted");
                gr.setStatus("Status: OK");
                logger.trace(" User Deleted");
                return gr;
            }
            else
                {
                    gr.setMessage("User Not exists");
                    logger.trace(" User Not exists");
                    return gr;
                }
        }
        catch(Exception e)
        {
            String str=e.getMessage();
            gr.setMessage("Exception Occurred "+ str);
            return gr;
        }
    }

    //get all users from DB
    @Transactional
    public List<Person> getAllPersons() {
        logger.trace(" Returning all users");
        return (List<Person>) personRepository.findAll();
    }

    //deleting a user info
    @Transactional
    public PersonResponse getPerson(Integer userId) {
        Optional<Person> temp=personRepository.findById(userId);
        if(temp.isPresent())
            {
                Person callPerson=temp.get();
                PersonResponse personResponse=new PersonResponse();
                personResponse.map(callPerson);
                logger.trace("Providing user details as request");
                return personResponse;
            }
        else {
            return null;
        }
    }

    @Transactional
    public List<Person> findAllPersons()
    {
        return (List<Person>) personRepository.findAll();
    }

}
