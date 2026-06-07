# Sistema Universitario - Gestión de Carreras, Estudiantes y Cursos

## Descripción

Aplicación web desarrollada como proyecto final para el cuarto examen parcial de Programación 3. Permite gestionar carreras, estudiantes y cursos universitarios, demostrando el uso de relaciones JPA como OneToMany y ManyToMany.

## Tecnologías utilizadas

- Java 21.0.11
- Spring Boot 4.0.6
- Spring Data JPA (Hibernate)
- MySQL 8.0
- Thymeleaf
- Bootstrap 5
- Maven


## Configuración de la base de datos

1. Crear la base de datos en MySQL:

```sql
CREATE DATABASE gestion_universidad;
```

2. Configurar src/main/resources/application.properties:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestion_universidad
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```
## Ejecución del proyecto

1. Clonar el repositorio:
```bash
git clone https://github.com/tu-usuario/gestion-cursos.git
Abrir el proyecto en IntelliJ IDEA
```
2. Abrir el proyecto en IntelliJ IDEA

3. Ejecutar la clase GestionCursosApplication.java

4. Abrir el navegador en http://localhost:8080

## Estructura del proyecto (MVC)
```text
src/main/java/com/universidad/gestioncursos/
├── controller/     # Controladores (Carrera, Estudiante, Curso)
├── model/          # Entidades JPA (Carrera, Materia, Estudiante, Curso, Tarea)
├── repository/     # Interfaces JpaRepository
└── service/        # Lógica de negocio

src/main/resources/
├── templates/      # Vistas Thymeleaf (HTML)
└── static/css/     # Estilos personalizados
```

## Relaciones JPA implementadas
OneToMany / ManyToOne: Carrera ↔ Materia, Curso ↔ Tarea

ManyToMany: Estudiante ↔ Curso (con tabla intermedia estudiante_curso)

## Funcionalidades
### CRUD de Carreras
Listar, crear, editar y eliminar carreras

### CRUD de Cursos
Listar, crear, editar y eliminar cursos

### CRUD de Estudiantes
Listar, crear, editar, eliminar y ver detalle de estudiantes

Asignación de múltiples cursos a un estudiante (ManyToMany)

## Autor
Nombre: Oscar Emmanuel Romero Pineda

Carnet: RP101324

Asignatura: Programación III

Docente: Ing. Cesar Sánchez, MIR

Fecha: 6 de Junio 2026

Licencia
Este proyecto fue desarrollado con fines académicos para el ciclo 01-2026.