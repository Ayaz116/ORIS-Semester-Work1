package ru.itis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.config.ModuleConfiguration;
import ru.itis.model.Birthday;
import ru.itis.repository.BirthdayRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BirthdayRepositoryImpl implements BirthdayRepository {

    private final JdbcTemplate jdbcTemplate = ModuleConfiguration.jdbcTemplate();

    private final RowMapper<Birthday> birthdayRowMapper = new RowMapper<>() {
        @Override
        public Birthday mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Birthday.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .birthDate(rs.getDate("birth_date"))
                    .build();
        }
    };

    @Override
    public List<Birthday> findAll() {
        String sql = "SELECT * FROM birthdays";
        return jdbcTemplate.query(sql, birthdayRowMapper);
    }

    @Override
    public Optional<Birthday> findById(Integer id) {
        String sql = "SELECT * FROM birthdays WHERE id = ?";
        List<Birthday> birthdays = jdbcTemplate.query(sql, birthdayRowMapper, id);
        return birthdays.stream().findFirst();
    }

    @Override
    public void save(Birthday birthday) {
        String sql = "INSERT INTO birthdays(name, birth_date) VALUES(?, ?)";
        jdbcTemplate.update(sql, birthday.getName(), birthday.getBirthDate());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM birthdays WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
