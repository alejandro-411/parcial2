package com.eam.parcial2.service;

import com.eam.parcial2.model.Product;
import com.eam.parcial2.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void findAll_ShouldReturnAllProducts() {
        // Arrange
        Product product1 = new Product(1L, "Product 1", "Description 1", 10.0, 100);
        Product product2 = new Product(2L, "Product 2", "Description 2", 20.0, 200);
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Act
        List<Product> products = productService.findAll();

        // Assert
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void findById_ExistingId_ShouldReturnProduct() {
        // Arrange
        Long id = 1L;
        Product product = new Product(id, "Product 1", "Description 1", 10.0, 100);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // Act
        Optional<Product> foundProduct = productService.findById(id);

        // Assert
        assertTrue(foundProduct.isPresent());
        assertEquals(id, foundProduct.get().getId());
        verify(productRepository, times(1)).findById(id);
    }


}