package com.techphantomexample.Productmicroservice.repository;

import com.techphantomexample.Productmicroservice.model.Planter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanterRepository extends JpaRepository< Planter , Integer>{

}