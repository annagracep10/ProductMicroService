package com.techphantomexample.Productmicroservice.validators;

import com.techphantomexample.Productmicroservice.exception.PlanterException;
import com.techphantomexample.Productmicroservice.model.Plant;
import com.techphantomexample.Productmicroservice.model.Planter;
import com.techphantomexample.Productmicroservice.repository.PlantRepository;
import com.techphantomexample.Productmicroservice.repository.PlanterRepository;

public class PlanterValidation {

    public static void validatePlanter(Planter planter, PlanterRepository planterRepository){

        if(isNullOrEmpty(planter.getName()) || isNullOrEmpty(planter.getMaterial()) || isNullOrEmpty(planter.getColor())){
            throw new PlanterException("Name, Material, and Color are Mandatory");
        }

    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}
