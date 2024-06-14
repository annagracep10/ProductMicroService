package com.techphantomexample.Productmicroservice.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.techphantomexample.Productmicroservice.model.BaseProduct;
import jakarta.persistence.*;
import lombok.*;

//@JsonTypeName("planter")
@Entity
@Table(name="planter_info")
public class Planter extends BaseProduct {
    private String material;
    private String dimensions;
    private String color;

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Planter(String name, String description, double price, String category, int quantity, String material, String dimensions, String color) {
        super(name, description, price, category, quantity);
        this.material = material;
        this.dimensions = dimensions;
        this.color = color;
    }

    public Planter() {
    }
}
