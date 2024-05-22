package com.scorsaro.eg;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long>, CoffeeRepositoryCustom {
}
