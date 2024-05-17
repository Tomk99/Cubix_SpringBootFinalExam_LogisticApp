package com.example.logisticapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;

@Entity
public class TransportPlan {
    @Id
    @GeneratedValue
    private long id;
    private int income;
    @OneToMany
    private List<Section> sections;

    public TransportPlan(long id, int income, List<Section> sections) {
        this.id = id;
        this.income = income;
        this.sections = sections;
    }

    public TransportPlan() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportPlan that = (TransportPlan) o;
        return id == that.id && income == that.income && Objects.equals(sections, that.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, income, sections);
    }
}