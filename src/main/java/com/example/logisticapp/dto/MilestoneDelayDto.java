package com.example.logisticapp.dto;

import com.example.logisticapp.model.Milestone;

import java.util.Objects;

public class MilestoneDelayDto {
    private long milestoneId;
    private int milestoneDelay;

    public MilestoneDelayDto(long milestoneId, int milestoneDelay) {
        this.milestoneId = milestoneId;
        this.milestoneDelay = milestoneDelay;
    }

    public long getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(long milestoneId) {
        this.milestoneId = milestoneId;
    }

    public int getMilestoneDelay() {
        return milestoneDelay;
    }

    public void setMilestoneDelay(int milestoneDelay) {
        this.milestoneDelay = milestoneDelay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MilestoneDelayDto that = (MilestoneDelayDto) o;
        return milestoneId == that.milestoneId && milestoneDelay == that.milestoneDelay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(milestoneId, milestoneDelay);
    }
}
