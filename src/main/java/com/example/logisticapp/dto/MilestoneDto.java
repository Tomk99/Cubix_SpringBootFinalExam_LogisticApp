package com.example.logisticapp.dto;

import com.example.logisticapp.model.Address;

import java.time.LocalDateTime;

public class MilestoneDto {
    private long milestoneId;
    private LocalDateTime plannedTime;
    private Address address;
}
