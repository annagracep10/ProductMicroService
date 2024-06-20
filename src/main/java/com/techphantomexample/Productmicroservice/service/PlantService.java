package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.model.Plant;
import com.techphantomexample.Productmicroservice.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {

    PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public String createPlant(Plant plant) {
        try {
            PlantValidation.validatePlant(plant,plantRepository);
            plantRepository.save(plant);
            return "Plant Created successfully";
        } catch (PlantValidation e) {
            throw e;
        } catch (Exception e) {
            throw new PlantValidation("Error", e);
        }
    }

    public String updatePlant(int id, Plant newPlantDetails) {
        try {
            if (!plantRepository.existsById(id)) {
                throw new PlantValidation("Plant not found");
            }

            Plant existingPlant = (Plant) plantRepository.findById(id).get();


            PlantValidation.validateUpdatePlant(newPlantDetails);

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
        } catch (PlantValidation e) {
            throw e;
        } catch (Exception e) {
            throw new PlantValidation("Error updating plant", e);
        }
    }

    public String deletePlant(int plantId) {
        try {
            if (!plantRepository.existsById(plantId)) {
                throw new PlantValidation("Plant not found");
            }
            plantRepository.deleteById(plantId);
            return "Plant Deleted Successfully";
        } catch (PlantValidation e) {
            throw e;
        } catch (Exception e) {
            throw new PlantValidation("Error deleting plant", e);
        }
    }

    public Plant getPlant(int plantId) {
        try {
            if (!plantRepository.existsById(plantId)) {
                return null;
            }
            return (Plant) plantRepository.findById(plantId).get();
        } catch (Exception e) {
            throw new PlantValidation("Error retrieving plant", e);
        }
    }

    public List<Plant> getAllPlants() {
        try {
            return plantRepository.findAll();
        } catch (Exception e) {
            throw new PlantValidation("Error retrieving Plants", e);
        }
    }

}
