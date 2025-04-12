#!/bin/bash

# Configuración
BASE_URL="http://localhost:8080/api/products"
HEADERS="Content-Type: application/json"

# Función para verificar respuestas HTTP
check_response() {
    if [ $1 -ne $2 ]; then
        echo "Error: Esperado código $2, recibido $1"
        echo "Respuesta: $3"
        exit 1
    fi
}

# 1. Crear un producto
echo "Creando producto..."
RESPONSE=$(curl -s -o response.json -w "%{http_code}" -X POST -H "$HEADERS" -d '{
    "name": "Producto Test",
    "description": "Descripción de prueba",
    "price": 99.99,
    "stock": 10
}' $BASE_URL)

check_response $RESPONSE 201
PRODUCT_ID=$(jq -r '.id' response.json)
echo "Producto creado con ID: $PRODUCT_ID"

# 2. Obtener el producto creado
echo "Obteniendo producto..."
RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" "$BASE_URL/$PRODUCT_ID")
check_response $RESPONSE 200

# 3. Listar todos los productos
echo "Listando productos..."
curl -H "$HEADERS" -s $BASE_URL | jq

# 4. Actualizar el producto
echo "Actualizando producto..."
RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" -X PUT -H "$HEADERS" -d '{
    "name": "Producto Actualizado",
    "description": "Nueva descripción",
    "price": 149.99,
    "stock": 5
}' "$BASE_URL/$PRODUCT_ID")
check_response $RESPONSE 200

# 5. Verificar actualización
echo "Verificando actualización..."
curl -H "$HEADERS" -s "$BASE_URL/$PRODUCT_ID" | jq

# 6. Eliminar el producto
echo "Eliminando producto..."
RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" -X DELETE "$BASE_URL/$PRODUCT_ID")
check_response $RESPONSE 204

# 7. Verificar eliminación
echo "Verificando eliminación..."
RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" "$BASE_URL/$PRODUCT_ID")
check_response $RESPONSE 404

echo "Todas las pruebas pasaron exitosamente!"