package ru.itis.repository;

import ru.itis.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();
    Optional<Task> findById(Integer id);
    void save(Task task);
    void update(Task task);
    void delete(Integer id);
    List<Task> findAllWithSorting(String sortBy);
    List<Task> findSubTasksByParentId(Integer parentId);
    void attachFileToTask(Integer taskId, String filePath);
}
