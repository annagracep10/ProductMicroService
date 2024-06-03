package com.techphantomexample.Productmicroservice.service;


import com.techphantomexample.Productmicroservice.model.Seed;

import java.util.List;

public interface SeedService {
    public String createSeed(Seed seed);
    public String updateSeed(int id,Seed seed);
    public String deleteSeed(int id);
    public Seed getSeed(int id);
    public List<Seed> getAllSeeds();
}
