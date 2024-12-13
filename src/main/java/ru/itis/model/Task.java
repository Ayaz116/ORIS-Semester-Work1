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
    private String priority;
    private Timestamp dueDate;
    private String status;
    private Integer categoryId;
    private Integer parentTaskId;
    private String attachedFilePath;
    private Long userId; // ID пользователя
    private List<Task> subTasks;
}
