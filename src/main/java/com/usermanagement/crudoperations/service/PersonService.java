package com.usermanagement.crudoperations.service;

import java.util.List;
import java.util.Optional;

import com.usermanagement.crudoperations.model.Person;
import com.usermanagement.crudoperations.model.PersonResponse;
import com.usermanagement.crudoperations.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    //add a user in DB
    @Transactional
    public boolean addPerson(Person person) {
        //code for Validation of already existing user
        String emailId=person.getEmailId();
        String userName=person.getUserName();
        String phoneNumber=person.getPhoneNumber();

        if((personRepository.findByEmailId(emailId)==null)
                                 &&
            personRepository.findByPhoneNumber(phoneNumber)==null
                                 &&
            personRepository.findByUserName(userName)==null)
            {
                personRepository.save(person);
                return true;
            }
        else
            return false;
    }

    //update a user in DB if ID already exists
    @Transactional
    public boolean updatePerson(Integer userId, Person person) {

        Optional<Person> temp=personRepository.findById(userId);
        if(temp.isPresent())
        {
            if((personRepository.findByEmailId(person.getEmailId())==null)
                    &&
                    personRepository.findByPhoneNumber(person.getPhoneNumber())==null
                    &&
                    personRepository.findByUserName(person.getUserName())==null)
            {
                person.setId(userId);
                personRepository.save(person);
                return true;
            }

        }

         return false;

    }

    //deleting a user info
    @Transactional
    public boolean deletePerson(Integer userId) {
        Optional<Person> temp=personRepository.findById(userId);

        if(temp.isPresent())
            {
                Person callPerson=(Person)temp.get();
                personRepository.delete(callPerson);
                return true;
            }
        else
            return false;
    }

    //get all users from DB
    @Transactional
    public List<Person> getAllPersons() {
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
