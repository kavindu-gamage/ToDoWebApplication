package com.example.todo.validation;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.example.todo.entity.Task;

@Component
public class TaskValidator {
    public void validateTask(Task task) {
        try {
            Objects.requireNonNull(task, "Task cannot be null");
            validateString(task.getTaskName(), "Task name cannot be null or empty");
            validateString(task.getDescription(), "Description cannot be null or empty");
            validateString(task.getPriority(), "Priority cannot be null or empty");
            validateString(task.getStatus(), "Status cannot be null or empty");
            Objects.requireNonNull(task.getDueDate(), "Due date cannot be null");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Validation error: " + e.getMessage());
        }

    }

    private void validateString(String value, String errorMessage) {
        Objects.requireNonNull(value, errorMessage);
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
