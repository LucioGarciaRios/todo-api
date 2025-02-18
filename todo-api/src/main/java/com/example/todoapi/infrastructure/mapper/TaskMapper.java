package com.example.todoapi.infrastructure.mapper;

import com.example.todoapi.presentation.dto.TaskDTO;
import com.example.todoapi.domain.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDTO toDTO(Task task) {
        if (task == null) {
            return null;
        }
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreationDate(),
                task.getStatus()
        );
    }

    public Task toEntity(TaskDTO dto) {
        if (dto == null) {
            return null;
        }
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCreationDate(dto.getCreationDate());
        task.setStatus(dto.getStatus());
        return task;
    }
}
