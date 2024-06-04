package com.techphantomexample.Productmicroservice.repository;

import com.techphantomexample.Productmicroservice.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant,Integer>
{
    boolean existsByName(String name);
}