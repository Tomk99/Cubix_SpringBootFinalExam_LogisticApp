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
    private long id;
    private int orderInPlan;
    @ManyToOne
    private Milestone startPoint;
    @ManyToOne
    private Milestone endPoint;
    @ManyToOne
    private TransportPlan transportPlan;

    public Section(long id, int orderInPlan, Milestone startPoint, Milestone endPoint, TransportPlan transportPlan) {
        this.id = id;
        this.orderInPlan = orderInPlan;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.transportPlan = transportPlan;
    }

    public Section() {

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

    public TransportPlan getTransportPlan() {
        return transportPlan;
    }

    public void setTransportPlan(TransportPlan transportPlan) {
        this.transportPlan = transportPlan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return id == section.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
