package com.techphantomexample.Productmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeedDto {
    private String name;
    private String description;
    private double price;
    private String category;
    private int quantity;
    private String seedType;
    private int germinationTime;
    private String season;
}
