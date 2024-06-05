package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.model.Planter;
import com.techphantomexample.Productmicroservice.repository.PlanterRepository;
import org.apache.logging.log4j.LogManager;

public class PlanterValidation extends RuntimeException{

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(PlanterValidation.class);

    public PlanterValidation(String message) {
        super(message);
        log.error(message);
    }

    public PlanterValidation(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }

    public static void validatePlanter(Planter planter, PlanterRepository planterRepository){

        if(isNullOrEmpty(planter.getName()) || isNullOrEmpty(planter.getMaterial()) || isNullOrEmpty(planter.getColor())){
            throw new PlanterValidation("Name, Material, and Color are Mandatory");
        }

        if (existsByPlanterName(planter.getName(), planterRepository)) {
            throw new PlanterValidation("Planter with same name already exists");
        }
    }

    public static void validateUpdatePlanter(Planter planter){

        if(isNullOrEmpty(planter.getName()) || isNullOrEmpty(planter.getMaterial()) || isNullOrEmpty(planter.getColor())){
            throw new PlanterValidation("Name, Material, and Color are Mandatory");
        }
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private static boolean existsByPlanterName(String name, PlanterRepository planterRepository){
        return planterRepository.existsByName(name);
    }
}
