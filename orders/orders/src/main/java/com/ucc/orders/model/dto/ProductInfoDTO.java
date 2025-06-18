package com.ucc.orders.model.dto;

public class ProductInfoDTO {

    private String name;

    public ProductInfoDTO() {}

    public ProductInfoDTO(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
