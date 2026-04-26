
package com.manufacto.Rawmaterial.service;

import com.manufacto.Rawmaterial.entity.RawMaterial;
import com.manufacto.Rawmaterial.RawMaterialRepository.RawMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RawMaterialService {

    private final RawMaterialRepository repository;

    // ==============================
    // ✅ CREATE
    // ==============================
    public RawMaterial create(RawMaterial material) {

        // DO NOT set ID manually
        // JPA will auto-generate ID

        setStockStatus(material);

        return repository.save(material); // INSERT
    }

    // ==============================
    // ✅ GET ALL
    // ==============================
    public List<RawMaterial> getAll() {
        return repository.findAll();
    }

    // ==============================
    // ✅ GET BY ID
    // ==============================
    public RawMaterial getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found with id: " + id));
    }

    
    public RawMaterial update(Long id, RawMaterial updated) {

        RawMaterial existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found with id: " + id));

        // 🔥 FORCE ID (VERY IMPORTANT)
        existing.setId(id);

        // Update fields
        existing.setMaterialName(updated.getMaterialName());
        existing.setMaterialType(updated.getMaterialType());
        existing.setUnitOfMeasure(updated.getUnitOfMeasure());
        existing.setAvailableQuantity(updated.getAvailableQuantity());
        existing.setReorderLevel(updated.getReorderLevel());

        // Stock logic
        if (existing.getAvailableQuantity() <= existing.getReorderLevel()) {
            existing.setStockStatus("LOW");
        } else {
            existing.setStockStatus("AVAILABLE");
        }

        return repository.save(existing); // ✅ ALWAYS UPDATE
    }
   

    // ==============================
    // ✅ DELETE
    // ==============================
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Material not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // ==============================
    // 🔥 COMMON LOGIC
    // ==============================
    private void setStockStatus(RawMaterial material) {
        if (material.getAvailableQuantity() <= material.getReorderLevel()) {
            material.setStockStatus("LOW");
        } else {
            material.setStockStatus("AVAILABLE");
        }
    }
}
