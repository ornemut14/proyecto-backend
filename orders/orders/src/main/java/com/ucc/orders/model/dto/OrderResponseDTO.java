package com.ucc.orders.model.dto;

import java.time.LocalDateTime;

public class OrderResponseDTO {

    private Long id;
    private String productName;
    private Integer quantity;
    private LocalDateTime orderDate;

    public OrderResponseDTO() {}

    public OrderResponseDTO(Long id, String productName, Integer quantity, LocalDateTime orderDate) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
}
