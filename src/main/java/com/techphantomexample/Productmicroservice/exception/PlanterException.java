package com.techphantomexample.Productmicroservice.exception;

import com.techphantomexample.Productmicroservice.model.Planter;
import com.techphantomexample.Productmicroservice.repository.PlanterRepository;
import org.apache.logging.log4j.LogManager;

public class PlanterException extends RuntimeException{

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(PlanterException.class);

    public PlanterException(String message) {
        super(message);
        log.error(message);
    }

}
