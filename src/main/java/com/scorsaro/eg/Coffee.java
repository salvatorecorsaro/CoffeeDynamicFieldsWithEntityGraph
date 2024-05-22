package com.scorsaro.eg;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String origin;
    private String roast;
    private String flavor;
    private Double price;

    public Coffee(String name, String origin, String roast, String flavor, Double price) {
        this.name = name;
        this.origin = origin;
        this.roast = roast;
        this.flavor = flavor;
        this.price = price;
    }
}
