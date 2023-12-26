package com.example.todo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.todo.controller.TaskControllerImpl;
import com.example.todo.entity.Task;
import com.example.todo.service.TaskService;

@ExtendWith(MockitoExtension.class)
public class TaskControllerUnitTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskControllerImpl tasksController;

    @Test
    void testGetAllTasks() {
        Task mockTask1 = new Task(1L, "aa1", "abc", "Low", "Not Completed", "2023-11-23");
        Task mockTask3 = new Task(3L, "aa3", "abc", "Low", "Not Completed", "2023-12-04");
        Task mockTask4 = new Task(4L, "aa4", "abc", "Low", "Not Completed", "2023-12-04");

        when(taskService.getAllTasks()).thenReturn(Arrays.asList(mockTask1, mockTask3, mockTask4));
        ResponseEntity<List<Task>> response = tasksController.getAllTasks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(mockTask1, mockTask3, mockTask4), response.getBody());
        assertEquals(mockTask1, response.getBody().get(0));
    }

    @Test
    void testGetAllTasksEmptyList() {
        when(taskService.getAllTasks()).thenReturn(Collections.emptyList());
        ResponseEntity<List<Task>> response = tasksController.getAllTasks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    void testTaskByID() {
        Task mockTask1 = new Task(1L, "aa1", "abc", "Low", "Not Completed", "2023-11-23");
        when(taskService.getTaskById(1L)).thenReturn(mockTask1);

        ResponseEntity<Task> task1 = tasksController.getTaskById(1L);
        assertEquals(mockTask1, task1.getBody());
        assertEquals(HttpStatus.OK, task1.getStatusCode());
    }

    @Test
    void testEmptyTaskById() {
        when(taskService.getTaskById(8L)).thenReturn(null);
        ResponseEntity<Task> response = tasksController.getTaskById(8L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    void testSaveTask() {
        Task taskS = new Task(5L, "aa5", "abcd", "Low", "Not Completed", "2023-11-23");
        when(taskService.saveTask(taskS)).thenReturn(taskS);

        ResponseEntity<Task> newTask = tasksController.saveTask(taskS);
        assertEquals(taskS, newTask.getBody());
        assertEquals(HttpStatus.OK, newTask.getStatusCode());

    }

    @Test
    void testUpdateTask() {
        Task taskU = new Task(4L, "aa4", "abcd", "Low", "Not Completed", "2024-01-23");
        when(taskService.updateTask(4L, taskU)).thenReturn(taskU);

        ResponseEntity<Task> updatedTask = tasksController.updateTask(4L, taskU);
        assertEquals(taskU, updatedTask.getBody());
        assertEquals(HttpStatus.OK, updatedTask.getStatusCode());

    }

    @Test
    void deleteTask_success() {
        Long taskId = 1L;
        ResponseEntity<Void> response = tasksController.deleteTask(taskId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(taskService).deleteTask(taskId); // Verify interaction with mock
    }

    // @Test
    // void testGetAllTasksException() {
    // when(taskService.getAllTasks()).thenThrow(new RuntimeException("Internal
    // Server Error"));
    // ResponseEntity<List<Task>> response = tasksController.getAllTasks();
    // assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    // }

    // @Test
    // void testInvalidTaskByID() {
    // when(taskService.getTaskById(7l)).thenThrow(new
    // handleNoSuchElementException("Resource not found"));

    // assertThrows(handleNoSuchElementException.class, () ->
    // tasksController.getTaskById(7L));
    // }

}
