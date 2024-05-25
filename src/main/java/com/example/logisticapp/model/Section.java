package com.example.logisticapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class Section {
    @Id
    @GeneratedValue
    private long sectionId;
    private int orderInPlan;
    @ManyToOne
    private Milestone startPoint;
    @ManyToOne
    private Milestone endPoint;

    public Section(long id, int orderInPlan, Milestone startPoint, Milestone endPoint) {
        this.sectionId = id;
        this.orderInPlan = orderInPlan;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Section() {

    }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
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
        Section section = (Section) o;
        return sectionId == section.sectionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionId);
    }
}
