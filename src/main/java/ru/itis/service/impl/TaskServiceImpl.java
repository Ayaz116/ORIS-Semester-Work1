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
    public List<Task> getAllTasks() {
        log.info("Fetching all tasks");
        List<Task> tasks = taskRepository.findAll();
        loadSubTasksForAll(tasks);
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
    public void deleteTask(Integer id) {
        log.info("Deleting task with id {}", id);
        taskRepository.delete(id);
    }

    @Override
    public Task getTaskById(Integer id) {
        log.info("Fetching task with id {}", id);
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setSubTasks(taskRepository.findSubTasksByParentId(id));
            return task;
        } else {
            log.warn("Task with id {} not found", id);
            throw new IllegalArgumentException("Task not found");
        }
    }

    @Override
    public List<Task> getAllTasks(String sortBy, boolean hideCompleted) {
        log.info("Fetching tasks sorted by {} with hideCompleted={}", sortBy, hideCompleted);
        List<Task> allTasks = taskRepository.findAllWithSorting(sortBy);
        if (hideCompleted) {
            allTasks.removeIf(t -> "done".equalsIgnoreCase(t.getStatus()));
        }
        loadSubTasksForAll(allTasks);
        return allTasks;
    }

    @Override
    public void attachFileToTask(Integer taskId, String filePath) {
        log.info("Attaching file {} to task with id {}", filePath, taskId);
        taskRepository.attachFileToTask(taskId, filePath);
    }

    private void loadSubTasksForAll(List<Task> tasks) {
        for (Task t : tasks) {
            List<Task> subs = taskRepository.findSubTasksByParentId(t.getId());
            t.setSubTasks(subs);
        }
    }
}
