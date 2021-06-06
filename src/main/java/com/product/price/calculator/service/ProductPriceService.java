package com.product.price.calculator.service;

import com.product.price.calculator.dto.OptimizedPriceDetails;
import com.product.price.calculator.dto.Price;
import com.product.price.calculator.exception.ErrorCodes;
import com.product.price.calculator.exception.PCException;
import com.product.price.calculator.model.Product;
import com.product.price.calculator.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class ProductPriceService {
    public static final int PRICE_QUANTITY = 50;
    private final ProductRepository productRepository;

    public ProductPriceService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        try {
            List<Product> productList = productRepository.findAll();
            if (productList.size() != 0) {
                return productList;
            } else return new ArrayList<>();
        } catch (Exception e) {
            throw new PCException(ErrorCodes.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public Product saveProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new PCException(ErrorCodes.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

   /* public Map<Integer, Double> priceRangeList(String price, Integer units) {
        int qty = 50;
        double cartonPrice = Double.parseDouble(price);
        double unitPrice = cartonPrice / 20;
        int noOfCartons = qty / units;
        Map<Integer, Double> priceRange = new HashMap<>();
        int count = 0;
        for (int x = 0; x <= noOfCartons; x++) {
            for (int n = 0; n < units; n++) {
                priceRange.put(count++, x * cartonPrice + n * unitPrice * 1.3);
                if (count == 51) {
                    break;
                }
            }

        }
        return priceRange;
    }*/



    /*public Map<Integer, Double> findPrice(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product product1 = product.get();
            return findPrice(product1.getUnits(), product1.getCartonPrice().toString(), 50, new HashMap<>());
        }
        return new HashMap<>();
    }*/

    public List<Price> ListPriceRange(Integer id) {
        Product product = getProduct(id);
        if (product != null) {
            return findPriceRange(product, PRICE_QUANTITY, new ArrayList<>());
        }
        return new ArrayList<>();
    }

    public Product getProduct(Integer id) {
        Product product = null;
        try {
            Optional<Product> productById = productRepository.findById(id);
            if (productById.isPresent()) {
                return productById.get();
            }
        } catch (Exception e) {
            throw new PCException(ErrorCodes.DATABASE_EXCEPTION, e.getMessage());
        }
        return product;

    }

    public List<Price> findPriceRange(Product product, Integer quantity, List<Price> priceList) {
        if (quantity > 0) {
            double productPrice = getProductPrice(product.getUnits(), product.getCartonPrice(), quantity);
            priceList.add(new Price(quantity, Math.round(productPrice*100.0)/100.0));
            return findPriceRange(product, quantity - 1, priceList);
        }
        priceList.sort(Comparator.comparing(Price::getId));
        return priceList;
    }

    /*public Map<Integer, Double> findPrice(Integer units, String price, Integer quantity, Map<Integer, Double> map) {
        if (quantity > 0) {
            double productPrice = getProductPrice(units, price, quantity);
            map.put(quantity, productPrice);
            return findPrice(units, price, quantity - 1, map);
        }
        return map;
    }*/

    private double getProductPrice(Integer units, Double cartonPrice, Integer quantity) {
        double unitPrice = cartonPrice / 20;
        int noOfCartons = quantity / units;
        int remainingCartons = quantity % units;
        return noOfCartons * cartonPrice + remainingCartons * unitPrice * 1.3;
    }


    public OptimizedPriceDetails calculatePrice(Price price) {
        double optimizedPrice;
        boolean isDiscounted = false;
        if (price.getQuantity() == 0) {
            return new OptimizedPriceDetails();
        }
        Product product = getProduct(price.getId());
        if (product != null) {
            optimizedPrice = getProductPrice(product.getUnits(), product.getCartonPrice(), price.getQuantity());
            int noOfCartons = price.getQuantity() / product.getUnits();
            int remainingCartons = price.getQuantity() % product.getUnits();
            if (noOfCartons >= 3) {
                optimizedPrice = optimizedPrice - (optimizedPrice * 0.1);
                isDiscounted = true;
            }

            return new OptimizedPriceDetails(Math.round(optimizedPrice*100.0)/100.0, noOfCartons, remainingCartons, isDiscounted);
        }
        return new OptimizedPriceDetails();
    }

    /*  public double calculatePrice(String name, Integer units, String price, Integer quantity) {
     *//*      double cartonPrice = Double.parseDouble(price);
        double unitPrice = cartonPrice / 20;
        int noOfCartons = quantity / units;
        int remainingCartons = quantity % units;

        Double optimized_price = noOfCartons * cartonPrice + remainingCartons * unitPrice * 1.3;*//*
        double optimized_price = getProductPrice(units, price, quantity);
        int noOfCartons = quantity / units;
        if (noOfCartons >= 3) {
            optimized_price = optimized_price - (optimized_price * 0.1);
        }
        return optimized_price;
    }*/
}
