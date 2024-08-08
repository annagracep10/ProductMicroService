package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.dto.PlantDto;
import com.techphantomexample.Productmicroservice.exception.PlantException;
import com.techphantomexample.Productmicroservice.model.Plant;
import com.techphantomexample.Productmicroservice.repository.PlantRepository;
import com.techphantomexample.Productmicroservice.validators.PlantValidation;
import com.techphantomexample.Productmicroservice.validators.PlanterValidation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PlantService {

    @Autowired
    PlantRepository plantRepository;

    public String createPlant(PlantDto plantdto) {

        if (plantRepository.existsByName(plantdto.getName())) {
            throw new PlantException("Plant already exists");
        }
        PlantValidation.validatePlant(plantdto,plantRepository);
        Plant plant = new Plant();
        plant.setName(plantdto.getName());
        plant.setDescription(plantdto.getDescription());
        plant.setPrice(plantdto.getPrice());
        plant.setCategory(plantdto.getCategory());
        plant.setQuantity(plantdto.getQuantity());
        plant.setImage(null);
        plant.setTypeOfPlant(plantdto.getTypeOfPlant());
        plant.setSunlightRequirements(plantdto.getSunlightRequirements());
        plant.setWateringFrequency(plantdto.getWateringFrequency());

        plantRepository.save(plant);
        return "Plant Created successfully";

    }

    public String updatePlant(int id, PlantDto newPlantDetails) {
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

    public void updatePlantQuantity(int plantId, int quantityToSubtract) {
        Plant plant = getPlant(plantId);
        plant.setQuantity(plant.getQuantity() + quantityToSubtract); // Add or subtract based on the sign of quantityToSubtract
        plantRepository.save(plant);
    }

}
