package com.example.logisticapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Milestone {
    @Id
    @GeneratedValue
    private long milestoneId;
    private LocalDateTime plannedTime;
    @ManyToOne
    private Address address;

    public Milestone(long milestoneId, LocalDateTime plannedTime, Address address) {
        this.milestoneId = milestoneId;
        this.plannedTime = plannedTime;
        this.address = address;
    }

    public Milestone() {

    }

    public long getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(long milestoneId) {
        this.milestoneId = milestoneId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Milestone milestone = (Milestone) o;
        return milestoneId == milestone.milestoneId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(milestoneId);
    }
}
