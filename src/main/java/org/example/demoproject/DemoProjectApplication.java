package org.example.demoproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.OptimisticLockException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class DemoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProjectApplication.class, args);
    }

    // -----------------------------
    // JPA REPOSITORY DEMO
    // -----------------------------
    @Bean
    CommandLineRunner jpaRunner(ProductJpaRepository repository) {

        return args -> {

            Category c1 = new Category("Electronics");

            Product p1 = new Product("Laptop", 1200, 10);
            p1.setCategory(c1);

            repository.save(p1);

            System.out.println("Saved product via JPA");

            repository.findAll().forEach(p ->
                    System.out.println(p.getId() + " " + p.getName())
            );
        };
    }

    // -----------------------------
    // OPTIMISTIC LOCKING DEMO
    // -----------------------------
    @Bean
    CommandLineRunner optimisticLockDemo(EntityManagerFactory emf) {

        return args -> {

            System.out.println("\n--- OPTIMISTIC LOCK DEMO ---");

            ExecutorService executor = Executors.newFixedThreadPool(2);

            executor.submit(() -> {

                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                Product p = em.find(Product.class, 1L);
                p.setStock(p.getStock() - 5);

                sleep(2000); // simulate delay

                em.persist(p);
                em.getTransaction().commit();
                em.close();

                System.out.println("Transaction 1 committed");
            });

            executor.submit(() -> {

                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                sleep(500); // start earlier

                Product p = em.find(Product.class, 1L);
                p.setStock(p.getStock() - 5);

                try {

                    em.persist(p);
                    em.getTransaction().commit();

                    System.out.println("Transaction 2 committed");

                } catch (OptimisticLockException e) {

                    System.out.println("OptimisticLockException detected!");
                    em.getTransaction().rollback();

                }

                em.close();
            });

            executor.shutdown();
        };
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}