package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.model.BaseProduct;
import com.techphantomexample.Productmicroservice.model.Plant;
import com.techphantomexample.Productmicroservice.model.Planter;
import com.techphantomexample.Productmicroservice.model.Seed;
import com.techphantomexample.Productmicroservice.repository.PlantRepository;
import com.techphantomexample.Productmicroservice.repository.PlanterRepository;
import com.techphantomexample.Productmicroservice.repository.SeedRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductOperationException extends RuntimeException {

    private static final Logger log = LogManager.getLogger(ProductOperationException.class);

    public ProductOperationException(String message) {
        super(message);
        log.error(message);
    }

    public ProductOperationException(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }

    public static void validateProduct(BaseProduct baseProduct, PlantRepository plantRepository, PlanterRepository planterRepository, SeedRepository seedRepository) {
        if (isNullOrEmpty(baseProduct.getName()) || isNullOrEmpty(baseProduct.getDescription()) || baseProduct.getPrice() <= 0 || isNullOrEmpty(baseProduct.getCategory()) || baseProduct.getQuantity() <= 0) {
            throw new ProductOperationException("All fields are required");
        }

        if (existsByName(baseProduct.getName(), baseProduct.getCategory(), plantRepository, planterRepository, seedRepository)) {
            throw new ProductOperationException("Product with the same name already exists");
        }

        log.info("Base product validation success");
        switch (baseProduct.getCategory().toUpperCase()) {
            case "PLANT":
                validatePlantFields((Plant) baseProduct);
                log.info("Plant validation success");
                break;
            case "PLANTER":
                validatePlanterFields((Planter) baseProduct);
                log.info("Planter validation success");
                break;
            case "SEED":
                validateSeedFields((Seed) baseProduct);
                log.info("Seed validation success");
                break;
            default:
                throw new ProductOperationException("Invalid product category");
        }
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private static boolean existsByName(String name, String category, PlantRepository plantRepository, PlanterRepository planterRepository, SeedRepository seedRepository) {
        switch (category.toUpperCase()) {
            case "PLANT":
                return plantRepository.existsByName(name);
            case "PLANTER":
                return planterRepository.existsByName(name);
            case "SEED":
                return seedRepository.existsByName(name);
            default:
                throw new ProductOperationException("Invalid product category");
        }
    }

    private static void validatePlantFields(Plant plant) {
        if (isNullOrEmpty(plant.getTypeOfPlant()) || isNullOrEmpty(plant.getSunlightRequirements()) || isNullOrEmpty(plant.getWateringFrequency())) {
            log.error("Plant field missing");
            throw new ProductOperationException("All fields for plant are required");
        }
    }

    private static void validatePlanterFields(Planter planter) {
        if (isNullOrEmpty(planter.getMaterial()) || isNullOrEmpty(planter.getDimensions()) || isNullOrEmpty(planter.getColor())) {
            log.error("Planter field missing");
            throw new ProductOperationException("All fields for planter are required");
        }
    }

    private static void validateSeedFields(Seed seed) {
        if (isNullOrEmpty(seed.getSeedType()) || seed.getGerminationTime() <= 0 || isNullOrEmpty(seed.getSeason())) {
            log.error("Seed field missing");
            throw new ProductOperationException("All fields for seed are required");
        }
    }
}
