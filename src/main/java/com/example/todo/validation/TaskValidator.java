package com.example.todo.validation;

import java.util.Objects;

import com.example.todo.entity.Task;

public class TaskValidator {
    public void validateTask(Task task) {
        Objects.requireNonNull(task, "Task cannot be null");
        validateString(task.getTaskName(), "Task name cannot be null or empty");
        validateString(task.getDescription(), "Description cannot be null or empty");
        validateString(task.getPriority(), "Priority cannot be null or empty");
        validateString(task.getStatus(), "Status cannot be null or empty");
        Objects.requireNonNull(task.getDueDate(), "Due date cannot be null");

    }

    private void validateString(String value, String errorMessage) {
        Objects.requireNonNull(value, errorMessage);
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
