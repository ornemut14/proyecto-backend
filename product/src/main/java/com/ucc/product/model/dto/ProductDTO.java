package com.ucc.product.model.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;

public class ProductDTO implements Serializable {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private double price;
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;
    @NotNull(message = "La categoría es obligatoria")
    private CategoryDTO categoryDTO;

    // Constructor vacío
    public ProductDTO() {
    }

    // Constructor completo
    public ProductDTO(String name, double price, int stock, CategoryDTO categoryDTO) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.categoryDTO = categoryDTO;}

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }
}

