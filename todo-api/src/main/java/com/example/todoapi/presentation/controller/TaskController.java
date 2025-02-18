package com.example.todoapi.presentation.controller;

import com.example.todoapi.presentation.dto.TaskDTO;
import com.example.todoapi.infrastructure.mapper.TaskMapper;
import com.example.todoapi.domain.model.Task;
import com.example.todoapi.application.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Task API", description = "Endpoints para gerenciamento de tarefas")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @Operation(
            summary = "Listar todas as tarefas",
            description = "Retorna uma lista contendo todas as tarefas cadastradas no sistema."
    )
    @GetMapping
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskService.findAllTasks();
        return tasks.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "Obter uma tarefa por ID",
            description = "Busca uma tarefa específica pelo seu ID. Retorna 404 se a tarefa não for encontrada."
    )
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(
            @Parameter(description = "ID da tarefa a ser buscada", example = "1")
            @PathVariable Long id) {
        return taskService.findTaskById(id)
                .map(task -> ResponseEntity.ok(taskMapper.toDTO(task)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Criar uma nova tarefa",
            description = "Cria uma nova tarefa no sistema e retorna a tarefa criada."
    )
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(
            @RequestBody TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskMapper.toDTO(createdTask));
    }

    @Operation(
            summary = "Atualizar uma tarefa existente",
            description = "Atualiza os dados de uma tarefa com base no ID informado. Retorna 404 se a tarefa não for encontrada."
    )
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(
            @Parameter(description = "ID da tarefa a ser atualizada", example = "1")
            @PathVariable Long id,

            @RequestBody TaskDTO taskDTO) {
        try {
            Task task = taskMapper.toEntity(taskDTO);
            Task updatedTask = taskService.updateTask(id, task);
            return ResponseEntity.ok(taskMapper.toDTO(updatedTask));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Excluir uma tarefa",
            description = "Remove uma tarefa com base no ID informado. Retorna 204 se a exclusão for bem-sucedida."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @Parameter(description = "ID da tarefa a ser excluída", example = "1")
            @PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
