
package com.eam.parcial2.controller;

import com.eam.parcial2.model.Product;
import com.eam.parcial2.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        productRepository.deleteAll();
    }

    @Test
    public void createProduct_ShouldReturnCreatedProduct() throws Exception {
        // Arrange
        Product product = new Product(null, "Test Product", "Test Description", 99.99, 10);

        // Act & Assert
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Product\",\"description\":\"Test Description\",\"price\":99.99,\"stock\":10}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.description", is(product.getDescription())));
    }

    @Test
    public void getAllProducts_ShouldReturnAllProducts() throws Exception {
        // Arrange
        Product product1 = new Product(null, "Product 1", "Description 1", 10.0, 100);
        Product product2 = new Product(null, "Product 2", "Description 2", 20.0, 200);
        productRepository.saveAll(List.of(product1, product2));

        // Act & Assert
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)));
    }
}