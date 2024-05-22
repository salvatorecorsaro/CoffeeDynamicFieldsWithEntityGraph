package com.scorsaro.eg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CoffeeService coffeeService;

    @Override
    public void run(String... args) throws Exception {
        coffeeService.preloadData();
    }
}

