package com.manufacto.Rawmaterial.RawMaterialRepository;

import com.manufacto.Rawmaterial.entity.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
}