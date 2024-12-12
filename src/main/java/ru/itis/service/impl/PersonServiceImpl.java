package ru.itis.service.impl;

import ru.itis.model.Person;
import ru.itis.repository.PersonRepository;
import ru.itis.service.PersonService;

import java.util.List;
import java.util.Optional;

public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @Override
    public void addPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.delete(id);
    }

    @Override
    public Person getPersonById(Long id) {
        Optional<Person> p = personRepository.findById(id);
        return p.orElseThrow(() -> new IllegalArgumentException("Person not found"));
    }
}
