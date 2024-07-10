package com.techphantomexample.Productmicroservice.validators;

import com.techphantomexample.Productmicroservice.exception.SeedException;
import com.techphantomexample.Productmicroservice.model.Seed;
import com.techphantomexample.Productmicroservice.repository.SeedRepository;

public class SeedValidation {

    public static void validateSeed(Seed seed, SeedRepository seedRepository){

        if(isNullOrEmpty(seed.getName()) || isNullOrEmpty(seed.getSeedType()) || isNullOrEmpty(seed.getCategory())){
            throw new SeedException("Name, Type, and category are Mandatory");
        }


    }


    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
