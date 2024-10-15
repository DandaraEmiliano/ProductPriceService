package com.example.ProductPriceService.service;

import com.example.ProductPriceService.model.Price;
import com.example.ProductPriceService.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Servicio que proporciona la lógica de negocio para la consulta de precios.
 */

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;

    /**
     * Busca el precio aplicable para un producto y marca en una fecha específica.
     * @param productId ID del producto.
     * @param brandId ID de la marca.
     * @param applicationDate Fecha para la cual se consulta el precio.
     * @return Optional con el precio correspondiente o vacío si no se encuentra.
     */

    public Optional<Price> findPrice(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        return priceRepository.findTopByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                productId, brandId, applicationDate, applicationDate);
    }
}
