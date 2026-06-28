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
    CommandLineRunner runner(ProductJdbcRepository repository) {

        return args -> {

            System.out.println("=================================");
            System.out.println("TESTING JDBC REPOSITORY");
            System.out.println("=================================");

            // -------------------------
            // 1. SAVE
            // -------------------------

            try {

                Product product = new Product(
                        "P003",
                        "Keyboard",
                        80.0,
                        20
                );

                repository.save(product);
                System.out.println("Product P003 saved successfully.");

            } catch (Exception e) {

                System.out.println("Product P003 already exists.");
            }

            // -------------------------
            // 2. FIND BY ID
            // -------------------------

            Product found = repository.findById("P003");

            if (found != null) {

                System.out.println();
                System.out.println("Found Product");

                System.out.println("ID    : " + found.getId());
                System.out.println("Name  : " + found.getName());
                System.out.println("Price : " + found.getPrice());
                System.out.println("Stock : " + found.getStock());

            } else {

                System.out.println("Product not found.");
            }

            // -------------------------
            // 3. FIND ALL
            // -------------------------

            System.out.println();
            System.out.println("All Products");

            for (Product p : repository.findAll()) {

                System.out.println(
                        p.getId() + " | " +
                                p.getName() + " | " +
                                p.getPrice() + " | " +
                                p.getStock()
                );
            }

            // -------------------------
            // 4. DELETE
            // -------------------------

            repository.deleteById("P003");

            System.out.println();
            System.out.println("Deleted Product P003");

            // -------------------------
            // 5. VERIFY DELETE
            // -------------------------

            Product deleted = repository.findById("P003");

            if (deleted == null) {

                System.out.println("Deletion verified successfully.");

            } else {

                System.out.println("Delete failed.");
            }

            System.out.println();
            System.out.println("Exercise 2.7 CRUD methods completed.");
        };
    }
}