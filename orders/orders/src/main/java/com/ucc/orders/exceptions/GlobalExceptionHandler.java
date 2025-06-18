/*package com.ucc.orders.exceptions;

import com.ucc.orders.exceptions.dto.ErrorMensajeDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMensajeDTO> handleOrderNotFound(OrderNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMensajeDTO(404, ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(ProductCommunicationException.class)
    public ResponseEntity<ErrorMensajeDTO> handleProductCommunication(ProductCommunicationException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorMensajeDTO(503, ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorMensajeDTO> handleInsufficientStock(InsufficientStockException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMensajeDTO(400, ex.getMessage(), request.getRequestURI()));}
}*/

package com.ucc.orders.exceptions;

import com.ucc.orders.exceptions.dto.ErrorMensajeDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMensajeDTO> handleProductNotFound(
            HttpServletRequest request,
            ProductNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorMensajeDTO(404, ex.getMessage(), request.getRequestURI()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorMensajeDTO> handleStock(HttpServletRequest request, InsufficientStockException ex) {
        return new ResponseEntity<>(
                new ErrorMensajeDTO(400, ex.getMessage(), request.getRequestURI()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMensajeDTO> handleOrderNotFound(HttpServletRequest request, OrderNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorMensajeDTO(404, ex.getMessage(), request.getRequestURI()),
                HttpStatus.NOT_FOUND
        );
    }

    // Maneja cualquier otra excepción no controlada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMensajeDTO> handleGeneralException(HttpServletRequest request, Exception ex) {
        return new ResponseEntity<>(
                new ErrorMensajeDTO(500, "Error interno del servidor", request.getRequestURI()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
