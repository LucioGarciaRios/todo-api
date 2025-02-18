# ✅ Todo API - Microsserviço Java para Gerenciamento de Tarefas

Este é um microsserviço desenvolvido em **Java** com as seguintes tecnologias:

| Framework/Biblioteca | Versão   | Link                                        |
|----------------------|----------|---------------------------------------------|
| Java                | 21        |                                             |
| Spring Boot         | 3.4.2     | [Spring Boot](https://spring.io/projects/spring-boot) |
| Spring Data JPA     | 3.4.2     | [Spring Data JPA](https://spring.io/projects/spring-data-jpa) |
| Lombok              | 1.18.34   | [Lombok](https://projectlombok.org/) |
| Flyway              | 9.0.0     | [Flyway](https://flywaydb.org/) |
| H2 Database        | 2.1.214   | [H2 Database](https://www.h2database.com/) |
| Swagger/OpenAPI     | 2.3.0     | [Springdoc OpenAPI](https://springdoc.org/) |

---

## 📌 Visão Geral

Microsserviço para **gerenciamento de tarefas (To-Do List)**, permitindo a criação, listagem, atualização e exclusão de tarefas.

---

[//]: # (## 🚀 Como Construir e Executar Localmente com **Maven**)

[//]: # (```sh)

[//]: # (./mvnw clean package)

[//]: # (./mvnw spring-boot:run)


# 🐳 Como Construir e Executar Localmente com Docker

1️⃣ Compilar e empacotar o código Java:
./mvnw -Pdev -Dspring-boot.run.profiles=dev clean package

2️⃣ Construir a imagem Docker:
docker build -t todo-api . -f Dockerfile

3️⃣ Executar a imagem recém-criada:
docker run --rm -p 8080:8080 todo-api


# 📖 Para visualizar a documentação da API

Swagger UI
🔗 http://localhost:8080/swagger-ui.htm

