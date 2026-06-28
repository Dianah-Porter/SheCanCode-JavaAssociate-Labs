package org.example.demoproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.OptimisticLockException;
import org.example.demoproject.lab2_3.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductOptimisticLockTest {

    @Autowired
    private EntityManagerFactory emf;

    @Test
    void optimisticLockShouldFailOnConcurrentUpdate() throws Exception {

        // ---------------------------
        // 1. CREATE PRODUCT
        // ---------------------------
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Product product = new Product("Tablet", 500, 50);
        em.persist(product);

        em.getTransaction().commit();
        em.close();

        Long id = product.getId();

        // ---------------------------
        // 2. SESSION 1
        // ---------------------------
        EntityManager em1 = emf.createEntityManager();
        em1.getTransaction().begin();

        Product p1 = em1.find(Product.class, id);

        // ---------------------------
        // 3. SESSION 2
        // ---------------------------
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();

        Product p2 = em2.find(Product.class, id);

        // ---------------------------
        // 4. MODIFY BOTH
        // ---------------------------
        p1.setStock(40);
        p2.setStock(30);

        em1.persist(p1);
        em1.getTransaction().commit();
        em1.close();

        // ---------------------------
        // 5. SECOND SHOULD FAIL
        // ---------------------------
        assertThrows(OptimisticLockException.class, () -> {

            em2.persist(p2);
            em2.getTransaction().commit();
            em2.close();
        });
    }
}