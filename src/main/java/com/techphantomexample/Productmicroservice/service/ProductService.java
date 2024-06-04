package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.model.BaseProduct;
import com.techphantomexample.Productmicroservice.model.Plant;
import com.techphantomexample.Productmicroservice.model.Planter;
import com.techphantomexample.Productmicroservice.model.Seed;
import com.techphantomexample.Productmicroservice.repository.PlantRepository;
import com.techphantomexample.Productmicroservice.repository.PlanterRepository;
import com.techphantomexample.Productmicroservice.repository.SeedRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {


    private static final Logger log = LogManager.getLogger(ProductService.class);
    @Autowired
    PlantRepository plantRepository;
    @Autowired
    PlanterRepository planterRepository;
    @Autowired
    SeedRepository seedRepository;


    public String createProduct(BaseProduct baseProduct) {
        try {
            ProductOperationException.validateProduct(baseProduct,plantRepository,planterRepository,seedRepository);
            log.info("Base product validation success");

            // Save product based on its category
            switch (baseProduct.getCategory().toUpperCase()) {
                case "PLANT":
                    plantRepository.save((Plant) baseProduct);
                    break;
                case "PLANTER":
                    planterRepository.save((Planter) baseProduct);
                    break;
                case "SEED":
                    seedRepository.save((Seed) baseProduct);
                    break;
                default:
                    throw new ProductOperationException("Invalid product category");
            }

            return "Product Created successfully";
        } catch (ProductOperationException e) {
            throw e;
        } catch (Exception e) {
            throw new ProductOperationException("Error creating product", e);
        }
    }
    public List<BaseProduct> getAllProducts() {
        try {
            List<BaseProduct> allProducts = new ArrayList<>();
            allProducts.addAll(plantRepository.findAll());
            allProducts.addAll(planterRepository.findAll());
            allProducts.addAll(seedRepository.findAll());
            return allProducts;
        } catch (Exception e) {
            throw new ProductOperationException("Error retrieving products", e);
        }
    }



}
