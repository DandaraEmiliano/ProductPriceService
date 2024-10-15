package com.example.ProductPriceService.controller;

import com.example.ProductPriceService.model.Price;
import com.example.ProductPriceService.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Clase de pruebas para el controlador PriceController, centrada en el endpoint de consulta de precios.
 * Utiliza MockMvc para simular solicitudes HTTP y verificar las respuestas.
 */

@WebMvcTest(PriceController.class)
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * Método de configuración que inicializa las anotaciones de Mockito.
     */

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Prueba para verificar la respuesta del precio a las 10 AM en una fecha específica.
     * Simula una respuesta válida del servicio PriceService.
     */

    @Test
    public void testGetPriceAt10AM() throws Exception {
        Price price = new Price();
        price.setProductId(35455);
        price.setBrandId(1);
        price.setPriceList(1);
        price.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00", formatter));
        price.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59", formatter));
        price.setPrice(35.50);
        price.setCurrency("EUR");

        when(priceService.findPrice(eq(35455), eq(1), any(LocalDateTime.class))).thenReturn(Optional.of(price));

        mockMvc.perform(get("/api/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    /**
     * Prueba para verificar la respuesta del precio a las 16 PM en una fecha específica.
     * Simula una respuesta válida con una tarifa diferente.
     */

    @Test
    public void testGetPriceAt16PM() throws Exception {
        Price price = new Price();
        price.setProductId(35455);
        price.setBrandId(1);
        price.setPriceList(2);
        price.setStartDate(LocalDateTime.parse("2020-06-14T15:00:00", formatter));
        price.setEndDate(LocalDateTime.parse("2020-06-14T18:30:00", formatter));
        price.setPrice(25.45);
        price.setCurrency("EUR");

        when(priceService.findPrice(eq(35455), eq(1), any(LocalDateTime.class))).thenReturn(Optional.of(price));

        mockMvc.perform(get("/api/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T16:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    /**
     * Prueba para verificar la respuesta del controlador cuando no se encuentra un precio a las 21 PM.
     * Simula una respuesta vacía del servicio PriceService.
     */

    @Test
    public void testGetPriceAt21PM() throws Exception {
        when(priceService.findPrice(eq(35455), eq(1), any(LocalDateTime.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T21:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
