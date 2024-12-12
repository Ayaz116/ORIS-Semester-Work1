package ru.itis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.config.ModuleConfiguration;
import ru.itis.model.Category;
import ru.itis.repository.CategoryRepository;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    private final JdbcTemplate jdbcTemplate = ModuleConfiguration.jdbcTemplate();

    private final RowMapper<Category> categoryRowMapper = (rs, rowNum) ->
            Category.builder().id(rs.getInt("id")).name(rs.getString("name")).build();

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("SELECT * FROM categories", categoryRowMapper);
    }

    @Override
    public void save(Category category) {
        jdbcTemplate.update("INSERT INTO categories(name) VALUES(?)", category.getName());
    }

    @Override
    public Category findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM categories WHERE id=?", categoryRowMapper, id);
    }
}
