package com.example.logisticapp.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class MilestoneDto {
    private long id;
    private LocalDateTime plannedTime;
    private long addressId;

    public MilestoneDto(long id, LocalDateTime plannedTime, long addressId) {
        this.id = id;
        this.plannedTime = plannedTime;
        this.addressId = addressId;
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

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
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
