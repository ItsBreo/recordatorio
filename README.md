# Notas API 📒🧑‍💻

Una API RESTful construida con **Spring Boot** para gestionar usuarios y sus notas personales. Cada usuario puede crear, actualizar y eliminar notas. Incluye autenticación básica por email y password (hash), validaciones y manejo de errores personalizados.

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
