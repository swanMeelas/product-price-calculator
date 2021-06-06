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

    /**
     * retrieve all products from DB
     *
     * @return list of products
     */
    @GetMapping
    ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productPriceService.findAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    /**
     * save product details in DB
     *
     * @param product product object
     * @return saved product
     */
    @PostMapping
    ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productPriceService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);

    }

    /**
     * Calculate total price and apply discount if required for given number of quantities of product
     *
     * @param price productID and quantity
     * @return price details
     */
    @PostMapping(value = "/price")
    ResponseEntity<OptimizedPriceDetails> calculatePrice(@RequestBody Price price) {
        OptimizedPriceDetails optimizedPriceDetails = productPriceService.calculatePrice(price);
        return new ResponseEntity<>(optimizedPriceDetails, HttpStatus.OK);
    }

    /**
     * retrieve all price ranges from 1-50 units for a product
     *
     * @param id product id
     * @return list of prices range for a particular product
     */
    @GetMapping(value = "/price/{id}")
    ResponseEntity<List<Price>> findPrice(@PathVariable Integer id) {
        List<Price> priceList = productPriceService.ListPriceRange(id);
        return new ResponseEntity<>(priceList, HttpStatus.OK);
    }

}
