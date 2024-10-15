package com.example.ProductPriceService.exception;

/**
 * Excepción personalizada lanzada cuando no se encuentra un precio para un producto específico.
 */
public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(String message) {
        super(message);
    }
}
