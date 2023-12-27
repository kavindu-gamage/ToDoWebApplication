package com.example.todo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.todo.entity.Task;
import com.example.todo.repository.TaskRepository;
import com.example.todo.service.TaskServiceImpl;
import com.example.todo.validation.IdValidator;
import com.example.todo.validation.TaskValidator;

@ExtendWith(MockitoExtension.class)
public class TaskServiceUnitTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskValidator taskValidator;

    @Mock
    private IdValidator idValidator;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void getAllTasks() {
        Task mockTask1 = new Task(1L, "aa1", "abc", "Low", "Not Completed", "2023-11-23");
        Task mockTask3 = new Task(3L, "aa3", "abc", "Low", "Not Completed", "2023-12-04");
        Task mockTask4 = new Task(4L, "aa4", "abc", "Low", "Not Completed", "2023-12-04");

        when(taskRepository.findAll()).thenReturn(Arrays.asList(mockTask1, mockTask3, mockTask4));
        List<Task> list = taskService.getAllTasks();

        assertEquals(Arrays.asList(mockTask1, mockTask3, mockTask4), list);
        assertEquals(mockTask1, list.get(0));
    }

    @Test
    void getTaskByID() {
        Long taskId = 1L;
        Task mockTask1 = new Task(1L, "aa1", "abc", "Low", "Not Completed", "2023-11-23");
        doNothing().when(idValidator).validateId(taskId);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(mockTask1));

        Task task = taskService.getTaskById(1L);
        assertEquals(mockTask1, task);
    }

    @Test
    void saveTask() {
        Task taskS = new Task(5L, "aa5", "abcd", "Low", "Not Completed", "2023-11-23");
        when(taskRepository.save(taskS)).thenReturn(taskS);

        Task newTask = taskService.saveTask(taskS);
        assertNotNull(newTask.getId());
        assertEquals(taskS, newTask);

    }

    @Test
    void updateTask() {
        Long taskId = 4L;
        Task taskU = new Task(4L, "aa4", "abcd", "Low", "Not Completed", "2024-01-23");
        Task taskE = new Task(4L, "aa4", "abc", "Low", "Not Completed", "2023-12-04");
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskE));
        when(taskRepository.save(taskU)).thenReturn(taskU);

        Task updatedTask = taskService.updateTask(taskId, taskU);
        assertEquals(taskU, updatedTask);
        verify(taskValidator).validateTask(taskE);

    }

    @Test
    void deleteTask() {
        Long taskId = 1L;
        taskService.deleteTask(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }

}
