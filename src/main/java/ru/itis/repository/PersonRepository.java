package ru.itis.repository;

import ru.itis.model.Person;
import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    List<Person> findAll();
    Optional<Person> findById(Long id);
    void save(Person person);
    void delete(Long id);
}
