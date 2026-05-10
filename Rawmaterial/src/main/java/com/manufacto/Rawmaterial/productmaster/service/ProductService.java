package com.manufacto.Rawmaterial.productmaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manufacto.Rawmaterial.productmaster.entity.product;
import com.manufacto.Rawmaterial.productmaster.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // CREATE
    public product save(product product) {

        if (product.getStatus() == null) {
            product.setStatus("Pending");
        }

        if (product.getStage() == null) {
            product.setStage("Product Master");
        }

        return repository.save(product);
    }

    // GET ALL
    public List<product> getAll() {
        return repository.findAll();
    }

    // GET BY ID
    public product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // UPDATE
    public product update(Long id, product updatedProduct) {

        product existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setProductId(updatedProduct.getProductId());
        existing.setProductName(updatedProduct.getProductName());
        existing.setStatus(updatedProduct.getStatus());
        existing.setStage(updatedProduct.getStage());

        return repository.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // MOVE TO WIP
    public product moveToWip(Long id) {

        product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStatus("Moved");
        product.setStage("Pre-Assembling");

        return repository.save(product);
    }
}
