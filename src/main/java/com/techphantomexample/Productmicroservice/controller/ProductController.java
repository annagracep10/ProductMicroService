package com.techphantomexample.Productmicroservice.controller;

import com.techphantomexample.Productmicroservice.model.*;
import com.techphantomexample.Productmicroservice.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    PlantService plantService;
    PlanterService planterService;
    SeedService seedService;
    RestTemplate restTemplate;

    public ProductController(PlantService plantService, PlanterService planterService, SeedService seedService, RestTemplate restTemplate) {
        this.plantService = plantService;
        this.planterService = planterService;
        this.seedService = seedService;
        this.restTemplate = restTemplate;
    }




    @PostMapping("/plant")
    public ResponseEntity<CreateResponse> createProduct(@RequestBody Plant plant) {
        try {
            String result = plantService.createPlant(plant);
            System.out.println(plant);
            HttpStatus httpStatus = HttpStatus.CREATED;

            CreateResponse response = new CreateResponse(result, httpStatus.value());
            return ResponseEntity.status(httpStatus).body(response);
        } catch (PlantValidation e) {
            HttpStatus status;
            if (e.getMessage().equals("Plant with same name already exists")) {
                status = HttpStatus.CONFLICT;
            } else if (e.getMessage().equals("Name, Type of Plant are Mandatory")) {
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
            } else if (e.getMessage().equals("Name, Type of Plant are Mandatory")) {
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


    @PostMapping("/planter")
    public ResponseEntity<CreateResponse> createPlanter(@RequestBody Planter planter) {
        try {
            String result = planterService.createPlanter(planter);
            HttpStatus httpStatus = HttpStatus.CREATED;

            CreateResponse response = new CreateResponse(result, httpStatus.value());
            return ResponseEntity.status(httpStatus).body(response);
        } catch (PlanterValidation e) {
            HttpStatus status;
            if (e.getMessage().equals("Planter with same name already exists")) {
                status = HttpStatus.CONFLICT;
            } else if (e.getMessage().equals("Name, Material, and Color are Mandatory")) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            CreateResponse response = new CreateResponse(e.getMessage(), status.value());
            return ResponseEntity.status(status).body(response);
        }
    }

    @PutMapping("/planter/{id}")
    public ResponseEntity<CreateResponse> updatePlanter(@PathVariable int id, @RequestBody Planter planter) {
        try {
            String result = planterService.updatePlanter(id, planter);
            HttpStatus httpStatus = HttpStatus.OK;

            CreateResponse response = new CreateResponse(result, httpStatus.value());
            return ResponseEntity.status(httpStatus).body(response);
        } catch (PlanterValidation e) {
            HttpStatus status;
            if (e.getMessage().equals("Planter with same name already exists")) {
                status = HttpStatus.CONFLICT;
            } else if (e.getMessage().equals("Name, Material, and Color are Mandatory") ) {
                status = HttpStatus.BAD_REQUEST;
            } else if (e.getMessage().equals("Planter not found")){
                status = HttpStatus.NOT_FOUND;
            }
            else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            CreateResponse response = new CreateResponse(e.getMessage(), status.value());
            return ResponseEntity.status(status).body(response);
        }
    }

    @GetMapping("/planter")
    public ResponseEntity<List<Planter>> getAllPlanters() {
        try {
            List<Planter> planters = planterService.getAllPlanters();
            return ResponseEntity.ok(planters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/planter/{id}")
    public ResponseEntity<Object> getPlanterById(@PathVariable int id) {
        Optional<Planter> planter = Optional.ofNullable(planterService.getPlanter(id));
        if (planter.isPresent()) {
            return ResponseEntity.ok(planter.get());
        } else {
            CreateResponse response = new CreateResponse("Planter not found", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/planter/{id}")
    public ResponseEntity<CreateResponse> deletePlanterById(@PathVariable int id) {
        try {
            String result = planterService.deletePlanter(id);
            CreateResponse response = new CreateResponse(result, HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } catch (PlanterValidation e) {
            HttpStatus status;
            if (e.getMessage().equals("Planter not found")) {
                status = HttpStatus.NOT_FOUND;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            CreateResponse response = new CreateResponse(e.getMessage(), status.value());
            return ResponseEntity.status(status).body(response);
        }
    }

    @PostMapping("/seed")
    public ResponseEntity<CreateResponse> createSeed(@RequestBody Seed seed) {
        try {
            String result = seedService.createSeed(seed);
            HttpStatus httpStatus = HttpStatus.CREATED;

            CreateResponse response = new CreateResponse(result, httpStatus.value());
            return ResponseEntity.status(httpStatus).body(response);
        } catch (SeedValidation e) {
            HttpStatus status;
            if (e.getMessage().equals("Seed with the same name already exists")) {
                status = HttpStatus.CONFLICT;
            } else if (e.getMessage().equals("Name, Type are Mandatory")) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            CreateResponse response = new CreateResponse(e.getMessage(), status.value());
            return ResponseEntity.status(status).body(response);
        }
    }

    @PutMapping("/seed/{id}")
    public ResponseEntity<CreateResponse> updateSeed(@PathVariable int id, @RequestBody Seed seed) {
        try {
            String result = seedService.updateSeed(id, seed);
            HttpStatus httpStatus = HttpStatus.OK;

            CreateResponse response = new CreateResponse(result, httpStatus.value());
            return ResponseEntity.status(httpStatus).body(response);
        } catch (SeedValidation e) {
            HttpStatus status;
            if (e.getMessage().equals("Seed with the same name already exists")) {
                status = HttpStatus.CONFLICT;
            } else if (e.getMessage().equals("Name, Type are Mandatory") ) {
                status = HttpStatus.BAD_REQUEST;
            } else if (e.getMessage().equals("Seed not found")){
                status = HttpStatus.NOT_FOUND;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            CreateResponse response = new CreateResponse(e.getMessage(), status.value());
            return ResponseEntity.status(status).body(response);
        }
    }

    @GetMapping("/seed")
    public ResponseEntity<List<Seed>> getAllSeeds() {
        try {
            List<Seed> seeds = seedService.getAllSeeds();
            return ResponseEntity.ok(seeds);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/seed/{id}")
    public ResponseEntity<Object> getSeedById(@PathVariable int id) {
        Optional<Seed> seed = Optional.ofNullable(seedService.getSeed(id));
        if (seed.isPresent()) {
            return ResponseEntity.ok(seed.get());
        } else {
            CreateResponse response = new CreateResponse("Seed not found", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/seed/{id}")
    public ResponseEntity<CreateResponse> deleteSeedById(@PathVariable int id) {
        try {
            String result = seedService.deleteSeed(id);
            CreateResponse response = new CreateResponse(result, HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } catch (SeedValidation e) {
            HttpStatus status;
            if (e.getMessage().equals("Seed not found")) {
                status = HttpStatus.NOT_FOUND;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            CreateResponse response = new CreateResponse(e.getMessage(), status.value());
            return ResponseEntity.status(status).body(response);
        }
    }


    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Plant> plants = plantService.getAllPlants();
            List<Planter> planters = planterService.getAllPlanters();
            List<Seed> seeds = seedService.getAllSeeds();

            CombinedProduct combinedProduct = new CombinedProduct(plants, planters, seeds);
            log.error("combined");
            return ResponseEntity.ok(combinedProduct);
        } catch (Exception e) {
            log.error("Error retrieving all products", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
