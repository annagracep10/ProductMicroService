package com.techphantomexample.Productmicroservice.exception;

import com.techphantomexample.Productmicroservice.model.Plant;
import com.techphantomexample.Productmicroservice.repository.PlantRepository;
import org.apache.logging.log4j.LogManager;

public class PlantValidation extends RuntimeException{

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(PlantValidation.class);

    public PlantValidation(String message) {
        super(message);
        log.error(message);
    }

    public PlantValidation(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }

    public static void validatePlant(Plant plant, PlantRepository plantRepository){

        if(isNullOrEmpty(plant.getName())||isNullOrEmpty(plant.getTypeOfPlant())){
            throw new PlantValidation("Name, Type of Plant are Mandatory");
        }


    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}
