package com.techphantomexample.Productmicroservice.service;

import com.techphantomexample.Productmicroservice.dto.PlanterDto;
import com.techphantomexample.Productmicroservice.exception.PlanterException;
import com.techphantomexample.Productmicroservice.validators.PlanterValidation;
import com.techphantomexample.Productmicroservice.model.Planter;
import com.techphantomexample.Productmicroservice.repository.PlanterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanterService {

    @Autowired
    PlanterRepository planterRepository;

    public PlanterService(PlanterRepository planterRepository) {
        this.planterRepository = planterRepository;
    }

    public String createPlanter(PlanterDto planterdto) {
        if (planterRepository.existsByName(planterdto.getName())) {
            throw new PlanterException("Planter already exists");
        }
        PlanterValidation.validatePlanter(planterdto, planterRepository);
        Planter planter = new Planter();
        planter.setName(planterdto.getName());
        planter.setDescription(planterdto.getDescription());
        planter.setPrice(planterdto.getPrice());
        planter.setCategory(planterdto.getCategory());
        planter.setImage(null);
        planter.setQuantity(planterdto.getQuantity());
        planter.setMaterial(planterdto.getMaterial());
        planter.setDimensions(planterdto.getDimensions());
        planter.setColor(planterdto.getColor());
        planterRepository.save(planter);
        return "Planter Created Successfully";

    }

    public String updatePlanter(int id, PlanterDto newPlanterDetails) {
        if (!planterRepository.existsById(id)) {
            throw new PlanterException("Planter not found");
        }
        Planter existingPlanter = planterRepository.findById(id).get();
        if (newPlanterDetails.getName() != null) {
            existingPlanter.setName(newPlanterDetails.getName());
        }
        if (newPlanterDetails.getDescription() != null) {
            existingPlanter.setDescription(newPlanterDetails.getDescription());
        }
        if (newPlanterDetails.getPrice() != 0) {
            existingPlanter.setPrice(newPlanterDetails.getPrice());
        }
        if (newPlanterDetails.getCategory() != null) {
            existingPlanter.setCategory(newPlanterDetails.getCategory());
        }
        if (newPlanterDetails.getQuantity() != 0) {
            existingPlanter.setQuantity(newPlanterDetails.getQuantity());
        }
        if (newPlanterDetails.getMaterial() != null) {
            existingPlanter.setMaterial(newPlanterDetails.getMaterial());
        }
        if (newPlanterDetails.getDimensions() != null) {
            existingPlanter.setDimensions(newPlanterDetails.getDimensions());
        }
        if (newPlanterDetails.getColor() != null) {
            existingPlanter.setColor(newPlanterDetails.getColor());
        }

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

    public void updatePlanterQuantity(int productId, int quantityToSubtract) {
        Planter planter = getPlanter(productId);
        planter.setQuantity(planter.getQuantity() + quantityToSubtract); // Add or subtract based on the sign of quantityToSubtract
        planterRepository.save(planter);
    }
}
