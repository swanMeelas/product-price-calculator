package com.product.price.calculator.service;

import com.product.price.calculator.data.ProductListObj;
import com.product.price.calculator.dto.OptimizedPriceDetails;
import com.product.price.calculator.dto.Price;
import com.product.price.calculator.model.Product;
import com.product.price.calculator.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductPriceServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductPriceService productPriceService;

    private List<Product> products;
    private Optional<Product> product;


    @BeforeEach
    void setUp() {
        products = ProductListObj.getProductList();
        product = products.stream().filter(m -> m.getId() == 1).findFirst();
    }

    @Test
    void findAllProducts() {
        when(productRepository.findAll()).thenReturn(products);
        List<Product> products = productPriceService.findAllProducts();
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals(20, products.get(0).getUnits());
    }

    @Test
    void saveProduct() {
        Product productSave = new Product(1, "Penguin Ears", 20, 175.00);
        when(productRepository.save(productSave)).thenReturn(products.get(0));
        Product product = productPriceService.saveProduct(productSave);
        assertNotNull(product);
        assertEquals(1, product.getId());
        assertEquals(20, product.getUnits());
        assertEquals(175.00, product.getCartonPrice());
        assertEquals("Penguin Ears", product.getName());
    }

    @Test
    void findPriceRange() {
        when(productRepository.findById(anyInt())).thenReturn(product);
        List<Price> price = productPriceService.ListPriceRange(1);
        assertNotNull(price);
        assertEquals(50, price.size());
        assertEquals(350.0, price.get(39).getPrice());
        assertEquals(216.13, price.get(18).getPrice());
        assertEquals(175.0, price.get(19).getPrice());
        assertEquals(186.38, price.get(20).getPrice());
    }

    @Test
    void getProduct() {
        when(productRepository.findById(anyInt())).thenReturn(product);
        Product product = productPriceService.getProduct(1);
        assertNotNull(product);
    }

    @Test
    void calculatePrice() {
        when(productRepository.findById(anyInt())).thenReturn(product);
        OptimizedPriceDetails optimizedPriceDetails = productPriceService.calculatePrice(new Price(1, 20));
        assertEquals(175.0, optimizedPriceDetails.getTotalPrice());
    }
}