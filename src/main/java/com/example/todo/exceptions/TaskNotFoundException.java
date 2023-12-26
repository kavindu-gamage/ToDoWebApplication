package com.example.todo.exceptions;

public class TaskNotFoundException extends RuntimeException {
    private Long taskId;

    public TaskNotFoundException(Long taskId) {
        super("Task not found with ID: " + taskId);
        this.taskId = taskId;
    }

    public Long getTaskId() {
        return taskId;
    }

}
