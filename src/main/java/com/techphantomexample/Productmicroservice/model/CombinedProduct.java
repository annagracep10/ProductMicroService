package com.techphantomexample.Productmicroservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CombinedProduct {

    private List<Plant> plants;
    private List<Planter> planters;
    private List<Seed> seeds;

}
