package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.model.Plant;
import com.techphantomexample.Productmicroservice.model.Planter;

import java.util.List;

public interface PlantService {
    public String createPlant(Plant plant);
    public String updatePlant(int id,Planter planter);
    public String deletePlant(int id);
    public Plant getPlant(int id);
    public List<Plant> getAllPlants();
}
