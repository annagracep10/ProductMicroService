package com.techphantomexample.Productmicroservice.repository;

import com.techphantomexample.Productmicroservice.model.Plant;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends BaseProductRepository<Plant> {
}