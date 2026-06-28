package org.example.demoproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductJpaRepository repository) {

        return args -> {

            Product p1 = new Product("Laptop", 1200, 10);
            Product p2 = new Product("Phone", 900, 15);

            repository.save(p1);
            repository.save(p2);

            System.out.println("Saved Products");

            repository.findAll().forEach(product ->
                    System.out.println(
                            product.getId() + " "
                                    + product.getName() + " "
                                    + product.getPrice() + " "
                                    + product.getStock()
                    )
            );
        };
    }
}