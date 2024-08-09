package com.techphantomexample.Productmicroservice.controller;

import com.techphantomexample.Productmicroservice.dto.PlantDto;
import com.techphantomexample.Productmicroservice.dto.PlanterDto;
import com.techphantomexample.Productmicroservice.dto.SeedDto;
import com.techphantomexample.Productmicroservice.exception.PlantException;
import com.techphantomexample.Productmicroservice.exception.PlanterException;
import com.techphantomexample.Productmicroservice.exception.SeedException;
import com.techphantomexample.Productmicroservice.model.*;
import com.techphantomexample.Productmicroservice.repository.PlantRepository;
import com.techphantomexample.Productmicroservice.repository.PlanterRepository;
import com.techphantomexample.Productmicroservice.repository.SeedRepository;
import com.techphantomexample.Productmicroservice.service.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    PlantService plantService;
    @Autowired
    PlanterService planterService;
    @Autowired
    SeedService seedService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    PlantRepository plantRepository;
    @Autowired
    PlanterRepository planterRepository;
    @Autowired
    SeedRepository seedRepository;


    @PostMapping("/plant")
    public CreateResponse createProduct(@RequestBody PlantDto plant) {
        String response = plantService.createPlant(plant);
        CreateResponse createResponse = new CreateResponse(response, HttpStatus.OK.value());
        return createResponse;
    }

    @PutMapping("/plant/{id}")
    public CreateResponse updatePlant(@PathVariable int id, @RequestBody PlantDto plant) {
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
    public ResponseEntity<Plant> getPlantById(@PathVariable int id) {
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
    public CreateResponse createPlanter(@RequestBody PlanterDto planter) {
        String response = planterService.createPlanter(planter);
        CreateResponse createResponse = new CreateResponse(response, HttpStatus.OK.value());
        return createResponse;
    }

    @PutMapping("/planter/{id}")
    public CreateResponse updatePlanter(@PathVariable int id, @RequestBody PlanterDto planter) {
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
    public ResponseEntity<Planter> getPlanterById(@PathVariable int id) {
        Planter planter = planterService.getPlanter(id);
        return new ResponseEntity<>(planter, HttpStatus.OK);
    }

    @DeleteMapping("/planter/{id}")
    public CreateResponse deletePlanterById(@PathVariable int id) {
        String result = planterService.deletePlanter(id);
        CreateResponse createResponse = new CreateResponse(result, HttpStatus.OK.value());
        return createResponse;
    }

    @PostMapping("/seed")
    public CreateResponse createSeed(@RequestBody SeedDto seed) {
        String response = seedService.createSeed(seed);
        CreateResponse createResponse = new CreateResponse(response, HttpStatus.OK.value());
        return createResponse;
    }

    @PutMapping("/seed/{id}")
    public CreateResponse updateSeed(@PathVariable int id, @RequestBody SeedDto seed) {
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
    public ResponseEntity<Seed> getSeedById(@PathVariable int id) {
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

    @PutMapping("/{productType}/{productId}/quantity")
    public ResponseEntity<?> updateQuantity(@PathVariable String productType, @PathVariable int productId, @RequestBody Map<String, Integer> quantityUpdate) {
        int quantityToSubtract = quantityUpdate.get("quantityToSubtract");

        switch (productType) {
            case "plant":
                plantService.updatePlantQuantity(productId, quantityToSubtract);
                break;
            case "planter":
                planterService.updatePlanterQuantity(productId, quantityToSubtract);
                break;
            case "seed":
                seedService.updateSeedQuantity(productId, quantityToSubtract);
                break;
            default:
                return new ResponseEntity<>("Product Type Unavailable", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Quantity updated successfully", HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("productId") int productId,
            @RequestParam("productType") String productType) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            switch (productType.toLowerCase()) {
                case "plant":
                    Plant plant = plantRepository.findById(productId)
                            .orElseThrow(() -> new PlantException("Plant not found"));
                    plant.setImage(file.getBytes());
                    plantRepository.save(plant);
                    break;
                case "planter":
                    Planter planter = planterRepository.findById(productId)
                            .orElseThrow(() -> new PlanterException("Planter not found"));
                    planter.setImage(file.getBytes());
                    planterRepository.save(planter);
                    break;
                case "seed":
                    Seed seed = seedRepository.findById(productId)
                            .orElseThrow(() -> new SeedException("Seed not found"));
                    seed.setImage(file.getBytes());
                    seedRepository.save(seed);
                    break;
                default:
                    return ResponseEntity.badRequest().body("Invalid product type");
            }
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing file: " + e.getMessage());
        }
    }



}
