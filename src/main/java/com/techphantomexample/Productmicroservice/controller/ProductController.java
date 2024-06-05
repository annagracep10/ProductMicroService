package com.techphantomexample.Productmicroservice.controller;

import com.techphantomexample.Productmicroservice.model.BaseProduct;
import com.techphantomexample.Productmicroservice.model.Plant;
import com.techphantomexample.Productmicroservice.model.Planter;
import com.techphantomexample.Productmicroservice.model.Seed;
import com.techphantomexample.Productmicroservice.service.PlantService;
import com.techphantomexample.Productmicroservice.service.PlantValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    PlantService plantService;

    public ProductController(PlantService plantService) {
        this.plantService = plantService;
    }

    @PostMapping("/plant")
    public ResponseEntity<CreateResponse> createProduct(@RequestBody Plant plant) {
        try {
            String result = plantService.createPlant(plant);
            HttpStatus httpStatus = HttpStatus.CREATED;

            CreateResponse response = new CreateResponse(result, httpStatus.value());
            return ResponseEntity.status(httpStatus).body(response);
        } catch (PlantValidation e) {
            HttpStatus status;
            if (e.getMessage().equals("Plant with same name already exists")) {
                status = HttpStatus.CONFLICT;
            } else if (e.getMessage().equals("Name, Type of Plant and Category are Mandatory")) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            CreateResponse response = new CreateResponse(e.getMessage(), status.value());
            return ResponseEntity.status(status).body(response);
        }
    }

    @PutMapping("/plant/{id}")
    public ResponseEntity<CreateResponse> updatePlant(@PathVariable int id, @RequestBody Plant plant) {
        try {
            String result = plantService.updatePlant(id, plant);
            HttpStatus httpStatus = HttpStatus.OK;

            CreateResponse response = new CreateResponse(result, httpStatus.value());
            return ResponseEntity.status(httpStatus).body(response);
        } catch (PlantValidation e) {
            HttpStatus status;
            if (e.getMessage().equals("Plant with same name already exists")) {
                status = HttpStatus.CONFLICT;
            } else if (e.getMessage().equals("Name, Type of Plant and Category are Mandatory")) {
                status = HttpStatus.BAD_REQUEST;
            } else if (e.getMessage().equals("Plant not found")) {
                status = HttpStatus.NOT_FOUND;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            CreateResponse response = new CreateResponse(e.getMessage(), status.value());
            return ResponseEntity.status(status).body(response);
        }
    }

    @GetMapping("/plant")
    public ResponseEntity<List<Plant>> getAllPlants() {
        try {
            List<Plant> plants = plantService.getAllPlants();
            return ResponseEntity.ok(plants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/plant/{id}")
    public ResponseEntity<Object> getPlantById(@PathVariable int id) {
        Optional<Plant> plant = Optional.ofNullable(plantService.getPlant(id));
        if (plant.isPresent()) {
            return ResponseEntity.ok(plant.get());
        } else {
            CreateResponse response = new CreateResponse("Plant not found", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/plant/{id}")
    public ResponseEntity<CreateResponse> deletePlantById(@PathVariable int id) {
        try {
            String result = plantService.deletePlant(id);
            CreateResponse response = new CreateResponse(result, HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } catch (PlantValidation e) {
            CreateResponse response = new CreateResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            CreateResponse response = new CreateResponse("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
