package org.example.demoproject;

import org.example.demoproject.Week2.lab2_3.Product;
import org.example.demoproject.Week2.lab2_3.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProjectApplication.class, args);
    }
// //week3 lab2_3 testing
//    @Bean
//    CommandLineRunner runner(ProductService service) {
//        return args -> {
//
//            service.save(new Product("Laptop", 1200.0, 10));
//            service.save(new Product("Phone", 800.0, 20));
//
//            System.out.println("ALL PRODUCTS:");
//
//            service.findAll().forEach(p ->
//                    System.out.println(
//                            p.getId() + " " +
//                                    p.getName() + " " +
//                                    p.getPrice()
//                    )
//            );
//        };
//    }
}