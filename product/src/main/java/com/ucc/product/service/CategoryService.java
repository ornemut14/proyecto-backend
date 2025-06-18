package com.ucc.product.service;

import com.ucc.product.model.entities.Category;
import com.ucc.product.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Obtener todas las categorías
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    // Crear nueva categoría
    public ResponseEntity<Object> newCategory(Category category) {
        categoryRepository.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Eliminar categoría
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada con ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    // Buscar categoría por ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Actualizar categoría (opcional)
    public Category updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));

        existingCategory.setName(updatedCategory.getName());
        existingCategory.setStatus(updatedCategory.getStatus());

        return categoryRepository.save(existingCategory);
    }
}
