package com.scorsaro.eg;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeService {


    private final CoffeeRepository coffeeRepository;

    public List<Coffee> getCoffeesWithSelectedFields(List<String> fields) {
        return coffeeRepository.findAllWithSelectedFields(fields);
    }

    public void preloadData() {
        List<Coffee> coffees = Arrays.asList(
                new Coffee("Espresso", "Ethiopia", "Dark", "Rich", 2.5),
                new Coffee("Latte", "Colombia", "Medium", "Smooth", 3.0),
                new Coffee("Cappuccino", "Brazil", "Light", "Frothy", 3.5)
        );
        coffeeRepository.saveAll(coffees);
    }
}

