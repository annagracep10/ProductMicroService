package com.techphantomexample.Productmicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seeds_info")
public class Seed extends BaseProduct {

    private String seedType;
    private int germinationTime;
    private String season;


}

