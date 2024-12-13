package ru.itis.service;

import ru.itis.model.Task;
import java.util.List;

public interface TaskService {
    List<Task> getAllTasks(String sortBy, boolean hideCompleted, Long userId);
    void createTask(Task task);
    void updateTask(Task task);
    void deleteTask(Integer id, Long userId);
    Task getTaskById(Integer id, Long userId);
}
