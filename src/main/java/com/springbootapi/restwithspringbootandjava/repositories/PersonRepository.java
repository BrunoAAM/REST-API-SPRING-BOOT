package com.springbootapi.restwithspringbootandjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootapi.restwithspringbootandjava.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}