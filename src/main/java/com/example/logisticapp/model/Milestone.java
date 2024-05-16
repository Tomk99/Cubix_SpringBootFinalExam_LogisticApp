package com.example.logisticapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Milestone {
    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime plannedTime;
    @ManyToOne
    private Address address;

    public Milestone(long id, LocalDateTime plannedTime, Address address) {
        this.id = id;
        this.plannedTime = plannedTime;
        this.address = address;
    }
    public Milestone() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(LocalDateTime plannedTime) {
        this.plannedTime = plannedTime;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
