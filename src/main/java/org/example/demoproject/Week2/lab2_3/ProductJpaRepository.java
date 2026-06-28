package org.example.demoproject.Week2.lab2_3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    @Query("""
           SELECT p
           FROM Product p
           WHERE p.category.name = :category
           AND p.price > :minPrice
           """)
    List<Product> findByCategoryAndPriceGreaterThan(String category,
                                                    double minPrice);
}