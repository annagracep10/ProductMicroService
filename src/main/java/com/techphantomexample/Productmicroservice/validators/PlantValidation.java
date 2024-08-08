package com.techphantomexample.Productmicroservice.validators;

import com.techphantomexample.Productmicroservice.dto.PlantDto;
import com.techphantomexample.Productmicroservice.exception.PlantException;
import com.techphantomexample.Productmicroservice.repository.PlantRepository;

public class PlantValidation {

    public static void validatePlant(PlantDto plant, PlantRepository plantRepository){

        if(isNullOrEmpty(plant.getName())||isNullOrEmpty(plant.getTypeOfPlant())){
            throw new PlantException("Name, Type of Plant are Mandatory");
        }
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
