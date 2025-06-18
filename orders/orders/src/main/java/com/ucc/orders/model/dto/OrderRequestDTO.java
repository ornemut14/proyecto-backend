package com.ucc.orders.model.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class OrderRequestDTO {

    private Long id;
    @NotNull(message = "El ID del producto es obligatorio")
    private Long productId;
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer quantity;
    private LocalDateTime orderDate;

    // Constructor vacío
    public OrderRequestDTO() {}

    // Constructor completo
    public OrderRequestDTO(Long id, Long productId, Integer quantity, LocalDateTime orderDate) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
