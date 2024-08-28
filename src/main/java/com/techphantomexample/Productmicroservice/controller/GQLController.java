package com.techphantomexample.Productmicroservice.controller;

import com.techphantomexample.Productmicroservice.dto.PlantDto;
import com.techphantomexample.Productmicroservice.model.Plant;
import com.techphantomexample.Productmicroservice.model.Planter;
import com.techphantomexample.Productmicroservice.model.Seed;
import com.techphantomexample.Productmicroservice.service.PlantService;
import com.techphantomexample.Productmicroservice.service.PlanterService;
import com.techphantomexample.Productmicroservice.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GQLController {

    @Autowired
    PlantService plantService;
    @Autowired
    PlanterService planterService;
    @Autowired
    SeedService seedService;


    @QueryMapping
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    @QueryMapping
    public List<Planter> getAllPlanters() {
        return planterService.getAllPlanters();
    }

    @QueryMapping
    public List<Seed> getAllSeeds() {
        return seedService.getAllSeeds();
    }

    @QueryMapping
    public Plant getPlantById(@Argument Integer id) {
        return plantService.getPlant(id);
    }

    @QueryMapping
    public Planter getPlanterById(@Argument Integer id) {
        return planterService.getPlanter(id);
    }

    @QueryMapping
    public Seed getSeedById(@Argument Integer id) {
        return seedService.getSeed(id);
    }

    @MutationMapping
    public String updatePlant(
            @Argument Integer id,
            @Argument String name,
            @Argument String description,
            @Argument Double price,
            @Argument String category,
            @Argument Integer quantity,
            @Argument String typeOfPlant,
            @Argument String sunlightRequirements,
            @Argument String wateringFrequency) {

        return plantService.updatePlant(id, name, description, price, category, quantity, typeOfPlant, sunlightRequirements, wateringFrequency);
    }

}
