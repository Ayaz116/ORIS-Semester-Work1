package ru.itis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.config.ModuleConfiguration;
import ru.itis.model.Task;
import ru.itis.repository.TaskRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TaskRepositoryImpl implements TaskRepository {
    private final JdbcTemplate jdbcTemplate = ModuleConfiguration.jdbcTemplate();

    private final RowMapper<Task> taskRowMapper = new RowMapper<>() {
        @Override
        public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Task.builder()
                    .id(rs.getInt("id")) // Было rs.getLong
                    .title(rs.getString("title"))
                    .description(rs.getString("description"))
                    .priority(rs.getString("priority"))
                    .dueDate(rs.getTimestamp("due_date"))
                    .status(rs.getString("status"))
                    .categoryId((Integer) rs.getObject("category_id")) // Было Long
                    .parentTaskId((Integer) rs.getObject("parent_task_id")) // Было Long
                    .build();
        }
    };


    @Override
    public List<Task> findAll() {
        String sql = "SELECT * FROM tasks";
        return jdbcTemplate.query(sql, taskRowMapper);
    }

    @Override
    public Optional<Task> findById(Integer id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        List<Task> tasks = jdbcTemplate.query(sql, taskRowMapper, id);
        return tasks.stream().findFirst();
    }

    @Override
    public void save(Task task) {
        String sql = "INSERT INTO tasks(title, description, priority, due_date, status, category_id, parent_task_id) " +
                "VALUES(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getPriority(), task.getDueDate(),
                task.getStatus(), task.getCategoryId(), task.getParentTaskId());
    }

    @Override
    public void update(Task task) {
        String sql = "UPDATE tasks SET title=?, description=?, priority=?, due_date=?, status=?, category_id=?, parent_task_id=? WHERE id=?";
        jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getPriority(), task.getDueDate(),
                task.getStatus(), task.getCategoryId(), task.getParentTaskId(), task.getId());
    }

    @Override
    public void delete(Integer id) { // Было Long
        String sql = "DELETE FROM tasks WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Task> findAllWithSorting(String sortBy) {
        String orderBy;
        switch (sortBy) {
            case "dueDate": orderBy = "due_date"; break;
            case "creationDate": orderBy = "id"; break;
            default:
                orderBy = "priority";
        }
        String sql = "SELECT * FROM tasks ORDER BY " + orderBy;
        return jdbcTemplate.query(sql, taskRowMapper);
    }

    @Override
    public List<Task> findSubTasksByParentId(Integer parentId) {
        String sql = "SELECT * FROM tasks WHERE parent_task_id = ?";
        return jdbcTemplate.query(sql, taskRowMapper, parentId);
    }
}
