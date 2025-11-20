package com.elijah.cloudtest.cloud_test_app.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> all() {
        log.debug("Fetching all products");
        return repo.findAll();
    }

    @Transactional
    public Product create(Product product) {
        log.info("Creating product: {}", product.getName());
        return repo.save(product);
    }

    public Product get(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Transactional
    public Product update(Long id, Product updated) {
        Product existing = get(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        log.info("Updating product id={}", id);
        return repo.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting product id={}", id);
        repo.deleteById(id);
    }
}