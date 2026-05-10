package com.manufacto.Rawmaterial.productmaster.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.manufacto.Rawmaterial.productmaster.entity.product;

public interface ProductRepository extends JpaRepository<product, Long> {
}