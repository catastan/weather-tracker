**Name:** Stan Petrisor Catalin \
**Group:** 342C3

## Assignment 2 - SCD

# RESTful Weather Application

### Description

The application consists of a RESTful API developed in Spring Boot, a MySQL
database and a database management utility, phpMyAdmin, all
containerized with Docker.

### Running

```bash
    docker-compose up --build
```

### Testing

When running the script in Postman, I obtained 74/74 tests passed.

### Notes

- I added a healthcheck in `docker-compose.yml` for the MySQL service, to
  check if the database is up before the application starts.

- I used a volume for the MySQL database to ensure data persistence
  when containers are restarted.

- I defined environment variables in `docker-compose.yml`.

- The `.dockerignore` file was added to exclude unnecessary files.

- The reason I chose Spring Boot and MySQL is the experience I already
  gained during my bachelor’s thesis.
