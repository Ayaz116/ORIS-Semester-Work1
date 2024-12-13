package ru.itis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Integer id;
    private String title;
    private String description;
    private String priority;  // low, medium, high
    private Timestamp dueDate;
    private String status;    // pending, done, overdue
    private Integer categoryId;
    private Integer parentTaskId;
    private List<Task> subTasks;
    private String attachedFilePath; // Добавлено
}

