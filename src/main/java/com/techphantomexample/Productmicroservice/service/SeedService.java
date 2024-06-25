package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.exception.PlantValidation;
import com.techphantomexample.Productmicroservice.exception.SeedValidation;
import com.techphantomexample.Productmicroservice.model.Seed;
import com.techphantomexample.Productmicroservice.repository.SeedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedService {

    private final SeedRepository seedRepository;

    public SeedService(SeedRepository seedRepository) {
        this.seedRepository = seedRepository;
    }

    public String createSeed(Seed seed) {
        if (seedRepository.existsByName(seed.getName())) {
            throw new SeedValidation("Seed already exists");
        }
        SeedValidation.validateSeed(seed, seedRepository);
        seedRepository.save(seed);
        return "Seed Created Successfully";

    }

    public String updateSeed(int id, Seed newSeedDetails) {
        if (!seedRepository.existsById(id)) {
            throw new SeedValidation("Seed not found");
        }
        Seed existingSeed = seedRepository.findById(id).get();
        SeedValidation.validateUpdateSeed(newSeedDetails);
        existingSeed.setName(newSeedDetails.getName());
        existingSeed.setDescription(newSeedDetails.getDescription());
        existingSeed.setPrice(newSeedDetails.getPrice());
        existingSeed.setCategory(newSeedDetails.getCategory());
        existingSeed.setQuantity(newSeedDetails.getQuantity());

        seedRepository.save(existingSeed);
        return "Seed Updated Successfully";
    }

    public String deleteSeed(int seedId) {
        if (!seedRepository.existsById(seedId)) {
            throw new SeedValidation("Seed not found");
        }
        seedRepository.deleteById(seedId);
        return "Seed Deleted Successfully";
    }

    public Seed getSeed(int seedId) {
        if (!seedRepository.existsById(seedId)) {
            throw  new SeedValidation("Seed not found");
        }
        return seedRepository.findById(seedId).get();
    }

    public List<Seed> getAllSeeds() {
        return seedRepository.findAll();
    }
}
