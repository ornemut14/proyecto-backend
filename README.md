SISTEMA DE GESTIÓN DE ÓRDENES Y PRODUCTOS

Este proyecto está compuesto por dos microservicios independientes desarrollados en Spring Boot: uno para productos y otro para órdenes. Ambos están organizados como módulos separados y se comunican entre sí mediante `RestTemplate`.

Estructura del Proyecto

microservicio/  
├── product/          → Microservicio de productos  
├── orders/           → Microservicio de órdenes  

Requisitos
- Java 17 o superior  
- Maven 3.8 o superior  
- (Opcional) Docker para la base de datos  
- (Opcional) Postman para pruebas  

Documentación con Swagger
Ambos microservicios están documentados con Swagger, usando configuración separada en la carpeta `config`. Esto permite visualizar y probar los endpoints desde el navegador:

- Microservicio de Productos: [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)  
- Microservicio de Órdenes: [http://localhost:8081/swagger-ui/index.html#/](http://localhost:8081/swagger-ui/index.html#/)  


Comunicación entre Microservicios
El microservicio de órdenes consulta al microservicio de productos para:

- Verificar si el producto existe  
- Consultar el stock disponible  
- Obtener el nombre del producto para mostrar en las órdenes  

Funcionalidades Principales
- Microservicio de Productos:
- Crear una categoría
- Obtener todas las categorías
- Obtener una categoría por ID
- Actualizar una categoría
- Eliminar una categoría
- Crear un producto
- Obtener todos los productos
- Obtener un producto por ID
- Actualizar un producto
- Eliminar un producto

Microservicio de Órdenes:
- Crear una orden  
- Obtener todas las órdenes  
- Obtener una orden por ID  
- Actualizar una orden  
- Eliminar una orden  

Pruebas con Postman
Categorias: 

POST 'http://localhost:8080/api/categories'
```json
{
  "name": "Bebidas"
}
```

PUT http://localhost:8080/api/categories/1
```json
{
  "name": "Bebidas Naturales"
}
```

GET: http://localhost:8080/api/categories
GET: http://localhost:8080/api/categories/1
DELETE: http://localhost:8080/api/categories/1

Productos:

POST `http://localhost:8080/api/products`
```json
{
  "name": "Yerba Mate",
  "price": 950.0,
  "stock": 100,
  "categoryDTO": {
    "id": 1
  }
}
```

PUT `http://localhost:8080/api/products/1`
```json
{
  "name": "Yerba Mate Premium",
  "price": 1050.0,
  "stock": 120,
  "categoryId": 1
}
```

GET: `http://localhost:8080/api/products`  
DELETE: `http://localhost:8080/api/products/{id}`

---

Órdenes:

POST `http://localhost:8081/api/orders`
```json
{
  "productId": 1,
  "quantity": 2
}
```

PUT `http://localhost:8081/api/orders/1`
```json
{
  "productId": 1,
  "quantity": 3
}
```

GET: `http://localhost:8081/api/orders`  
DELETE: `http://localhost:8081/api/orders/{id}`

Autoría

- Candela Barboza  
- Ornella Mut
