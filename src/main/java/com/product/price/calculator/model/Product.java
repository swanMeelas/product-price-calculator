package com.product.price.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "units")
    private Integer units;

    @Column(name = "cartonprice")
    private Double cartonPrice;

    @Override
    public String toString() {
        return "Product{" +
                "id :" + id +
                ", name:'" + name + '\'' +
                ", units:" + units +
                ", cartonPrice:" + cartonPrice +
                '}';
    }
}
