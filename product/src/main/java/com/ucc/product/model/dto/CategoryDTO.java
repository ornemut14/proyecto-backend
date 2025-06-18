package com.ucc.product.model.dto;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private Long id;

    public CategoryDTO() {
        // Constructor vacío
    }

    public CategoryDTO(Long id) {
        this.id = id;
    }

    // Getter
    public Long getId() {
        return id;
    }

    // Setter
    public void setId(Long id) {
        this.id = id;
    }
}

