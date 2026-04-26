package com.manufacto.Rawmaterial.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "raw_materials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(nullable = false)
    private String materialName;

    private String materialType;
    private String unitOfMeasure;
    private Integer availableQuantity;
    private Integer reorderLevel;
    private String stockStatus;
}