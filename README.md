# Microservicio CRUD Spring Boot con Docker y MariaDB

**Un microservicio completo que implementa operaciones CRUD, contenerizado con Docker y orquestado mediante Docker Compose.**

---

## 📌 Características Principales
- **CRUD Completo**: API REST para gestionar entidades (ej: Productos) con operaciones básicas.
- **Arquitectura en Capas**: Controladores, Servicios y Repositorios bien estructurados.
- **Contenerización**:
  - Dockerfile con Multi-Stage Build para optimización.
  - Docker Compose para orquestar el microservicio y MariaDB.
- **Persistencia de Datos**: 
  - Volúmenes Docker para MariaDB.
  - Script de respaldo de la base de datos.
- **Pruebas Automatizadas**:
  - Pruebas unitarias (JUnit + Mockito).
  - Pruebas de integración.
  - Reporte de cobertura con JaCoCo.
- **Postman**:
  - Colección con todos los endpoints.
  - Pruebas pre-construidas para validar el CRUD.
- **Despliegue**:
  - Imagen pública en Docker Hub.
  - (Opcional) Script de automatización Bash/PowerShell.

---

## 🛠 Requisitos Previos
- Docker y Docker Compose instalados.
- JDK 17+.
- Maven.
- Postman (para probar endpoints).

---

##  Instalación y Uso

### 1. Clonar el repositorio

git clone https://github.com/tuusuario/microservicio-crud.git
cd microservicio-crud

### Ejecutar con Docker Compones

docker-compose up --build

### Acceder a la API

- URL Base: http://localhost:8080/api/products

  #### Endpoints:
  - GET /api/products: Listar todos los productos.
  - GET /api/products/{id}: Obtener un producto por ID
  - POST /api/products: Crear un nuevo producto
  - Delete /api/products/{id}: Eliminar un producto

 ### Estructura del Proyecto
 microservicio-crud/
├── src/                 # Código fuente del microservicio
├── docker/              # Configuraciones de Docker
├── postman/            # Colección Postman exportada
├── reports/            # Reportes de pruebas y cobertura
├── Dockerfile          # Configuración multi-etapa
├── docker-compose.yml  # Orquestación de servicios
└── .dockerignore       # Archivos excluidos en Docker

### Tecnologías Utilizadas
  -Backend: Spring Boot 3, Spring Data JPA.

  -Base de Datos: MariaDB.

  -Contenerización: Docker, Docker Compose.

  -Pruebas: JUnit 5, Mockito, JaCoCo.

  -Herramientas: Postman, Maven.
