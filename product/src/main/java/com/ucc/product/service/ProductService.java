/*package com.ucc.product.service;

import com.ucc.product.exceptions.Product.ProductNotExistException;
import com.ucc.product.model.entities.Product;
import com.ucc.product.model.dto.ProductDTO;
import com.ucc.product.model.dto.ProductInfoDTO;
import com.ucc.product.model.mappers.ProductMapper;
import com.ucc.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<Product> getProducts()
    {
        return productRepository.findAll();
    }

    public List<ProductInfoDTO> getAllInfoProducts()
    {
        return productRepository.findAll()
                .stream()
                .map(productEntity -> new ProductInfoDTO(
                        productEntity.getId(),
                        productEntity.getName(),
                        productEntity.getCategory()
                ))
                .collect(Collectors.toList());

    }


    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new ProductNotExistException("No se encontro el producto con el id indicado " + id);
        }
    }


   /* metodo con mapper*/
    /*public ResponseEntity<Object> newProduct(ProductDTO productDTO) {
     Product productEntity = productMapper.productsDTOToProductsEntity(productDTO);
     productRepository.save(productEntity);
     return new ResponseEntity<>(HttpStatus.CREATED);
}

public List<Product> getProductByPriceDesc(){
        return productRepository.findAllByOrderPriceDesc();
}




    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }


    public Product updateProduct(Long id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        // Actualizar los campos
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());

        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        productRepository.deleteById(id);
    }

    public void manejoExcepciones() {
        try {
            int resultado = 10 / 0;
        } catch (ArithmeticException arithmeticException){
            System.out.println("Error aritmetico en la operacion" + arithmeticException.getMessage());
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Ejecucion del programa finalizada");
        }
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

}*/

package com.ucc.product.service;

import com.ucc.product.exceptions.Product.ProductNotExistException;
import com.ucc.product.model.dto.CategoryDTO;
import com.ucc.product.model.dto.ProductDTO;
import com.ucc.product.model.dto.ProductInfoDTO;
import com.ucc.product.model.entities.Product;
import com.ucc.product.model.mappers.ProductMapper;
import com.ucc.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<ProductInfoDTO> getAllInfoProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductInfoDTO(
                        product.getId(),
                        product.getName(),
                        new CategoryDTO(product.getCategory().getId()),
                        product.getStock()
                ))
                .collect(Collectors.toList());
    }

    public ProductInfoDTO getProductDTOById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotExistException("No se encontró el producto con ID " + id));

        return new ProductInfoDTO(
                product.getId(),
                product.getName(),
                new CategoryDTO(product.getCategory().getId()),
                product.getStock()
        );
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public ResponseEntity<Object> newProduct(ProductDTO productDTO) {
        Product productEntity = productMapper.productsDTOToProductsEntity(productDTO);
        productRepository.save(productEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public List<Product> getProductByPriceDesc() {
        return productRepository.findAllByOrderPriceDesc();
    }

    public Product updateProduct(Long id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());

        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        productRepository.deleteById(id);
    }

    public void manejoExcepciones() {
        try {
            int resultado = 10 / 0;
        } catch (ArithmeticException arithmeticException) {
            System.out.println("Error aritmético en la operación: " + arithmeticException.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Ejecución del programa finalizada");
        }
    }

    // ✅ Método nuevo para descontar stock
    public void reduceStock(Long id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Stock insuficiente para el producto con ID: " + id);
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    public void increaseStock(Long id, int quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotExistException("Producto no encontrado con id: " + id));

        product.setStock(product.getStock() + quantity);
        productRepository.save(product);
    }

}
