package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.model.Planter;

import java.util.List;

public interface PlanterService {

    public String createPlanter(Planter planter);
    public String updatePlanter(int id,Planter planter);
    public String deletePlanter(int id);
    public Planter getPlanter(int id);
    public List<Planter> getAllPlanters();

}
