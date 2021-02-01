package com.usermanagement.crudoperations.repository;

import com.usermanagement.crudoperations.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    public Person findByEmailId(String emailID);
    public Person findByPhoneNumber(String phoneNumber);
    public Person findByUserName(String userName);
    //public Person findById(Integer id);
}

