package com.techphantomexample.Productmicroservice.model;


import jakarta.persistence.*;

@Entity
@Table(name = "seeds_info")
public class Seed extends BaseProduct {
    private String seedType;
    private int germinationTime;
    private String season;

    public Seed(String name, String description, double price, String category, int quantity, String seedType, int germinationTime, String season) {
        super(name, description, price, category, quantity);
        this.seedType = seedType;
        this.germinationTime = germinationTime;
        this.season = season;
    }
// Getters and Setters

    public String getSeedType() {
        return seedType;
    }

    public void setSeedType(String seedType) {
        this.seedType = seedType;
    }

    public int getGerminationTime() {
        return germinationTime;
    }

    public void setGerminationTime(int germinationTime) {
        this.germinationTime = germinationTime;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}

