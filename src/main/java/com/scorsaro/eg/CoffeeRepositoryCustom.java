package com.scorsaro.eg;

import java.util.List;

public interface CoffeeRepositoryCustom {
    List<Coffee> findAllWithSelectedFields(List<String> fields);
}
