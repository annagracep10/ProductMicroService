package com.techphantomexample.Productmicroservice.exception;

import org.apache.logging.log4j.LogManager;

public class PlantException extends RuntimeException{

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(PlantException.class);

    public PlantException(String message) {
        super(message);
        log.error(message);
    }

}
