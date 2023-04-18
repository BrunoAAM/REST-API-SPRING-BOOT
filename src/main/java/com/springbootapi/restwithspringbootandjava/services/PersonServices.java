package com.springbootapi.restwithspringbootandjava.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapi.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import com.springbootapi.restwithspringbootandjava.model.Person;
import com.springbootapi.restwithspringbootandjava.repositories.PersonRepository;
@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());


    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {

        logger.info("Finding all people!");

        return repository.findAll();
    }

    public Person findById(Long id) {

        logger.info("Finding one person!");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }

    public Person update(Person person) {
        logger.info("Updating one person!");

        // Tenta recuperar a pessoa (para confirmar que ela existe); caso contrário, lança exceção.
        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public Person create(Person person) {
        logger.info("Creating one person!");

        return repository.save(person);
    }
}
