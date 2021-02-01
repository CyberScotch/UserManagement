package com.usermanagement.crudoperations.controller;

import com.usermanagement.crudoperations.model.PersonResponse;
import com.usermanagement.crudoperations.service.PersonService;
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

    //To add a user without duplicate username, email_id or Phone_no
    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public HttpStatus insertPerson(@RequestBody Person person) {
        return personService.addPerson(person) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }

    //updating using ID of user
    @RequestMapping(value = "/persons", method = RequestMethod.PUT)
    public HttpStatus updatePerson(@RequestParam(value="userId") Integer userId, @RequestBody Person person) {
        return personService.updatePerson(userId, person) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
    }

    //deleting using ID of user
    @RequestMapping(value = "/persons", method = RequestMethod.DELETE)
    public HttpStatus deletePerson(@RequestParam Integer userId) {
        return personService.deletePerson(userId) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
    }

    //getting specific fields of user
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public PersonResponse getPerson(@RequestParam(value="userId") Integer userId) {
        return personService.getPerson(userId);
    }

}
