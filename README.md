# Virtual Classroom System

API REST para una plataforma educativa desarrollada con Spring Boot que permite gestionar usuarios, permisos, roles, estudiantes, profesores y cursos dentro de un aula virtual.

El sistema implementa autenticación y autorización mediante Spring Security, utilizando JWT y OAuth2, junto con un sistema de control de acceso basado en roles (RBAC) para proteger los endpoints y garantizar el acceso únicamente a usuarios autorizados. Las contraseñas se almacenan de forma segura mediante encriptación antes de persistirse en la base de datos.

Simula el backend de una plataforma educativa donde administradores, profesores y estudiantes interactúan con los recursos disponibles según sus permisos.

## Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Security
* JWT (JSON Web Tokens)
* Autenticación mediante OAuth2
* JPA / Hibernate
* MySQL
* Maven

## Arquitectura
El sistema sigue una arquitectura en capas.

### security.config
Contiene la configuración relacionada con Spring Security, incluyendo:
* Configuración de reglas de autorización
* Integración de autenticación mediante JWT
* Soporte para OAuth2 Authentication
* Filtros de seguridad para validar tokens en cada request

### utils
Incluye clases auxiliares como JwtUtils
* Generación y validación de tokens JWT
* Extracción de información del token

### controller
Contiene los endpoints de la API REST que exponen la funcionalidad del sistema. Estos se encuentran securizados mediante anotaciones para que los usuarios accedan a los recursos en función de sus roles.
Entre ellos se incluyen:

AuthenticationController
- Maneja la autenticación de usuarios
- Genera tokens JWT para usuarios válidos

### service
Cada entidad principal cuenta con su propio servicio que se encarga de validar datos, aplicar reglas de negocio, coordinar la interacción con los repositorios y gestionar las relaciones entre entidades.
Además incluye:

UserDetailsServiceImp
- Integración con Spring Security para cargar usuarios desde la base de datos durante el proceso de autenticación.

### repository
Cada entidad del dominio posee su correspondiente repositorio.

### model
Estas entidades modelan las relaciones del sistema, permitiendo:
* Asociar usuarios con roles
* Asignar permisos a roles
* Relacionar profesores y estudiantes con cursos

### dto
Contienen los Data Transfer Objects utilizados para recibir datos desde el cliente, enviar respuestas estructuradas y evitar exponer directamente las entidades del modelo.

## Aspectos Destacados del Proyecto
* Implementación de Spring Security para proteger la API
* Autenticación mediante JWT
* Integración de OAuth2 Authentication
* Sistema de control de acceso basado en roles (RBAC)
* Encriptado de contraseñas antes de su almacenamiento en base de datos mediante PasswordEncoder
* Protección de endpoints mediante reglas de autorización
* Uso de method security para validar permisos en métodos específicos
* Uso de DTOs para desacoplar la API del modelo de datos
* Uso de programación funcional (Streams, Optional y expresiones lambda)
* Aplicación de buenas prácticas de organización y separación de responsabilidades


## Estado del proyecto

Proyecto actualmente en desarrollo.  
Se continúan incorporando mejoras en seguridad, organización del código y funcionalidades adicionales.
