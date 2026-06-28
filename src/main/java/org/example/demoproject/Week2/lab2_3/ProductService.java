package org.example.demoproject.Week2.lab2_3;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void save(Product p) {
        repository.save(p);
    }

    public Product findById(Long id) {
        return repository.findById(id);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void bulkImport(List<Product> products) {

        for (Product p : products) {

            if (p.getPrice() < 0) {
                throw new RuntimeException("Invalid price");
            }

            repository.save(p);
        }
    }
}