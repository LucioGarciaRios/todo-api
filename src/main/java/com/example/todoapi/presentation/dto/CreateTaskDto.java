package com.example.todoapi.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDto {

    @NotBlank(message = "O título é obrigatório")
    private String title;

    @Size(max = 1024, message = "A descrição deve ter no máximo 1024 caracteres")
    private String description;

    @NotBlank(message = "O status é obrigatório")
    private String status;
}
