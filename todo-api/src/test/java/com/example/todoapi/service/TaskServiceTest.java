package com.example.todoapi.service;

import com.example.todoapi.application.service.TaskService;
import com.example.todoapi.domain.model.Task;
import com.example.todoapi.domain.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    public void setUp() {
        // Inicializa os mocks do Mockito
        MockitoAnnotations.openMocks(this);
        // Cria uma instância de Task para uso nos testes
        task = new Task();
        task.setId(1L);
        task.setTitle("Teste Task");
        task.setDescription("Descrição da task");
        task.setCreationDate(LocalDateTime.now());
        task.setStatus("pendente");
    }

    @Test
    public void testFindAllTasks() {
        // Simula o retorno do repositório com uma lista contendo uma task
        List<Task> tasks = Arrays.asList(task);
        when(taskRepository.findAll()).thenReturn(tasks);
        // Executa o método de listagem
        List<Task> result = taskService.findAllTasks();
        // Verifica se o tamanho da lista é 1 e se o repositório foi chamado uma vez
        assertEquals(1, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void testFindTaskById() {
        // Simula o retorno da task para o id 1
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Optional<Task> result = taskService.findTaskById(1L);
        assertTrue(result.isPresent());
        assertEquals("Teste Task", result.get().getTitle());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateTask() {
        // Simula o salvamento de uma task
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        Task created = taskService.createTask(task);
        assertNotNull(created);
        assertEquals("Teste Task", created.getTitle());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testUpdateTask() {
        // Cria uma task atualizada com novos dados
        Task updatedTask = new Task();
        updatedTask.setTitle("Task Atualizada");
        updatedTask.setDescription("Descrição atualizada");
        updatedTask.setStatus("completa");

        // Simula que a task original é encontrada e, em seguida, salva a task atualizada
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        Task result = taskService.updateTask(1L, updatedTask);
        assertEquals("Task Atualizada", result.getTitle());
        assertEquals("completa", result.getStatus());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testUpdateTaskNotFound() {
        // Cria uma task com dados para atualização
        Task updatedTask = new Task();
        updatedTask.setTitle("Task Atualizada");
        updatedTask.setDescription("Descrição atualizada");
        updatedTask.setStatus("completa");

        // Simula que nenhuma task foi encontrada para o id informado
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            taskService.updateTask(1L, updatedTask);
        });
        assertTrue(exception.getMessage().contains("Tarefa não encontrada"));
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteTask() {
        // Simula a exclusão da task
        doNothing().when(taskRepository).deleteById(1L);
        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }
}
