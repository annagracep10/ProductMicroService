package com.techphantomexample.Productmicroservice.exception;

import com.techphantomexample.Productmicroservice.model.Seed;
import com.techphantomexample.Productmicroservice.repository.SeedRepository;
import org.apache.logging.log4j.LogManager;

public class SeedException extends RuntimeException{

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(SeedException.class);

    public SeedException(String message) {
        super(message);
        log.error(message);
    }



}
