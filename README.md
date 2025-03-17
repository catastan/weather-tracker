**Nume:** Stan Petrisor Catalin \
**Grupa:** 342C3

## Tema 2 - SCD

# Aplicatie RESTful Meteo

### Descriere

Aplicatia este formata dintr-ul API RESTful dezvoltat in Spring Boot, o baza
de date MySQL si un utilitar de gestiune al bazei de date, phpMyAdmin, toate
containerizate cu Docker.

### Rulare

```bash
    docker-compose up --build
```

### Testare

La rularea scriptului in Postman, am obtinut 74/74 teste passed.

### Mentiuni

- Am adaugat healthcheck in `docker-compose.yml` pentru serviciul MySQL, pentru
  a verifica daca baza de date este pornita inainte ca aplicatia sa porneasca.

- Am utilizat un volum pentru baza de date MySQL pentru a asigura persistenta
  datelor atunci cand se repornesc containerele.

- Am definit variabile de mediu in `docker-compose.yml`.

- Fisierul .dockerignore a fost adaugat pentru a exclude fisierele inutile.

- Motivul pentru care am ales Spring Boot si MySQL este exeperienta deja
  acumulata in cadrul lucrarii de licenta.
