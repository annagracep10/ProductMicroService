package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.exception.PlantException;
import com.techphantomexample.Productmicroservice.exception.PlanterException;
import com.techphantomexample.Productmicroservice.validators.PlanterValidation;
import com.techphantomexample.Productmicroservice.model.Planter;
import com.techphantomexample.Productmicroservice.repository.PlanterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanterService {

    private final PlanterRepository planterRepository;

    public PlanterService(PlanterRepository planterRepository) {
        this.planterRepository = planterRepository;
    }

    public String createPlanter(Planter planter) {
        if (planterRepository.existsByName(planter.getName())) {
            throw new PlanterException("Planter already exists");
        }
        PlanterValidation.validatePlanter(planter, planterRepository);
        planterRepository.save(planter);
        return "Planter Created Successfully";

    }

    public String updatePlanter(int id, Planter newPlanterDetails) {
        if (!planterRepository.existsById(id)) {
            throw new PlanterException("Planter not found");
        }
        Planter existingPlanter = planterRepository.findById(id).get();
        PlanterValidation.validatePlanter(newPlanterDetails,planterRepository);
        existingPlanter.setName(newPlanterDetails.getName());
        existingPlanter.setDescription(newPlanterDetails.getDescription());
        existingPlanter.setPrice(newPlanterDetails.getPrice());
        existingPlanter.setCategory(newPlanterDetails.getCategory());
        existingPlanter.setQuantity(newPlanterDetails.getQuantity());
        existingPlanter.setMaterial(newPlanterDetails.getMaterial());
        existingPlanter.setDimensions(newPlanterDetails.getDimensions());
        existingPlanter.setColor(newPlanterDetails.getColor());

        planterRepository.save(existingPlanter);
        return "Planter Updated Successfully";
    }

    public String deletePlanter(int planterId) {
        if (!planterRepository.existsById(planterId)) {
            throw new PlanterException("Planter not found");
        }
        planterRepository.deleteById(planterId);
        return "Planter Deleted Successfully";
    }

    public Planter getPlanter(int planterId) {
        if (!planterRepository.existsById(planterId)) {
            throw  new PlanterException("Planter not found");
        }
        return planterRepository.findById(planterId).get();
    }

    public List<Planter> getAllPlanters() {
        return planterRepository.findAll();

    }
}
