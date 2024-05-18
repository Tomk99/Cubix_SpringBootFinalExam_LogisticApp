package com.example.logisticapp.dto;

import com.example.logisticapp.model.Section;

import java.util.List;
import java.util.Objects;

public class TransportPlanDto {

    private long id;
    private int income;
    private List<Section> sections;

    public TransportPlanDto(long id, int income, List<Section> sections) {
        this.id = id;
        this.income = income;
        this.sections = sections;
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
        TransportPlanDto that = (TransportPlanDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
