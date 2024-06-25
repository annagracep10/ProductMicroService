package com.techphantomexample.Productmicroservice.exception;

import com.techphantomexample.Productmicroservice.model.Seed;
import com.techphantomexample.Productmicroservice.repository.SeedRepository;
import org.apache.logging.log4j.LogManager;

public class SeedValidation extends RuntimeException{

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(SeedValidation.class);

    public SeedValidation(String message) {
        super(message);
        log.error(message);
    }

    public SeedValidation(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }

    public static void validateSeed(Seed seed, SeedRepository seedRepository){

        if(isNullOrEmpty(seed.getName()) || isNullOrEmpty(seed.getSeedType()) || isNullOrEmpty(seed.getCategory())){
            throw new SeedValidation("Name, Type, and category are Mandatory");
        }


        if (existsBySeedName(seed.getName(), seedRepository)) {
            throw new SeedValidation("Seed with the same name already exists");
        }
    }

    public static void validateUpdateSeed(Seed seed){

        if(isNullOrEmpty(seed.getName()) || isNullOrEmpty(seed.getSeedType()) || isNullOrEmpty(seed.getCategory())){
            throw new SeedValidation("Name, Type, and category are Mandatory");
        }
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private static boolean existsBySeedName(String name, SeedRepository seedRepository){
        return seedRepository.existsByName(name);
    }
}
