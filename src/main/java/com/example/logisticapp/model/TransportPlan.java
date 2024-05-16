package com.example.logisticapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class TransportPlan {
    @Id
    @GeneratedValue
    private long id;
    private int income;
    @OneToMany
    private List<Section> sections;
}
