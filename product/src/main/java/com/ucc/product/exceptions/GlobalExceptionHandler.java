package com.ucc.product.exceptions;

import com.ucc.product.exceptions.dto.ErrorMensajeDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.ucc.product.exceptions.Product.ProductNotExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ErrorMensajeDTO> productNotExist(HttpServletRequest request, ProductNotExistException ex) {
        ErrorMensajeDTO errorMensajeDTO = new ErrorMensajeDTO(999, ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorMensajeDTO, HttpStatus.FAILED_DEPENDENCY);
    }

}

