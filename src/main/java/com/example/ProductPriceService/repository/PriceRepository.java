package com.example.ProductPriceService.repository;

import com.example.ProductPriceService.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Price. Proporciona métodos para acceder a los precios en la base de datos.
 */

public interface PriceRepository extends JpaRepository<Price, Long> {

    /**
     * Encuentra el precio aplicable para un producto y marca en un rango de fechas, ordenado por prioridad.
     * @param productId ID del producto.
     * @param brandId ID de la marca.
     * @param startDate Fecha de inicio.
     * @param endDate Fecha de fin.
     * @return Optional conteniendo el precio encontrado o vacío si no se encuentra.
     */

    Optional<Price> findTopByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Integer productId, Integer brandId, LocalDateTime startDate, LocalDateTime endDate);
}
