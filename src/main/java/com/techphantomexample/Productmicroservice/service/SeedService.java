package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.dto.SeedDto;
import com.techphantomexample.Productmicroservice.exception.SeedException;
import com.techphantomexample.Productmicroservice.model.Seed;
import com.techphantomexample.Productmicroservice.repository.SeedRepository;
import com.techphantomexample.Productmicroservice.validators.SeedValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedService {

    @Autowired
    SeedRepository seedRepository;

    public SeedService(SeedRepository seedRepository) {
        this.seedRepository = seedRepository;
    }

    public String createSeed(SeedDto seedDto) {
        if (seedRepository.existsByName(seedDto.getName())) {
            throw new SeedException("Seed already exists");
        }
        SeedValidation.validateSeed(seedDto, seedRepository);
        Seed seed = new Seed();
        seed.setName(seedDto.getName());
        seed.setDescription(seedDto.getDescription());
        seed.setPrice(seedDto.getPrice());
        seed.setCategory(seedDto.getCategory());
        seed.setImage(null);
        seed.setQuantity(seedDto.getQuantity());
        seed.setSeedType(seedDto.getSeedType());
        seed.setGerminationTime(seedDto.getGerminationTime());
        seed.setSeason(seedDto.getSeason());
        seedRepository.save(seed);
        return "Seed Created Successfully";

    }

    public String updateSeed(int id, SeedDto newSeedDetails) {
        if (!seedRepository.existsById(id)) {
            throw new SeedException("Seed not found");
        }
        Seed existingSeed = seedRepository.findById(id).get();
        SeedValidation.validateSeed(newSeedDetails,seedRepository);
        existingSeed.setName(newSeedDetails.getName());
        existingSeed.setDescription(newSeedDetails.getDescription());
        existingSeed.setPrice(newSeedDetails.getPrice());
        existingSeed.setCategory(newSeedDetails.getCategory());
        existingSeed.setQuantity(newSeedDetails.getQuantity());
        existingSeed.setSeedType(newSeedDetails.getSeedType());
        existingSeed.setGerminationTime(newSeedDetails.getGerminationTime());
        existingSeed.setSeason(newSeedDetails.getSeason());

        seedRepository.save(existingSeed);
        return "Seed Updated Successfully";
    }

    public String deleteSeed(int seedId) {
        if (!seedRepository.existsById(seedId)) {
            throw new SeedException("Seed not found");
        }
        seedRepository.deleteById(seedId);
        return "Seed Deleted Successfully";
    }

    public Seed getSeed(int seedId) {
        if (!seedRepository.existsById(seedId)) {
            throw  new SeedException("Seed not found");
        }
        return seedRepository.findById(seedId).get();
    }

    public List<Seed> getAllSeeds() {
        return seedRepository.findAll();
    }

    public void updateSeedQuantity(int productId, int quantityToSubtract) {
        Seed seed = getSeed(productId);
        seed.setQuantity(seed.getQuantity() + quantityToSubtract);
        seedRepository.save(seed);
    }
}
