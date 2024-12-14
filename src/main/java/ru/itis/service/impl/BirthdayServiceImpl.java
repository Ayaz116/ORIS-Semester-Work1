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
                    // Получение текущего года для обработки
                    LocalDate nextBirthday = birthday.getBirthDate()
                            .withYear(today.getYear());

                    // Если день рождения уже прошёл в этом году
                    if (nextBirthday.isBefore(today) || nextBirthday.isEqual(today)) {
                        nextBirthday = nextBirthday.plusYears(1);
                    }

                    // Расчёт дней до ближайшего дня рождения
                    long daysToBirthday = ChronoUnit.DAYS.between(today, nextBirthday);

                    // Расчёт возраста на следующий день рождения
                    int upcomingAge = nextBirthday.getYear() - birthday.getBirthDate().getYear();

                    // Устанавливаем вычисленные значения
                    birthday.setDaysToBirthday((int) daysToBirthday);
                    birthday.setUpcomingAge(upcomingAge);

                    return birthday;
                })
                // Фильтруем только дни рождения в ближайшую неделю
                .filter(birthday -> birthday.getDaysToBirthday() <= 7)
                // Сортируем по количеству дней до дня рождения
                .sorted(Comparator.comparingInt(Birthday::getDaysToBirthday))
                .collect(Collectors.toList());
    }



}
