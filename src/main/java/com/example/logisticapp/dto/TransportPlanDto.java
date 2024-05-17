package com.example.logisticapp.dto;

import com.example.logisticapp.model.Section;

import java.util.List;
import java.util.Objects;

public class TransportPlanDto {

    private long transportPlanId;
    private int income;
    private List<Section> sections;

    public TransportPlanDto(long transportPlanId, int income, List<Section> sections) {
        this.transportPlanId = transportPlanId;
        this.income = income;
        this.sections = sections;
    }

    public long getTransportPlanId() {
        return transportPlanId;
    }

    public void setTransportPlanId(long transportPlanId) {
        this.transportPlanId = transportPlanId;
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
        return transportPlanId == that.transportPlanId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transportPlanId);
    }
}
