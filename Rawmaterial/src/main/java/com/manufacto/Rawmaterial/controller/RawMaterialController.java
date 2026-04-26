
package com.manufacto.Rawmaterial.controller;

import com.manufacto.Rawmaterial.entity.RawMaterial;
import com.manufacto.Rawmaterial.service.RawMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/raw-materials")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RawMaterialController {

    private final RawMaterialService service;

    // ✅ CREATE
    @PostMapping
    public RawMaterial create(@RequestBody RawMaterial material) {
        return service.create(material);
    }

    // ✅ GET ALL
    @GetMapping
    public List<RawMaterial> getAll() {
        return service.getAll();
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public RawMaterial getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // ✅ UPDATE (🔥 IMPORTANT)
    @PutMapping("/{id}")
    public RawMaterial update(@PathVariable Long id,
                             @RequestBody RawMaterial material) {

        return service.update(id, material);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }
}
