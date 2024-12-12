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
    private Integer id; // Было Long
    private String title;
    private String description;
    private String priority;  // low, medium, high
    private Timestamp dueDate;
    private String status;    // pending, done, archived, overdue
    private Integer categoryId; // Было Long
    private Integer parentTaskId; // Было Long
    private List<Task> subTasks;
}

