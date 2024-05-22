package com.scorsaro.eg;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CoffeeService coffeeService;

    @Override
    public void run(String... args) throws Exception {
        coffeeService.preloadData();
    }
}

