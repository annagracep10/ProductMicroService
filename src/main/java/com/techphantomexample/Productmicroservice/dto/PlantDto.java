package com.techphantomexample.Productmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantDto  {
    private String name;
    private String description;
    private double price;
    private String category;
    private int quantity;
    private String typeOfPlant;
    private String sunlightRequirements;
    private String wateringFrequency;
}
