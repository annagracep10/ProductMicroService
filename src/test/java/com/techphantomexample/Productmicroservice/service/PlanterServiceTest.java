package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.exception.PlanterException;
import com.techphantomexample.Productmicroservice.model.Planter;
import com.techphantomexample.Productmicroservice.repository.PlanterRepository;
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
class PlanterServiceTest {

    @Mock
    private PlanterRepository planterRepository;

    @InjectMocks
    private PlanterService planterService;

    private Planter planter;

    @BeforeEach
    public void setUp() {
        planter = new Planter();
        planter.setId(1);
        planter.setName("Test Planter");
        planter.setDescription("Test Description");
        planter.setPrice(10.0);
        planter.setCategory("Test Category");
        planter.setQuantity(5);
        planter.setMaterial("Test Material");
        planter.setDimensions("10x10x10");
        planter.setColor("Green");
    }

    @Test
    public void testCreatePlanter_Success() {
        when(planterRepository.existsByName(planter.getName())).thenReturn(false);

        String result = planterService.createPlanter(planter);

        assertEquals("Planter Created Successfully", result);
        verify(planterRepository, times(1)).save(planter);
    }

    @Test
    public void testCreatePlanter_PlanterExists() {
        when(planterRepository.existsByName(planter.getName())).thenReturn(true);

        Exception exception = assertThrows(PlanterException.class, () -> {
            planterService.createPlanter(planter);
        });

        assertEquals("Planter already exists", exception.getMessage());
        verify(planterRepository, never()).save(planter);
    }

    @Test
    public void testUpdatePlanter_Success() {
        when(planterRepository.existsById(planter.getId())).thenReturn(true);
        when(planterRepository.findById(planter.getId())).thenReturn(Optional.of(planter));

        Planter updatedPlanter = new Planter();
        updatedPlanter.setName("Updated Name");
        updatedPlanter.setDescription("Updated Description");
        updatedPlanter.setPrice(15.0);
        updatedPlanter.setCategory("Updated Category");
        updatedPlanter.setQuantity(10);
        updatedPlanter.setMaterial("Updated Material");
        updatedPlanter.setDimensions("15x15x15");
        updatedPlanter.setColor("Blue");

        String result = planterService.updatePlanter(planter.getId(), updatedPlanter);

        assertEquals("Planter Updated Successfully", result);
        verify(planterRepository, times(1)).save(planter);
    }

    @Test
    public void testUpdatePlanter_NotFound() {
        when(planterRepository.existsById(planter.getId())).thenReturn(false);

        Exception exception = assertThrows(PlanterException.class, () -> {
            planterService.updatePlanter(planter.getId(), planter);
        });

        assertEquals("Planter not found", exception.getMessage());
        verify(planterRepository, never()).save(planter);
    }

    @Test
    public void testDeletePlanter_Success() {
        when(planterRepository.existsById(planter.getId())).thenReturn(true);
        doNothing().when(planterRepository).deleteById(planter.getId());

        String result = planterService.deletePlanter(planter.getId());

        assertEquals("Planter Deleted Successfully", result);
        verify(planterRepository, times(1)).deleteById(planter.getId());
    }

    @Test
    public void testDeletePlanter_NotFound() {
        when(planterRepository.existsById(planter.getId())).thenReturn(false);

        Exception exception = assertThrows(PlanterException.class, () -> {
            planterService.deletePlanter(planter.getId());
        });

        assertEquals("Planter not found", exception.getMessage());
        verify(planterRepository, never()).deleteById(planter.getId());
    }

    @Test
    public void testGetPlanter_Success() {
        when(planterRepository.existsById(planter.getId())).thenReturn(true);
        when(planterRepository.findById(planter.getId())).thenReturn(Optional.of(planter));

        Planter result = planterService.getPlanter(planter.getId());

        assertEquals(planter, result);
    }

    @Test
    public void testGetPlanter_NotFound() {
        when(planterRepository.existsById(planter.getId())).thenReturn(false);

        Exception exception = assertThrows(PlanterException.class, () -> {
            planterService.getPlanter(planter.getId());
        });

        assertEquals("Planter not found", exception.getMessage());
    }

    @Test
    public void testGetAllPlanters() {
        Planter planter1 = new Planter();
        planter1.setName("Planter 1");
        Planter planter2 = new Planter();
        planter2.setName("Planter 2");

        when(planterRepository.findAll()).thenReturn(Arrays.asList(planter1, planter2));

        List<Planter> result = planterService.getAllPlanters();

        assertEquals(2, result.size());
        assertTrue(result.contains(planter1));
        assertTrue(result.contains(planter2));
    }
}

