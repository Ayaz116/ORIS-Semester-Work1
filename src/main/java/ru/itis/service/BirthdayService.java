package ru.itis.service;

import ru.itis.model.Birthday;

import java.util.List;

public interface BirthdayService {
    List<Birthday> getAllBirthdays(Long userId);
    void addBirthday(Birthday birthday);
    void deleteBirthday(Long id, Long userId);
    List<Birthday> getUpcomingBirthdays(Long userId);

}
