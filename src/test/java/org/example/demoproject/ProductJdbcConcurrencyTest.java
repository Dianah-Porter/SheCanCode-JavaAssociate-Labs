package org.example.demoproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductJdbcRepositoryTest {

    @Autowired
    private ProductJdbcRepository repository;

    @Test
    void contextLoads() {
        assertNotNull(repository);
    }

    @Test
    void basicRepositoryIsWired() {
        assertNotNull(repository, "Repository should be injected by Spring");
    }
}
