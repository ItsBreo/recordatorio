# Notas API 📒🧑‍💻

Una API RESTful construida con **Spring Boot** para gestionar usuarios y sus notas personales. Cada usuario puede crear, consultar, actualizar y eliminar sus notas.

El proyecto incluye:
- Validaciones con Bean Validation.
- Manejo de errores personalizado con `@ControllerAdvice`.
- Relaciones entre entidades con JPA/Hibernate.
- Autenticación básica simulada (email y password hasheado, sin tokens).

---

## 🛠️ Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MariaDB
- Maven
- Bean Validation (Jakarta)
- Lombok
- ControllerAdvice para gestión de errores

---

## 🔐 Relaciones entre entidades

- Un `Usuario` puede tener muchas `Notas` (`@OneToMany`)
- Las notas se eliminan si el usuario es eliminado (`cascade = ALL`, `orphanRemoval = true`)
- Cada `Nota` pertenece a un único `Usuario` (`@ManyToOne`)

## 🗂️ Estructura del proyecto

```

src
└── main
    ├── java
    │   └── azael.josue.recordatorio
    │       ├── controller
    │       │   ├── noteController.java         # Controlador REST para gestionar las notas
    │       │   ├── userControllerV1.java       # Controlador REST v1 para gestión de usuarios
    │       │   └── userControllerV2.java       # Controlador REST v2 para gestión avanzada de usuarios
    │       ├── dto
    │       │   └── signInRequest.java          # DTO para solicitud de inicio de sesión con email y password
    │       ├── exception
    │       │   ├── GlobalExceptionHandler.java # Clase para manejar errores globales con @ControllerAdvice
    │       │   └── NotFoundException.java      # Excepción personalizada para recursos no encontrados
    │       ├── model
    │       │   ├── note.java                   # Entidad que representa una nota (título, contenido, fecha, usuario)
    │       │   └── user.java                   # Entidad que representa un usuario (nombre, email, contraseña)
    │       ├── repository
    │       │   ├── noteRepository.java         # Repositorio JPA para la entidad Note
    │       │   └── userRepository.java         # Repositorio JPA para la entidad User
    │       └── service
    │           ├── AbstractCrudService.java    # Servicio genérico con operaciones CRUD comunes
    │           ├── crudService.java            # Interfaz genérica para servicios CRUD
    │           ├── noteService.java            # Interfaz específica para el servicio de notas
    │           ├── noteServiceImpl.java        # Implementación del servicio de notas
    │           ├── userService.java            # Interfaz específica para el servicio de usuarios
    │           └── userServiceImpl.java        # Implementación del servicio de usuarios
    └── resources
        └── application.properties              # Archivo de configuración del proyecto (base de datos, puerto, etc.)

```

## 🚀 Instrucciones de ejecución

### 1. Clonar el repositorio de GitHub

Utilizando `GitHub Desktop`, se puede realizar una clonación del repositorio

## 2. Asegurarnos de tener instalado MariaDB y crear la Base de Datos

```sql
CREATE DATABASE notasdb;
```

## 3. Ajusta la configuración en resources -> application.properties

Son las propiedades que se utilizarán al ejecutar el proyecto, 
deben estar acorde con tus propiedades, comprueba el puerto y que el
nombre y contraseña sean los que usas para acceder a MariaDB

## 4. ¡Ahora deberías ser capaz ejecutar el proyecto!

Mediante tu editor de código como `Visual Studio Code` o `IntelliJ`

O a último remedio, mediante consola

```bash
./mvnw spring-boot:run
```

## 5. Pruebas de EndPoints

Los endpoints probados son los siguientes:

Puedes utilizar la herramienta `POSTMAN` para probar los diferentes endpoints
Se puede descargar vía internet, o desde `Visual Studio Code`, instalando la
extensión.

(A tener en cuenta, especificar la ruta completa, con tu puerto web del host local, además del endpoint)

```
POST http://localhost:8080/api/v2/sign-in
POST http://localhost:8080/api/v1/users
GET  http://localhost:8080/api/v1/users
POST http://localhost:8080/api/v1/notes?userId=5 (En las capturas, el id es 5, puede ser diferente dependiendo de a qué quieras introducir la nota)
GET http://localhost:8080/api/v1/notes
GET http://localhost:8080/api/v1/users (Visualizamos la nota con el usuario)
PUT http://localhost:8080/api/v1/notes/3?userId=5
PUT http://localhost:8080/api/v1/users/5
GET http://localhost:8080/api/v1/notes/3
GET http://localhost:8080/api/v1/users/5
POST http://localhost:8080/api/v1/notes?userId=4
POST http://localhost:8080/api/v1/notes?userId=5
GET http://localhost:8080/api/v1/notes?userId=5?order=desc
DELETE http://localhost:8080/api/v1/notes/3
GET http://localhost:8080/api/v1/notes
DELETE http://localhost:8080/api/v1/users/4
GET http://localhost:8080/api/v1/users
```