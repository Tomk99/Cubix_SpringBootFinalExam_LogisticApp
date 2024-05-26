package com.example.logisticapp.dto;

import com.example.logisticapp.model.Milestone;

import java.util.Objects;

public class SectionDto {
    private long id;
    private int orderInPlan;
    private Milestone startPoint;
    private Milestone endPoint;

    public SectionDto(long id, int orderInPlan, Milestone startPoint, Milestone endPoint) {
        this.id = id;
        this.orderInPlan = orderInPlan;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrderInPlan() {
        return orderInPlan;
    }

    public void setOrderInPlan(int orderInPlan) {
        this.orderInPlan = orderInPlan;
    }

    public Milestone getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Milestone startPoint) {
        this.startPoint = startPoint;
    }

    public Milestone getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Milestone endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionDto that = (SectionDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
