package com.techphantomexample.Productmicroservice.controller;

import com.techphantomexample.Productmicroservice.exception.PlantValidation;
import com.techphantomexample.Productmicroservice.exception.PlanterValidation;
import com.techphantomexample.Productmicroservice.exception.SeedValidation;
import com.techphantomexample.Productmicroservice.model.*;
import com.techphantomexample.Productmicroservice.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    public CreateResponse createProduct(@RequestBody Plant plant) {
        String response = plantService.createPlant(plant);
        CreateResponse createResponse = new CreateResponse(response, HttpStatus.OK.value());
        return createResponse;
    }

    @PutMapping("/plant/{id}")
    public CreateResponse updatePlant(@PathVariable int id, @RequestBody Plant plant) {
        String response = plantService.updatePlant(id, plant);
        CreateResponse createResponse = new CreateResponse(response, HttpStatus.OK.value());
        return createResponse;
    }

    @GetMapping("/plant")
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant> plants = plantService.getAllPlants();
        return new ResponseEntity<>(plants, HttpStatus.OK);
    }

    @GetMapping("/plant/{id}")
    public ResponseEntity<?> getPlantById(@PathVariable int id) {
        Plant plant = plantService.getPlant(id);
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }

    @DeleteMapping("/plant/{id}")
    public CreateResponse deletePlantById(@PathVariable int id) {
        String response = plantService.deletePlant(id);
        CreateResponse createresponse = new CreateResponse(response, HttpStatus.OK.value());
        return createresponse;
    }





    @PostMapping("/planter")
    public CreateResponse createPlanter(@RequestBody Planter planter) {
        String response = planterService.createPlanter(planter);
        CreateResponse createResponse = new CreateResponse(response, HttpStatus.OK.value());
        return createResponse;
    }

    @PutMapping("/planter/{id}")
    public CreateResponse updatePlanter(@PathVariable int id, @RequestBody Planter planter) {
        String response = planterService.updatePlanter(id, planter);
        CreateResponse createResponse = new CreateResponse(response, HttpStatus.OK.value());
        return createResponse;
    }

    @GetMapping("/planter")
    public ResponseEntity<List<Planter>> getAllPlanters() {
        List<Planter> planters = planterService.getAllPlanters();
        return new ResponseEntity<>(planters, HttpStatus.OK);
    }

    @GetMapping("/planter/{id}")
    public ResponseEntity<Object> getPlanterById(@PathVariable int id) {
        Planter planter = planterService.getPlanter(id);
        return new ResponseEntity<>(planter, HttpStatus.OK);
    }

    @DeleteMapping("/planter/{id}")
    public CreateResponse deletePlanterById(@PathVariable int id) {
        String result = plantService.deletePlant(id);
        CreateResponse createResponse = new CreateResponse(result, HttpStatus.OK.value());
        return createResponse;
    }




    @PostMapping("/seed")
    public CreateResponse createSeed(@RequestBody Seed seed) {
        String response = seedService.createSeed(seed);
        CreateResponse createResponse = new CreateResponse(response, HttpStatus.OK.value());
        return createResponse;
    }

    @PutMapping("/seed/{id}")
    public CreateResponse updateSeed(@PathVariable int id, @RequestBody Seed seed) {
        String response = seedService.updateSeed(id, seed);
        CreateResponse createResponse = new CreateResponse(response, HttpStatus.OK.value());
        return createResponse;
    }

    @GetMapping("/seed")
    public ResponseEntity<List<Seed>> getAllSeeds() {
        List<Seed> seeds = seedService.getAllSeeds();
        return new ResponseEntity<>(seeds, HttpStatus.OK);
    }

    @GetMapping("/seed/{id}")
    public ResponseEntity<Object> getSeedById(@PathVariable int id) {
        Seed seed = seedService.getSeed(id);
        return new ResponseEntity<>(seed, HttpStatus.OK);
    }

    @DeleteMapping("/seed/{id}")
    public CreateResponse deleteSeedById(@PathVariable int id) {
        String result = seedService.deleteSeed(id);
        CreateResponse createResponse = new CreateResponse(result, HttpStatus.OK.value());
        return createResponse;
    }


    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {

        List<Plant> plants = plantService.getAllPlants();
        List<Planter> planters = planterService.getAllPlanters();
        List<Seed> seeds = seedService.getAllSeeds();

        CombinedProduct combinedProduct = new CombinedProduct(plants, planters, seeds);
        log.error("combined");
        return ResponseEntity.ok(combinedProduct);
    }


}
