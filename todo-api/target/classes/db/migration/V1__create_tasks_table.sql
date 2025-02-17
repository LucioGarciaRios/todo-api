CREATE TABLE tasks (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description VARCHAR(1024),
                       creation_date TIMESTAMP NOT NULL,
                       status VARCHAR(50) NOT NULL
);
