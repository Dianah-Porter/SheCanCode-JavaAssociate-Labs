package org.example.demoproject;
import org.example.demoproject.Product;
import org.example.demoproject.ProductJdbcRepository;
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

            Product product = new Product(
                    "P002",
                    "Laptop",
                    1200,
                    15
            );

            repository.save(product);

            System.out.println("Product saved successfully!");
        };
    }
}
