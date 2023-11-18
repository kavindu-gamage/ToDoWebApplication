package com.example.todo.service;

import java.util.List;

import com.example.todo.entity.Task;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task saveTask(Task task);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);

}
