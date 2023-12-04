package com.example.todo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.todo.entity.Task;

@Service
public interface TasksController {

    @GetMapping("/tasks")
    ResponseEntity<List<Task>> getAllTasks();

    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable Long id);

    @PostMapping("/tasks")
    ResponseEntity<Task> saveTask(@RequestBody Task task);

    @PutMapping("/tasks/{id}")
    ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task);

    @DeleteMapping("/tasks/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable Long id);
}
