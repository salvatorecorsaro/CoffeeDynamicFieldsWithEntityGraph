package com.scorsaro.eg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coffees")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @GetMapping
    public List<Coffee> getCoffees(@RequestParam List<String> fields) {
        return coffeeService.getCoffeesWithSelectedFields(fields);
    }
}

