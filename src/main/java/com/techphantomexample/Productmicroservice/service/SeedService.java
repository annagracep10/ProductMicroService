package com.techphantomexample.Productmicroservice.service;

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
        try {
            SeedValidation.validateSeed(seed, seedRepository);
            seedRepository.save(seed);
            return "Seed Created Successfully";
        } catch (SeedValidation e) {
            throw e;
        } catch (Exception e) {
            throw new SeedValidation("Error creating seed", e);
        }
    }

    public String updateSeed(int id, Seed newSeedDetails) {
        try {
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
        } catch (SeedValidation e) {
            throw e;
        } catch (Exception e) {
            throw new SeedValidation("Error updating seed", e);
        }
    }

    public String deleteSeed(int seedId) {
        try {
            if (!seedRepository.existsById(seedId)) {
                throw new SeedValidation("Seed not found");
            }
            seedRepository.deleteById(seedId);
            return "Seed Deleted Successfully";
        } catch (SeedValidation e) {
            throw e;
        } catch (Exception e) {
            throw new SeedValidation("Error deleting seed", e);
        }
    }

    public Seed getSeed(int seedId) {
        try {
            if (!seedRepository.existsById(seedId)) {
                return null;
            }
            return seedRepository.findById(seedId).get();
        } catch (Exception e) {
            throw new SeedValidation("Error retrieving seed", e);
        }
    }

    public List<Seed> getAllSeeds() {
        try {
            return seedRepository.findAll();
        } catch (Exception e) {
            throw new SeedValidation("Error retrieving seeds", e);
        }
    }
}
