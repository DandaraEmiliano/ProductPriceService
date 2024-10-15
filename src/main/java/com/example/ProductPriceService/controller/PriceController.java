package com.example.ProductPriceService.controller;

import com.example.ProductPriceService.model.Price;
import com.example.ProductPriceService.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controlador REST para manejar las solicitudes relacionadas con los precios.
 * Proporciona un endpoint para consultar el precio de un producto en una fecha específica.
 */
@RestController
@RequestMapping("/api/prices")
public class PriceController {
    @Autowired
    private PriceService priceService;

    /**
     * Endpoint GET para obtener el precio de un producto basado en su ID, marca y fecha de aplicación.
     * @param productId ID del producto.
     * @param brandId ID de la marca.
     * @param applicationDate Fecha para la cual se busca el precio.
     * @return ResponseEntity con el precio correspondiente o un estado 404 si no se encuentra.
     */

    @GetMapping
    public ResponseEntity<Price> getPrice(
            @RequestParam Integer productId,
            @RequestParam Integer brandId,
            @RequestParam String applicationDate) {

        LocalDateTime date = LocalDateTime.parse(applicationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        return priceService.findPrice(productId, brandId, date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
