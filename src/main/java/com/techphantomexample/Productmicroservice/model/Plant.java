package com.techphantomexample.Productmicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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


