package com.techphantomexample.Productmicroservice.validators;

import com.techphantomexample.Productmicroservice.dto.SeedDto;
import com.techphantomexample.Productmicroservice.exception.SeedException;
import com.techphantomexample.Productmicroservice.repository.SeedRepository;

public class SeedValidation {

    public static void validateSeed(SeedDto seed, SeedRepository seedRepository){

        if(isNullOrEmpty(seed.getName()) || isNullOrEmpty(seed.getSeedType()) || isNullOrEmpty(seed.getCategory())){
            throw new SeedException("Name, Type, and category are Mandatory");
        }
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
