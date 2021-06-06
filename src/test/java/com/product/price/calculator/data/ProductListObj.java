package com.product.price.calculator.data;

import com.product.price.calculator.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListObj {

    public static List<Product> getProductList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Penguin Ears", 20, 175.00));
        products.add(new Product(2, "Horseshoe", 5, 825.00));
        return products;
    }
}
