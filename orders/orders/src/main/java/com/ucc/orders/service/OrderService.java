package com.ucc.orders.service;

import com.ucc.orders.exceptions.InsufficientStockException;
import com.ucc.orders.exceptions.OrderNotFoundException;
import com.ucc.orders.exceptions.ProductNotFoundException;
import com.ucc.orders.model.dto.OrderRequestDTO;
import com.ucc.orders.model.dto.OrderResponseDTO;
import com.ucc.orders.model.entities.Order;
import com.ucc.orders.model.mappers.OrderMapper;
import com.ucc.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String PRODUCT_SERVICE_URL = "http://localhost:8080/api/products";

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

  
    private HttpHeaders createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("user", "1234");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }


    public OrderResponseDTO createOrder(OrderRequestDTO dto) throws ProductNotFoundException {
        HttpEntity<String> entity = new HttpEntity<>(createAuthHeaders());

        // Validar existencia
        ResponseEntity<Boolean> existsResponse = restTemplate.exchange(
                PRODUCT_SERVICE_URL + "/exists/" + dto.getProductId(),
                HttpMethod.GET,
                entity,
                Boolean.class
        );
        Boolean exists = existsResponse.getBody();
        if (exists == null || !exists) {
            throw new ProductNotFoundException("El producto con ID " + dto.getProductId() + " no existe.");
        }


        // Validar stock
        ResponseEntity<Integer> stockResponse = restTemplate.exchange(
                PRODUCT_SERVICE_URL + "/" + dto.getProductId() + "/stock",
                HttpMethod.GET,
                entity,
                Integer.class
        );
        Integer stock = stockResponse.getBody();
        if (stock == null || stock < dto.getQuantity()) {
            throw new InsufficientStockException("Stock insuficiente para el producto con ID " + dto.getProductId());
        }

        // Descontar stock
        restTemplate.exchange(
                PRODUCT_SERVICE_URL + "/" + dto.getProductId() + "/stock?quantity=" + dto.getQuantity(),
                HttpMethod.PUT,
                entity,
                Void.class
        );

        // Guardar orden
        Order order = orderMapper.requestDtoToEntity(dto);
        Order saved = orderRepository.save(order);
        return orderMapper.entityToResponseDto(saved);
    }

    // 🔹 Editar orden
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO dto) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Orden con ID " + id + " no encontrada"));

        HttpEntity<String> entity = new HttpEntity<>(createAuthHeaders());

        
        restTemplate.exchange(
                PRODUCT_SERVICE_URL + "/" + existingOrder.getProductId() + "/stock/add?quantity=" + existingOrder.getQuantity(),
                HttpMethod.PUT,
                entity,
                Void.class
        );


        ResponseEntity<Boolean> existsResponse = restTemplate.exchange(
                PRODUCT_SERVICE_URL + "/exists/" + dto.getProductId(),
                HttpMethod.GET,
                entity,
                Boolean.class
        );
        Boolean exists = existsResponse.getBody();
        if (exists == null || !exists) {
            throw new ProductNotFoundException("El producto con ID " + dto.getProductId() + " no existe.");
        }

        ResponseEntity<Integer> stockResponse = restTemplate.exchange(
                PRODUCT_SERVICE_URL + "/" + dto.getProductId() + "/stock",
                HttpMethod.GET,
                entity,
                Integer.class
        );
        Integer stock = stockResponse.getBody();
        if (stock == null || stock < dto.getQuantity()) {
            throw new InsufficientStockException("Stock insuficiente para el producto con ID " + dto.getProductId());
        }

        
        restTemplate.exchange(
                PRODUCT_SERVICE_URL + "/" + dto.getProductId() + "/stock?quantity=" + dto.getQuantity(),
                HttpMethod.PUT,
                entity,
                Void.class
        );

        
        existingOrder.setProductId(dto.getProductId());
        existingOrder.setQuantity(dto.getQuantity());
        existingOrder.setOrderDate(dto.getOrderDate());

        Order saved = orderRepository.save(existingOrder);
        return orderMapper.entityToResponseDto(saved);
    }

  
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    
    public Optional<OrderResponseDTO> getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::entityToResponseDto);
    }

 
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

