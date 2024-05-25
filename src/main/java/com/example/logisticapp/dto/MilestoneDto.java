package com.example.logisticapp.dto;

import com.example.logisticapp.model.Address;

import java.time.LocalDateTime;
import java.util.Objects;

public class MilestoneDto {
    private long id;
    private LocalDateTime plannedTime;
    private Address address;

    public MilestoneDto(long id, LocalDateTime plannedTime, Address address) {
        this.id = id;
        this.plannedTime = plannedTime;
        this.address = address;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MilestoneDto that = (MilestoneDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
