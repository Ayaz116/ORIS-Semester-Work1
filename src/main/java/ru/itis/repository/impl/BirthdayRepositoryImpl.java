package ru.itis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.config.ModuleConfiguration;
import ru.itis.model.Birthday;
import ru.itis.repository.BirthdayRepository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BirthdayRepositoryImpl implements BirthdayRepository {

    private final JdbcTemplate jdbcTemplate = ModuleConfiguration.jdbcTemplate();

    private final RowMapper<Birthday> birthdayRowMapper = (rs, rowNum) -> Birthday.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .birthDate(rs.getDate("birth_date").toLocalDate())
            .userId(rs.getLong("user_id"))
            .build();

    @Override
    public List<Birthday> findAllByUser(Long userId) {
        String sql = "SELECT * FROM birthdays WHERE user_id = ?";
        return jdbcTemplate.query(sql, birthdayRowMapper, userId);
    }

    @Override
    public void save(Birthday birthday) {
        String sql = "INSERT INTO birthdays (name, birth_date, user_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, birthday.getName(), birthday.getBirthDate(), birthday.getUserId());
    }

    @Override
    public void deleteByIdAndUser(Long id, Long userId) {
        String sql = "DELETE FROM birthdays WHERE id = ? AND user_id = ?";
        jdbcTemplate.update(sql, id, userId);
    }

    @Override
    public List<Birthday> findBirthdaysBetweenDates(LocalDate startDate, LocalDate endDate, Long userId) {
        String sql = "SELECT * FROM birthdays WHERE user_id = ? " +
                "AND birth_date >= ? AND birth_date <= ? ORDER BY birth_date";
        return jdbcTemplate.query(sql, birthdayRowMapper, userId, Date.valueOf(startDate), Date.valueOf(endDate));
    }

}
