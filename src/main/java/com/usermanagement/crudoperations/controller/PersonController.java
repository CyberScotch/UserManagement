package com.usermanagement.crudoperations.controller;

import com.usermanagement.crudoperations.model.GeneralResponse;
import com.usermanagement.crudoperations.model.PersonResponse;
import com.usermanagement.crudoperations.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.usermanagement.crudoperations.model.Person;
import com.usermanagement.crudoperations.repository.PersonRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RestController
@CrossOrigin("*")
public class PersonController {

    @Autowired
    PersonService personService;

   Logger logger= LoggerFactory.getLogger(PersonController.class);

    //To add a user without duplicate username, email_id or Phone_no
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Person> showUsers() {
        logger.trace("Showing ALL Users");
        return personService.getAllPersons();
    }

    //To add a user without duplicate username, email_id or Phone_no
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public GeneralResponse insertPerson(@RequestBody Person person) {
       return personService.addPerson(person);
    }

    //getting specific fields of user
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public PersonResponse getPerson(@RequestParam(value="userId") Integer userId) {
        return personService.getPerson(userId);
    }

    //updating using ID of user
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public GeneralResponse updatePerson(@RequestParam(value="userId") Integer userId, @RequestBody Person person) {
        return personService.updatePerson(userId, person);
    }

    //deleting using ID of user
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public GeneralResponse deletePerson(@RequestParam Integer userId) {
        return personService.deletePerson(userId);
    }



}
