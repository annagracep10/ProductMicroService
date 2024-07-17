package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.exception.PlantException;
import com.techphantomexample.Productmicroservice.model.Plant;
import com.techphantomexample.Productmicroservice.repository.PlantRepository;
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
class PlantServiceTest {

    @Mock
    private PlantRepository plantRepository;

    @InjectMocks
    private PlantService plantService;

    private Plant plant;

    @BeforeEach
    public void setUp() {
        plant = new Plant();
        plant.setId(1);
        plant.setName("Test Plant");
        plant.setDescription("Test Description");
        plant.setPrice(10.0);
        plant.setCategory("Test Category");
        plant.setQuantity(5);
        plant.setTypeOfPlant("Indoor");
        plant.setSunlightRequirements("Low");
        plant.setWateringFrequency("Weekly");
    }

    @Test
    public void testCreatePlant_Success() {
        when(plantRepository.existsByName(plant.getName())).thenReturn(false);


        String result = plantService.createPlant(plant);

        assertEquals("Plant Created successfully", result);
        verify(plantRepository, times(1)).save(plant);
    }

    @Test
    public void testCreatePlant_PlantExists() {
        when(plantRepository.existsByName(plant.getName())).thenReturn(true);

        Exception exception = assertThrows(PlantException.class, () -> {
            plantService.createPlant(plant);
        });

        assertEquals("Plant already exists", exception.getMessage());
        verify(plantRepository, never()).save(plant);
    }

    @Test
    public void testUpdatePlant_Success() {
        when(plantRepository.existsById(plant.getId())).thenReturn(true);
        when(plantRepository.findById(plant.getId())).thenReturn(Optional.of(plant));

        Plant updatedPlant = new Plant();
        updatedPlant.setName("Updated Name");
        updatedPlant.setDescription("Updated Description");
        updatedPlant.setPrice(15.0);
        updatedPlant.setCategory("Updated Category");
        updatedPlant.setQuantity(10);
        updatedPlant.setTypeOfPlant("Outdoor");
        updatedPlant.setSunlightRequirements("High");
        updatedPlant.setWateringFrequency("Daily");

        String result = plantService.updatePlant(plant.getId(), updatedPlant);

        assertEquals("Plant Updated Successfully", result);
        verify(plantRepository, times(1)).save(plant);
    }

    @Test
    public void testUpdatePlant_NotFound() {
        when(plantRepository.existsById(plant.getId())).thenReturn(false);

        Exception exception = assertThrows(PlantException.class, () -> {
            plantService.updatePlant(plant.getId(), plant);
        });

        assertEquals("Plant not found", exception.getMessage());
        verify(plantRepository, never()).save(plant);
    }

    @Test
    public void testDeletePlant_Success() {
        when(plantRepository.existsById(plant.getId())).thenReturn(true);
        doNothing().when(plantRepository).deleteById(plant.getId());

        String result = plantService.deletePlant(plant.getId());

        assertEquals("Plant Deleted Successfully", result);
        verify(plantRepository, times(1)).deleteById(plant.getId());
    }

    @Test
    public void testDeletePlant_NotFound() {
        when(plantRepository.existsById(plant.getId())).thenReturn(false);

        Exception exception = assertThrows(PlantException.class, () -> {
            plantService.deletePlant(plant.getId());
        });

        assertEquals("Plant not found", exception.getMessage());
        verify(plantRepository, never()).deleteById(plant.getId());
    }

    @Test
    public void testGetPlant_Success() {
        when(plantRepository.existsById(plant.getId())).thenReturn(true);
        when(plantRepository.findById(plant.getId())).thenReturn(Optional.of(plant));

        Plant result = plantService.getPlant(plant.getId());

        assertEquals(plant, result);
    }

    @Test
    public void testGetPlant_NotFound() {
        when(plantRepository.existsById(plant.getId())).thenReturn(false);

        Exception exception = assertThrows(PlantException.class, () -> {
            plantService.getPlant(plant.getId());
        });

        assertEquals("Plant not found", exception.getMessage());
    }

    @Test
    public void testGetAllPlants() {
        Plant plant1 = new Plant();
        plant1.setName("Plant 1");
        Plant plant2 = new Plant();
        plant2.setName("Plant 2");

        when(plantRepository.findAll()).thenReturn(Arrays.asList(plant1, plant2));

        List<Plant> result = plantService.getAllPlants();

        assertEquals(2, result.size());
        assertTrue(result.contains(plant1));
        assertTrue(result.contains(plant2));
    }
}