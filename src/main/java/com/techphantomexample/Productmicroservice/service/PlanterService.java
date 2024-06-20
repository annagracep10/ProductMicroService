package com.techphantomexample.Productmicroservice.service;

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
        try {
            PlanterValidation.validatePlanter(planter, planterRepository);
            planterRepository.save(planter);
            return "Planter Created Successfully";
        } catch (PlanterValidation e) {
            throw e;
        } catch (Exception e) {
            throw new PlanterValidation("Error creating planter", e);
        }
    }

    public String updatePlanter(int id, Planter newPlanterDetails) {
        try {
            if (!planterRepository.existsById(id)) {
                throw new PlanterValidation("Planter not found");
            }

            Planter existingPlanter = planterRepository.findById(id).get();

            PlanterValidation.validateUpdatePlanter(newPlanterDetails);

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
        } catch (PlanterValidation e) {
            throw e;
        } catch (Exception e) {
            throw new PlanterValidation("Error updating planter", e);
        }
    }

    public String deletePlanter(int planterId) {
        try {
            if (!planterRepository.existsById(planterId)) {
                throw new PlanterValidation("Planter not found");
            }
            planterRepository.deleteById(planterId);
            return "Planter Deleted Successfully";
        } catch (PlanterValidation e) {
            throw e;
        } catch (Exception e) {
            throw new PlanterValidation("Error deleting planter", e);
        }
    }

    public Planter getPlanter(int planterId) {
        try {
            if (!planterRepository.existsById(planterId)) {
                return null;
            }
            return planterRepository.findById(planterId).get();
        } catch (Exception e) {
            throw new PlanterValidation("Error retrieving planter", e);
        }
    }

    public List<Planter> getAllPlanters() {
        try {
            return planterRepository.findAll();
        } catch (Exception e) {
            throw new PlanterValidation("Error retrieving planters", e);
        }
    }
}
