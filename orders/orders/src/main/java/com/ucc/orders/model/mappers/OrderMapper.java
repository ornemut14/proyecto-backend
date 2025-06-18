package com.ucc.orders.model.mappers;

import com.ucc.orders.model.dto.OrderRequestDTO;
import com.ucc.orders.model.dto.OrderResponseDTO;
import com.ucc.orders.model.entities.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderMapper {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String PRODUCT_SERVICE_URL = "http://localhost:8080/api/products";

    public Order requestDtoToEntity(OrderRequestDTO dto) {
        Order order = new Order();
        order.setOrderDate(dto.getOrderDate());
        order.setQuantity(dto.getQuantity());
        order.setProductId(dto.getProductId());
        return order;
    }

    public OrderResponseDTO entityToResponseDto(Order entity) {
        String productName;

        try {
            // Llamada al endpoint que devuelve solo el nombre
            productName = restTemplate.getForObject(PRODUCT_SERVICE_URL + "/" + entity.getProductId() + "/name", String.class);
        } catch (Exception e) {
            productName = "Nombre no disponible";
        }

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(entity.getId());
        dto.setOrderDate(entity.getOrderDate());
        dto.setQuantity(entity.getQuantity());
        dto.setProductName(productName);

        return dto;
    }
}
