package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.exception.SeedException;
import com.techphantomexample.Productmicroservice.model.Seed;
import com.techphantomexample.Productmicroservice.repository.SeedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeedServiceTest {

    @Mock
    private SeedRepository seedRepository;

    @InjectMocks
    private SeedService seedService;

    private Seed seed;

    @BeforeEach
    public void setUp() {
        seed = new Seed();
        seed.setId(1);
        seed.setName("Test Seed");
        seed.setDescription("Test Description");
        seed.setPrice(10.0);
        seed.setSeedType("Test Type");
        seed.setCategory("Test Category");
        seed.setQuantity(5);
    }

    @Test
    public void testCreateSeed_Success() {
        when(seedRepository.existsByName(seed.getName())).thenReturn(false);

        String result = seedService.createSeed(seed);

        assertEquals("Seed Created Successfully", result);
        verify(seedRepository, times(1)).save(seed);
    }

    @Test
    public void testCreateSeed_SeedExists() {
        when(seedRepository.existsByName(seed.getName())).thenReturn(true);

        Exception exception = assertThrows(SeedException.class, () -> {
            seedService.createSeed(seed);
        });

        assertEquals("Seed already exists", exception.getMessage());
        verify(seedRepository, never()).save(seed);
    }

    @Test
    public void testUpdateSeed_Success() {
        when(seedRepository.existsById(seed.getId())).thenReturn(true);
        when(seedRepository.findById(seed.getId())).thenReturn(Optional.of(seed));

        Seed updatedSeed = new Seed();
        updatedSeed.setName("Updated Name");
        updatedSeed.setDescription("Updated Description");
        updatedSeed.setPrice(15.0);
        updatedSeed.setCategory("Updated Category");
        updatedSeed.setSeedType("Updated Type");
        updatedSeed.setQuantity(10);

        String result = seedService.updateSeed(seed.getId(), updatedSeed);

        assertEquals("Seed Updated Successfully", result);
        verify(seedRepository, times(1)).save(seed);
    }

    @Test
    public void testUpdateSeed_NotFound() {
        when(seedRepository.existsById(seed.getId())).thenReturn(false);

        Exception exception = assertThrows(SeedException.class, () -> {
            seedService.updateSeed(seed.getId(), seed);
        });

        assertEquals("Seed not found", exception.getMessage());
        verify(seedRepository, never()).save(seed);
    }

    @Test
    public void testDeleteSeed_Success() {
        when(seedRepository.existsById(seed.getId())).thenReturn(true);
        doNothing().when(seedRepository).deleteById(seed.getId());

        String result = seedService.deleteSeed(seed.getId());

        assertEquals("Seed Deleted Successfully", result);
        verify(seedRepository, times(1)).deleteById(seed.getId());
    }

    @Test
    public void testDeleteSeed_NotFound() {
        when(seedRepository.existsById(seed.getId())).thenReturn(false);

        Exception exception = assertThrows(SeedException.class, () -> {
            seedService.deleteSeed(seed.getId());
        });

        assertEquals("Seed not found", exception.getMessage());
        verify(seedRepository, never()).deleteById(seed.getId());
    }

    @Test
    public void testGetSeed_Success() {
        when(seedRepository.existsById(seed.getId())).thenReturn(true);
        when(seedRepository.findById(seed.getId())).thenReturn(Optional.of(seed));

        Seed result = seedService.getSeed(seed.getId());

        assertEquals(seed, result);
    }

    @Test
    public void testGetSeed_NotFound() {
        when(seedRepository.existsById(seed.getId())).thenReturn(false);

        Exception exception = assertThrows(SeedException.class, () -> {
            seedService.getSeed(seed.getId());
        });

        assertEquals("Seed not found", exception.getMessage());
    }

    @Test
    public void testGetAllSeeds() {
        Seed seed1 = new Seed();
        seed1.setName("Seed 1");
        Seed seed2 = new Seed();
        seed2.setName("Seed 2");

        when(seedRepository.findAll()).thenReturn(Arrays.asList(seed1, seed2));

        List<Seed> result = seedService.getAllSeeds();

        assertEquals(2, result.size());
        assertTrue(result.contains(seed1));
        assertTrue(result.contains(seed2));
    }
}