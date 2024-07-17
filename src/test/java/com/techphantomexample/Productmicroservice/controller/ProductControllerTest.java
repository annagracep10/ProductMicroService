package com.techphantomexample.Productmicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techphantomexample.Productmicroservice.model.Plant;
import com.techphantomexample.Productmicroservice.model.Planter;
import com.techphantomexample.Productmicroservice.model.Seed;
import com.techphantomexample.Productmicroservice.service.PlantService;
import com.techphantomexample.Productmicroservice.service.PlanterService;
import com.techphantomexample.Productmicroservice.service.SeedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlantService plantService;

    @MockBean
    private PlanterService planterService;

    @MockBean
    private SeedService seedService;

    @MockBean
    private RestTemplate restTemplate;

    private Plant plant;
    private Planter planter;
    private Seed seed;

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

        planter = new Planter();
        planter.setId(1);
        planter.setName("Test Planter");
        planter.setDescription("Test Description");
        planter.setPrice(20.0);
        planter.setCategory("Test Category");
        planter.setQuantity(5);
        planter.setMaterial("Ceramic");
        planter.setDimensions("10x10x10");
        planter.setColor("White");

        seed = new Seed();
        seed.setId(1);
        seed.setName("Test Seed");
        seed.setDescription("Test Description");
        seed.setPrice(5.0);
        seed.setCategory("Test Category");
        seed.setSeedType("Test Type");
        seed.setQuantity(10);
    }

    @Test
    public void testCreatePlant() throws Exception {
        when(plantService.createPlant(any(Plant.class))).thenReturn("Plant Created successfully");

        mockMvc.perform(post("/product/plant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(plant)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Plant Created successfully"))
                .andExpect(jsonPath("$.status").value(200));

        verify(plantService, times(1)).createPlant(any(Plant.class));
    }

    @Test
    public void testUpdatePlant() throws Exception {
        when(plantService.updatePlant(eq(1), any(Plant.class))).thenReturn("Plant Updated Successfully");

        mockMvc.perform(put("/product/plant/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(plant)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Plant Updated Successfully"))
                .andExpect(jsonPath("$.status").value(200));

        verify(plantService, times(1)).updatePlant(eq(1), any(Plant.class));
    }

    @Test
    public void testGetAllPlants() throws Exception {
        List<Plant> plants = Arrays.asList(plant);
        when(plantService.getAllPlants()).thenReturn(plants);

        mockMvc.perform(get("/product/plant"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Plant"));

        verify(plantService, times(1)).getAllPlants();
    }

    @Test
    public void testGetPlantById() throws Exception {
        when(plantService.getPlant(1)).thenReturn(plant);

        mockMvc.perform(get("/product/plant/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Plant"));

        verify(plantService, times(1)).getPlant(1);
    }

    @Test
    public void testDeletePlantById() throws Exception {
        when(plantService.deletePlant(1)).thenReturn("Plant Deleted Successfully");

        mockMvc.perform(delete("/product/plant/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Plant Deleted Successfully"))
                .andExpect(jsonPath("$.status").value(200));

        verify(plantService, times(1)).deletePlant(1);
    }

    @Test
    public void testCreatePlanter() throws Exception {
        when(planterService.createPlanter(any(Planter.class))).thenReturn("Planter Created Successfully");

        mockMvc.perform(post("/product/planter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(planter)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Planter Created Successfully"))
                .andExpect(jsonPath("$.status").value(200));

        verify(planterService, times(1)).createPlanter(any(Planter.class));
    }

    @Test
    public void testUpdatePlanter() throws Exception {
        when(planterService.updatePlanter(eq(1), any(Planter.class))).thenReturn("Planter Updated Successfully");

        mockMvc.perform(put("/product/planter/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(planter)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Planter Updated Successfully"))
                .andExpect(jsonPath("$.status").value(200));

        verify(planterService, times(1)).updatePlanter(eq(1), any(Planter.class));
    }

    @Test
    public void testGetAllPlanters() throws Exception {
        List<Planter> planters = Arrays.asList(planter);
        when(planterService.getAllPlanters()).thenReturn(planters);

        mockMvc.perform(get("/product/planter"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Planter"));

        verify(planterService, times(1)).getAllPlanters();
    }

    @Test
    public void testGetPlanterById() throws Exception {
        when(planterService.getPlanter(1)).thenReturn(planter);

        mockMvc.perform(get("/product/planter/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Planter"));

        verify(planterService, times(1)).getPlanter(1);
    }

    @Test
    public void testDeletePlanterById() throws Exception {
        when(planterService.deletePlanter(1)).thenReturn("Planter Deleted Successfully");

        mockMvc.perform(delete("/product/planter/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Planter Deleted Successfully"))
                .andExpect(jsonPath("$.status").value(200));

        verify(planterService, times(1)).deletePlanter(1);
    }

    @Test
    public void testCreateSeed() throws Exception {
        when(seedService.createSeed(any(Seed.class))).thenReturn("Seed Created Successfully");

        mockMvc.perform(post("/product/seed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(seed)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Seed Created Successfully"))
                .andExpect(jsonPath("$.status").value(200));

        verify(seedService, times(1)).createSeed(any(Seed.class));
    }

    @Test
    public void testUpdateSeed() throws Exception {
        when(seedService.updateSeed(eq(1), any(Seed.class))).thenReturn("Seed Updated Successfully");

        mockMvc.perform(put("/product/seed/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(seed)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Seed Updated Successfully"))
                .andExpect(jsonPath("$.status").value(200));

        verify(seedService, times(1)).updateSeed(eq(1), any(Seed.class));
    }

    @Test
    public void testGetAllSeeds() throws Exception {
        List<Seed> seeds = Arrays.asList(seed);
        when(seedService.getAllSeeds()).thenReturn(seeds);

        mockMvc.perform(get("/product/seed"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Seed"));

        verify(seedService, times(1)).getAllSeeds();
    }

    @Test
    public void testGetSeedById() throws Exception {
        when(seedService.getSeed(1)).thenReturn(seed);

        mockMvc.perform(get("/product/seed/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Seed"));

        verify(seedService, times(1)).getSeed(1);
    }

    @Test
    public void testDeleteSeedById() throws Exception {
        when(seedService.deleteSeed(1)).thenReturn("Seed Deleted Successfully");

        mockMvc.perform(delete("/product/seed/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Seed Deleted Successfully"))
                .andExpect(jsonPath("$.status").value(200));

        verify(seedService, times(1)).deleteSeed(1);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}