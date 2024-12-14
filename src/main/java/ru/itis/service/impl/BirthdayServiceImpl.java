package ru.itis.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.itis.model.Birthday;
import ru.itis.repository.BirthdayRepository;
import ru.itis.service.BirthdayService;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BirthdayServiceImpl implements BirthdayService {

    private final BirthdayRepository birthdayRepository;

    public BirthdayServiceImpl(BirthdayRepository birthdayRepository) {
        this.birthdayRepository = birthdayRepository;
    }

    @Override
    public List<Birthday> getAllBirthdays(Long userId) {
        log.info("Fetching all birthdays for user with id {}", userId);
        return birthdayRepository.findAllByUser(userId);
    }

    @Override
    public void addBirthday(Birthday birthday) {
        log.info("Adding new birthday: {}", birthday);
        birthdayRepository.save(birthday);
    }

    @Override
    public void deleteBirthday(Long id, Long userId) {
        log.info("Deleting birthday with id {} for user {}", id, userId);
        birthdayRepository.deleteByIdAndUser(id, userId);
    }

    @Override
    public List<Birthday> getUpcomingBirthdays(Long userId) {
        List<Birthday> allBirthdays = birthdayRepository.findAllByUser(userId);

        LocalDate today = LocalDate.now();
        return allBirthdays.stream()
                .map(birthday -> {
                    LocalDate nextBirthday = birthday.getBirthDate()
                            .withYear(today.getYear());

                    if (nextBirthday.isBefore(today) || nextBirthday.isEqual(today)) {
                        nextBirthday = nextBirthday.plusYears(1);
                    }

                    long daysToBirthday = ChronoUnit.DAYS.between(today, nextBirthday);

                    int upcomingAge = nextBirthday.getYear() - birthday.getBirthDate().getYear();

                    birthday.setDaysToBirthday((int) daysToBirthday);
                    birthday.setUpcomingAge(upcomingAge);

                    return birthday;
                })

                .filter(birthday -> birthday.getDaysToBirthday() <= 7)

                .sorted(Comparator.comparingInt(Birthday::getDaysToBirthday))
                .collect(Collectors.toList());
    }



}
