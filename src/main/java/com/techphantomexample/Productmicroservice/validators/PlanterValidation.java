package com.techphantomexample.Productmicroservice.validators;

import com.techphantomexample.Productmicroservice.dto.PlanterDto;
import com.techphantomexample.Productmicroservice.exception.PlanterException;
import com.techphantomexample.Productmicroservice.repository.PlanterRepository;

public class PlanterValidation {

    public static void validatePlanter(PlanterDto planter, PlanterRepository planterRepository){

        if(isNullOrEmpty(planter.getName()) || isNullOrEmpty(planter.getMaterial()) || isNullOrEmpty(planter.getColor())){
            throw new PlanterException("Name, Material, and Color are Mandatory");
        }
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
