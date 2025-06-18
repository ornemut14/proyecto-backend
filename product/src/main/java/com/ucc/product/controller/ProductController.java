package com.ucc.product.controller;

import com.ucc.product.model.entities.Product;
import com.ucc.product.model.dto.ProductDTO;
import com.ucc.product.model.dto.ProductInfoDTO;
import com.ucc.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Obtener todos los productos (completos)
    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    // Obtener todos los productos (info con DTO)
    @GetMapping("/info")
    public List<ProductInfoDTO> getProductsDTO() {
        return productService.getAllInfoProducts();
    }

    // Obtener producto por ID (con DTO)
    @GetMapping("/{id}")
    public ProductInfoDTO getProductById(@PathVariable Long id) {
        return productService.getProductDTOById(id);
    }

    // ✅ NUEVO: Obtener solo el nombre del producto (para microservicio de órdenes)
    @GetMapping("/{id}/name")
    public ResponseEntity<String> getProductName(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalProduct.get().getName());
    }

    // Crear nuevo producto
    @PostMapping
    public ResponseEntity<Object> newProducts(@RequestBody ProductDTO product) {
        return productService.newProduct(product);
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    // Para probar manejo de excepciones
    @GetMapping("/price")
    public Product getProductsPrice() {
        productService.manejoExcepciones();
        return new Product();
    }

    // Verificar si existe un producto (usado por microservicio de órdenes)
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> checkIfProductExists(@PathVariable Long id) {
        boolean exists = productService.getProductById(id).isPresent();
        return ResponseEntity.ok(exists);
    }

    // Obtener stock de un producto por ID
    @GetMapping("/{id}/stock")
    public ResponseEntity<Integer> getProductStock(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalProduct.get().getStock());
    }

    // Descontar stock
    @PutMapping("/{id}/stock")
    public ResponseEntity<Void> reduceProductStock(@PathVariable Long id, @RequestParam("quantity") Integer quantity) {
        productService.reduceStock(id, quantity);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/stock/add")
    public ResponseEntity<Void> increaseStock(@PathVariable Long id, @RequestParam Integer quantity) {
        productService.increaseStock(id, quantity);
        return ResponseEntity.ok().build();
    }

}

