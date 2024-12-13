package ru.itis.service;

import ru.itis.model.Task;
import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    void createTask(Task task);
    void updateTask(Task task);
    void deleteTask(Integer id);
    Task getTaskById(Integer id);
    List<Task> getAllTasks(String sortBy, boolean hideCompleted);
    void attachFileToTask(Integer taskId, String filePath);
}
