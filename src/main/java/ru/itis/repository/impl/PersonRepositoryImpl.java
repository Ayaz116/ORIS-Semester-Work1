package ru.itis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.config.ModuleConfiguration;
import ru.itis.model.Person;
import ru.itis.repository.PersonRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PersonRepositoryImpl implements PersonRepository {

    private final JdbcTemplate jdbcTemplate = ModuleConfiguration.jdbcTemplate();

    private final RowMapper<Person> personRowMapper = (rs, rowNum) ->
            Person.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .birthDate(rs.getDate("birth_date"))
                    .build();

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM people", personRowMapper);
    }

    @Override
    public Optional<Person> findById(Long id) {
        List<Person> res = jdbcTemplate.query("SELECT * FROM people WHERE id=?", personRowMapper, id);
        return res.stream().findFirst();
    }

    @Override
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO people(name, birth_date) VALUES(?,?)", person.getName(), person.getBirthDate());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM people WHERE id=?", id);
    }
}
