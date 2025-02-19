# Todo API

## Descrição

Esta é uma API RESTful para gerenciamento de tarefas (To-Do List). Ela permite criar, listar, atualizar e deletar tarefas.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **Flyway**
- **Microsoft SQL Server**
- **Swagger/OpenAPI** (para documentação da API)

## Como Rodar o Projeto

1. Clone o repositório:
   ```sh
   git clone https://github.com/LucioGarciaRios/todo-api.git
   ```
2. Acesse a pasta do projeto:
   ```sh
   cd todo-api
   ```
3. Compile o projeto:
   ```sh
   mvn clean install
   ```
4. Execute a API:
   ```sh
   mvn spring-boot:run
   ```

A API estará disponível em `http://localhost:8080`.

## Endpoints

### Criar uma Tarefa
📌 **POST** `http://localhost:8080/tasks`
```json
{
  "title": "Nova Tarefa",
  "description": "Descricao da tarefa",
  "status": "PENDING"
}
```

### Listar Todas as Tarefas
📌 **GET** `http://localhost:8080/tasks`

### Buscar Tarefa por ID
📌 **GET** `http://localhost:8080/tasks/{id}`

### Atualizar uma Tarefa
📌 **PUT** `http://localhost:8080/tasks/{id}`
```json
{
  "title": "Tarefa Atualizada",
  "description": "Nova descricao",
  "status": "COMPLETED"
}
```

### Deletar uma Tarefa
📌 **DELETE** `http://localhost:8080/tasks/{id}`

## Documentação da API

A API conta com documentação interativa via Swagger. Após iniciar o projeto, acesse:

🌐 `http://localhost:8080/swagger-ui.html`

## Autor

👨‍💻 Desenvolvido por **Lúcio Garcia**.

