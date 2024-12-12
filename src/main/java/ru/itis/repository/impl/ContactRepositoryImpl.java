package ru.itis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.config.ModuleConfiguration;
import ru.itis.model.Contact;
import ru.itis.repository.ContactRepository;

import java.util.List;

public class ContactRepositoryImpl implements ContactRepository {
    private final JdbcTemplate jdbcTemplate = ModuleConfiguration.jdbcTemplate();

    private final RowMapper<Contact> contactRowMapper = (rs, rowNum) ->
            Contact.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .birthDate(rs.getDate("birth_date"))
                    .build();

    @Override
    public List<Contact> findAll() {
        return jdbcTemplate.query("SELECT * FROM contacts", contactRowMapper);
    }

    @Override
    public void save(Contact contact) {
        jdbcTemplate.update("INSERT INTO contacts(name, birth_date) VALUES(?,?)", contact.getName(), contact.getBirthDate());
    }

    @Override
    public Contact findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM contacts WHERE id=?", contactRowMapper, id);
    }
}
