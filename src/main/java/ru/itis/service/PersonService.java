package ru.itis.service;

import ru.itis.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPeople();
    void addPerson(Person person);
    void deletePerson(Long id);
    Person getPersonById(Long id);
}
