package com.example.todoapi.application.service;

import com.example.todoapi.domain.model.Task;
import com.example.todoapi.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {

        task.setCreatedAt(java.time.LocalDateTime.now());

        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task newTaskData) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(newTaskData.getTitle());
            task.setDescription(newTaskData.getDescription());
            task.setStatus(newTaskData.getStatus());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada com id " + id));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
