package ru.itis.repository;

import ru.itis.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();
    List<Task> findAllByUser(Long userId);
    List<Task> findAllWithSorting(String sortBy, Long userId);
    Optional<Task> findById(Integer id);
    Optional<Task> findByIdAndUser(Integer id, Long userId);
    void save(Task task);
    void update(Task task);
    void delete(Integer id);
    List<Task> findSubTasksByParentId(Integer parentId);
    void attachFileToTask(Integer taskId, String filePath);
}
