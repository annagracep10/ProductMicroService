package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.exception.PlantException;
import com.techphantomexample.Productmicroservice.model.Plant;
import com.techphantomexample.Productmicroservice.repository.PlantRepository;
import com.techphantomexample.Productmicroservice.validators.PlantValidation;
import com.techphantomexample.Productmicroservice.validators.PlanterValidation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {

    PlantRepository plantRepository;


    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public String createPlant(Plant plant) {
        if (plantRepository.existsByName(plant.getName())) {
            throw new PlantException("Plant already exists");
        }
        PlantValidation.validatePlant(plant,plantRepository);
        plantRepository.save(plant);
        return "Plant Created successfully";

    }

    public String updatePlant(int id, Plant newPlantDetails) {
        if (!plantRepository.existsById(id)) {
            throw new PlantException("Plant not found");
        }
        Plant existingPlant = plantRepository.findById(id).get();
        PlantValidation.validatePlant(newPlantDetails,plantRepository);
        existingPlant.setName(newPlantDetails.getName());
        existingPlant.setDescription(newPlantDetails.getDescription());
        existingPlant.setPrice(newPlantDetails.getPrice());
        existingPlant.setCategory(newPlantDetails.getCategory());
        existingPlant.setQuantity(newPlantDetails.getQuantity());
        existingPlant.setTypeOfPlant(newPlantDetails.getTypeOfPlant());
        existingPlant.setSunlightRequirements(newPlantDetails.getSunlightRequirements());
        existingPlant.setWateringFrequency(newPlantDetails.getWateringFrequency());

        plantRepository.save(existingPlant);
        return "Plant Updated Successfully";
    }

    public String deletePlant(int plantId) {
        if (!plantRepository.existsById(plantId)) {
            throw new PlantException("Plant not found");
        }
        plantRepository.deleteById(plantId);
        return "Plant Deleted Successfully";
    }

    public Plant getPlant(int plantId) {
        if (!plantRepository.existsById(plantId)) {
            throw new PlantException("Plant not found");
        }
        return  plantRepository.findById(plantId).get();
    }

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();

    }

}
