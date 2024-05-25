package com.example.logisticapp.service;

import com.example.logisticapp.dto.MilestoneDelayDto;
import com.example.logisticapp.model.TransportPlan;
import com.example.logisticapp.repository.TransportPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransportPlanService {

    @Autowired
    TransportPlanRepository transportPlanRepository;

    public List<TransportPlan> findAll() {
        return transportPlanRepository.findAll();
    }

    public TransportPlan create(TransportPlan transportPlan) {
        return transportPlanRepository.save(transportPlan);
    }

    public void addDelay(long id, MilestoneDelayDto milestoneDelayDto) {
        TransportPlan transportPlan = transportPlanRepository.findById(id).orElseThrow(NoSuchElementException::new);
        transportPlan.getSections().stream().filter(section -> section.getStartPoint().getMilestoneId() == milestoneDelayDto.getMilestoneId()).forEach(section -> section.getEndPoint().setPlannedTime(section.getEndPoint().getPlannedTime().plusMinutes(milestoneDelayDto.getMilestoneDelay())));
    }
}
