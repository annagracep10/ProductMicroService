package com.techphantomexample.Productmicroservice.repository;

import com.techphantomexample.Productmicroservice.model.BaseProduct;
import com.techphantomexample.Productmicroservice.model.Seed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeedRepository extends JpaRepository<Seed, Integer> {

}