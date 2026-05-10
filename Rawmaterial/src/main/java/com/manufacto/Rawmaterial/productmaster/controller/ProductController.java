package com.manufacto.Rawmaterial.productmaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.manufacto.Rawmaterial.productmaster.entity.product;
import com.manufacto.Rawmaterial.productmaster.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService service;

    // CREATE
    @PostMapping
    public product save(@RequestBody product product) {
        return service.save(product);
    }

    // GET ALL
    @GetMapping
    public List<product> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public product getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public product update(@PathVariable Long id,
                          @RequestBody product product) {
        return service.update(id, product);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Product deleted successfully";
    }

    // MOVE TO WIP
    @PutMapping("/move-to-wip/{id}")
    public product moveToWip(@PathVariable Long id) {
        return service.moveToWip(id);
    }
}
