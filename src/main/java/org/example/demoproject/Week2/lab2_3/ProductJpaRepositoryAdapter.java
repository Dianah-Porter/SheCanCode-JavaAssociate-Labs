package org.example.demoproject.lab2_3;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductJpaRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository jpaRepository;

    public ProductJpaRepositoryAdapter(ProductJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Product product) {
        jpaRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return jpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}