package com.example.todo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.todo.entity.Task;
import com.example.todo.repository.TaskRepository;

public class TaskServiceImpl implements TaskService {
    
    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Task Not Found"+id));
    }

    @Override
    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task){
        Task existingTask = taskRepository.findById(id).orElse(null);

        if(task != null ){
            existingTask.setTaskName(task.getTaskName());
            existingTask.setDescription(task.getDescription());
            existingTask.setPriority(task.getPriority());
            existingTask.setStatus(task.getStatus());
            existingTask.setDueDate(task.getDueDate());
            return taskRepository.save(existingTask);
        }else{
            return null;
        }
    }

    @Override
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
