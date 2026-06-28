package org.example.demoproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductService service) {

        return args -> {

            service.save(new Product("Laptop", 1200, 10));
            service.save(new Product("Phone", 800, 20));

            System.out.println("ALL PRODUCTS:");

            service.findAll().forEach(p ->
                    System.out.println(
                            p.getId() + " " +
                                    p.getName() + " " +
                                    p.getPrice()
                    )
            );
        };
    }
}