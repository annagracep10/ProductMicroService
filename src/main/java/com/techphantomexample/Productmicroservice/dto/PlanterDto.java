package com.techphantomexample.Productmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanterDto  {
    private String name;
    private String description;
    private double price;
    private String category;
    private int quantity;
    private String material;
    private String dimensions;
    private String color;
}
