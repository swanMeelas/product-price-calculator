package com.product.price.calculator.controller;

import com.product.price.calculator.dto.OptimizedPriceDetails;
import com.product.price.calculator.dto.Price;
import com.product.price.calculator.model.Product;
import com.product.price.calculator.service.ProductPriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8085"})
@RestController
@RequestMapping("/products")
public class ProductPriceController {

    private final ProductPriceService productPriceService;

    public ProductPriceController(ProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }

    @GetMapping
    ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productPriceService.findAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productPriceService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);

    }

    @PostMapping(value = "/price")
    ResponseEntity<OptimizedPriceDetails> calculatePrice(@RequestBody Price price) {
        OptimizedPriceDetails optimizedPriceDetails = productPriceService.calculatePrice(price);
        return new ResponseEntity<>(optimizedPriceDetails, HttpStatus.OK);
    }

    @GetMapping(value = "/price/{id}")
    ResponseEntity<List<Price>> findPrice(@PathVariable Integer id) {
        List<Price> priceList = productPriceService.ListPriceRange(id);
        return new ResponseEntity<>(priceList, HttpStatus.OK);
    }

}
