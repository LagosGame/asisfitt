# AsisFitt API

API REST para gestión de box de crossfit en Ciudad de Asís. Gestiona usuarios, WODs, clases y reservas.

## Tecnologías

- **Java 21** + **Spring Boot 3.5**
- **PostgreSQL** — base de datos relacional
- **Spring Data JPA** — acceso a datos con Hibernate
- **Docker + Docker Compose** — contenedores para despliegue
- **Swagger/OpenAPI** — documentación de la API
- **JUnit 5 + Mockito** — tests unitarios 

## Reglas de negocio

- Un usuario no puede reservar la misma clase dos veces
- Una clase no puede superar su capacidad máxima
- Una reserva cancelada libera una plaza

## Cómo arrancar el proyecto

### Requisitos
- Docker Desktop instalado

### Pasos

```bash
git clone https://github.com/LagosGame/asisfitt.git
cd asisfitt
docker compose up
```

La API estará disponible en `http://localhost:8084`

Documentación Swagger en `http://localhost:8084/swagger-ui/index.html`

## Endpoints principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /usuarios | Listar usuarios |
| POST | /usuarios | Crear usuario |
| GET | /wods | Listar WODs |
| POST | /wods | Crear WOD |
| GET | /clases | Listar clases |
| POST | /clases | Crear clase con WOD asignado |
| GET | /reservas | Listar reservas |
| POST | /reservas | Crear reserva (valida capacidad y duplicados) |
| PATCH | /reservas/{id}/cancelar | Cancelar reserva |

## Tests

```bash
./mvnw test
```