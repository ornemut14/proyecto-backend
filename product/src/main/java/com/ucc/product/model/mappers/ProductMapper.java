package com.ucc.product.model.mappers;

import com.ucc.product.model.dto.CategoryDTO;
import com.ucc.product.model.dto.ProductDTO;
import com.ucc.product.model.dto.ProductInfoDTO;
import com.ucc.product.model.entities.Category;
import com.ucc.product.model.entities.Product;
import com.ucc.product.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final CategoryRepository categoryRepository;

    // Constructor manual
    public ProductMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Convierte de DTO a Entidad
    public Product productsDTOToProductsEntity(ProductDTO productDTO) {
        Product productEntity = new Product();
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setStock(productDTO.getStock()); // ← agregado
        productEntity.setStatus(Boolean.TRUE);

        Long categoryId = productDTO.getCategoryDTO().getId();
        Category categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("No se encontró la categoría con ID " + categoryId));

        productEntity.setCategory(categoryEntity);
        return productEntity;
    }

    // Convierte de Entidad a ProductDTO
    public ProductDTO productEntityToProductDTO(Product productEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(productEntity.getName());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setStock(productEntity.getStock()); // ← agregado
        return productDTO;
    }

    // Convierte de Entidad a ProductInfoDTO (usado en GET /products/info o /products/{id})
    public ProductInfoDTO productEntityToProductInfoDTO(Product productEntity) {
        ProductInfoDTO dto = new ProductInfoDTO();
        dto.setId(productEntity.getId());
        dto.setName(productEntity.getName());

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(productEntity.getCategory().getId());

        dto.setCategory(categoryDTO);
        return dto;
    }
}
