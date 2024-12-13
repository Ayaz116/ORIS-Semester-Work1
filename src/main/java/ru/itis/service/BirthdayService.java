package ru.itis.service;

import ru.itis.model.Birthday;

import java.util.List;

public interface BirthdayService {
    List<Birthday> getAllBirthdays();
    void addBirthday(Birthday birthday);
    void deleteBirthday(Integer id);
}
