package com.product.price.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price implements Serializable {
    private Integer id;
    private Double price;
    private Integer quantity;

    public Price(Integer id, Double price) {
        this.id = id;
        this.price = price;
    }

    public Price(Integer id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
