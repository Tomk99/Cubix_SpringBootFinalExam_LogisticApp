package com.example.logisticapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Section {
    @Id
    @GeneratedValue
    private long id;
    private int orderInPlan;
    @ManyToOne
    private Milestone startPoint;
    @ManyToOne
    private Milestone endPoint;
}
