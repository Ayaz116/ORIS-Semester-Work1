package ru.itis.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.itis.model.Task;
import ru.itis.repository.TaskRepository;
import ru.itis.service.TaskService;

import java.util.List;
import java.util.Optional;

@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks(String sortBy, boolean hideCompleted, Long userId) {
        List<Task> tasks = taskRepository.findAllWithSorting(sortBy, userId);
        if (hideCompleted) {
            tasks.removeIf(task -> "done".equalsIgnoreCase(task.getStatus()));
        }
        return tasks;
    }

    @Override
    public void createTask(Task task) {
        log.info("Creating a new task: {}", task);
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Task task) {
        log.info("Updating task with id {}: {}", task.getId(), task);
        taskRepository.update(task);
    }

    @Override
    public void deleteTask(Integer id, Long userId) {
        log.info("Deleting task with id {} for user {}", id, userId);
        Optional<Task> task = taskRepository.findByIdAndUser(id, userId);
        task.ifPresentOrElse(
                t -> taskRepository.delete(id),
                () -> log.warn("Task with id {} not found or access denied", id)
        );
    }

    @Override
    public Task getTaskById(Integer id, Long userId) {
        log.info("Fetching task with id {} for user {}", id, userId);
        Optional<Task> task = taskRepository.findByIdAndUser(id, userId);
        return task.orElseThrow(() -> new IllegalArgumentException("Task not found or access denied"));
    }
}
