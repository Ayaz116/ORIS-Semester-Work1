package ru.itis.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.itis.model.Birthday;
import ru.itis.repository.BirthdayRepository;
import ru.itis.service.BirthdayService;

import java.util.List;

@Slf4j
public class BirthdayServiceImpl implements BirthdayService {

    private final BirthdayRepository birthdayRepository;

    public BirthdayServiceImpl(BirthdayRepository birthdayRepository) {
        this.birthdayRepository = birthdayRepository;
    }

    @Override
    public List<Birthday> getAllBirthdays() {
        log.info("Fetching all birthdays");
        return birthdayRepository.findAll();
    }

    @Override
    public void addBirthday(Birthday birthday) {
        log.info("Adding new birthday: {}", birthday);
        birthdayRepository.save(birthday);
    }

    @Override
    public void deleteBirthday(Integer id) {
        log.info("Deleting birthday with id {}", id);
        birthdayRepository.delete(id);
    }
}
