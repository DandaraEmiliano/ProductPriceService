package com.example.ProductPriceService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Manejador global de excepciones para proporcionar respuestas consistentes en caso de error.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Maneja la excepción cuando no se encuentra un precio.
     * @param ex Excepción lanzada cuando el precio no se encuentra.
     * @param request La solicitud web actual.
     * @return ResponseEntity con mensaje de error y estado 404.
     */

    @ExceptionHandler(PriceNotFoundException.class)
    protected ResponseEntity<Object> handlePriceNotFound(PriceNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Maneja cualquier otra excepción no específica lanzada en la aplicación.
     * @param ex Excepción lanzada.
     * @param request La solicitud web actual.
     * @return ResponseEntity con mensaje de error y estado 500.
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        String errorMessage = "Error inesperado: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
