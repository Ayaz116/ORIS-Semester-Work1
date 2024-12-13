package ru.itis.repository;

import ru.itis.model.Birthday;

import java.util.List;
import java.util.Optional;

public interface BirthdayRepository {
    List<Birthday> findAll();
    Optional<Birthday> findById(Integer id);
    void save(Birthday birthday);
    void delete(Integer id);
}
