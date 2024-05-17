package com.example.logisticapp.dto;

import com.example.logisticapp.model.Milestone;
import com.example.logisticapp.model.TransportPlan;

public class SectionDto {
    private long sectionId;
    private int orderInPlan;
    private Milestone startPoint;
    private Milestone endPoint;
    private TransportPlan transportPlan;
}
