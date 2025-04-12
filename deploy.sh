#!/bin/bash

# Construir la imagen Docker
docker-compose build

# Ejecutar los contenedores
docker-compose up -d

# Esperar a que la aplicación esté lista
echo "Esperando a que la aplicación esté lista..."
sleep 15

# Ejecutar pruebas
echo "Ejecutando pruebas..."
docker exec app mvn test

# Generar reporte de cobertura
docker exec app mvn jacoco:report

# Mostrar información
echo "Aplicación disponible en http://localhost:8080"
echo "Base de datos disponible en localhost:3306"
