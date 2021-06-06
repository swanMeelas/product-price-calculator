package com.product.price.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptimizedPriceDetails {
    private Double totalPrice;
    private Integer noOfCartons;
    private Integer noOfUnits;
    private Boolean isDiscounted;
}
