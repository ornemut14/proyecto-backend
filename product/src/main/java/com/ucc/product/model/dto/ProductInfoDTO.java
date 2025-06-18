package com.ucc.product.model.dto;

import java.io.Serializable;

public class ProductInfoDTO implements Serializable {
    private Long id;
    private String name;
    private CategoryDTO category;
    private Integer stock; // ✅ Nuevo campo

    public ProductInfoDTO() {
    }

    public ProductInfoDTO(Long id, String name, CategoryDTO category, Integer stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.stock = stock;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public Integer getStock() {
        return stock;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
