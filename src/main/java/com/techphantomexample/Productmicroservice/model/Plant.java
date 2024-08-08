package com.techphantomexample.Productmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="plant_info")
public class Plant extends BaseProduct {

    private String typeOfPlant;
    private String sunlightRequirements;
    private String wateringFrequency;

}


