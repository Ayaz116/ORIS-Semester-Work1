package ru.itis.repository;

import ru.itis.model.Birthday;

import java.time.LocalDate;
import java.util.List;

public interface BirthdayRepository {
    List<Birthday> findAllByUser(Long userId);
    void save(Birthday birthday);
    void deleteByIdAndUser(Long id, Long userId);
    List<Birthday> findBirthdaysBetweenDates(LocalDate startDate, LocalDate endDate, Long userId);

}
