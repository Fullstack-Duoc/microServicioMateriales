# Guía de Uso: API de Gestión de Materiales

Esta API permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre el catálogo de materiales de minería.

## Configuración Base
- **URL Base:** `http://localhost:8086/api/v1/materiales`
- **Puerto:** `8086`
- **Formato de datos:** `JSON`

---

## Endpoints y Métodos REST

### 1. Crear un nuevo Material
Crea un registro de material en la base de datos.

- **Método:** `POST`
- **URL:** `/`
- **Cuerpo (JSON):**
```json
{
  "nombre": "Cobre de Alta Pureza",
  "clasificacion": "Metálico",
  "densidadPromedio": 8.96
}
```
- **Respuesta Exitosa:** `201 Created`

### 2. Listar todos los Materiales
Obtiene una lista completa de todos los materiales registrados.

- **Método:** `GET`
- **URL:** `/`
- **Respuesta Exitosa:** `200 OK` (Array de objetos JSON)

### 3. Obtener Material por ID
Busca un material específico utilizando su identificador único.

- **Método:** `GET`
- **URL:** `/{id}` (Ejemplo: `/1`)
- **Respuesta Exitosa:** `200 OK`

### 4. Actualizar Material (Completo)
Reemplaza todos los datos de un material existente.

- **Método:** `PUT`
- **URL:** `/{id}` (Ejemplo: `/1`)
- **Cuerpo (JSON):**
```json
{
  "nombre": "Cobre Refinado",
  "clasificacion": "Metálico Industrial",
  "densidadPromedio": 9.02
}
```
- **Respuesta Exitosa:** `200 OK`

### 5. Actualización Parcial de Densidad
Modifica únicamente el valor de la densidad de un material.

- **Método:** `PATCH`
- **URL:** `/{id}/densidad?valor={nueva_densidad}`
- **Ejemplo:** `/1/densidad?valor=9.15`
- **Respuesta Exitosa:** `200 OK`

### 6. Buscar por Clasificación
Filtra los materiales según su categoría o clasificación.

- **Método:** `GET`
- **URL:** `/buscar?clasificacion={criterio}`
- **Ejemplo:** `/buscar?clasificacion=Metálico`
- **Respuesta Exitosa:** `200 OK`

### 7. Eliminar Material
Elimina permanentemente un registro por su ID.

- **Método:** `DELETE`
- **URL:** `/{id}` (Ejemplo: `/1`)
- **Respuesta Exitosa:** `204 No Content`

---

## Reglas de Validación y Negocio

Para que las peticiones sean exitosas, debes considerar lo siguiente:

1.  **Nombre:** No puede estar vacío y debe ser único en el sistema (Case Insensitive).
2.  **Clasificación:** Es obligatoria y no puede estar en blanco.
3.  **Densidad:** Debe ser un número estrictamente mayor a `0`.

## Manejo de Errores

Si algo sale mal, la API responderá con un objeto de error detallado:

```json
{
  "timestamp": "2024-05-24T21:45:00",
  "mensaje": "Error de validacion en los datos",
  "detalles": "densidadPromedio: La densidad promedio debe ser mayor a 0"
}
```

### Códigos de Estado Comunes:
- `200 OK`: Operación exitosa.
- `201 Created`: Recurso creado con éxito.
- `204 No Content`: Eliminación exitosa.
- `400 Bad Request`: Error en los datos enviados o violación de reglas de negocio (ej. nombre duplicado).
- `404 Not Found`: El ID solicitado no existe.

---

## Documentación Interactiva
Puedes probar estos endpoints visualmente accediendo a Swagger UI mientras la aplicación está corriendo:
`http://localhost:8086/swagger-ui/index.html`