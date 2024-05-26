package com.example.logisticapp.service;

import com.example.logisticapp.config.LogisticConfig;
import com.example.logisticapp.dto.MilestoneDelayDto;
import com.example.logisticapp.model.Milestone;
import com.example.logisticapp.model.Section;
import com.example.logisticapp.model.TransportPlan;
import com.example.logisticapp.repository.SectionRepository;
import com.example.logisticapp.repository.TransportPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransportPlanService {

    @Autowired
    TransportPlanRepository transportPlanRepository;
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    LogisticConfig config;

    public List<TransportPlan> findAll() {
        return transportPlanRepository.findAll();
    }
    @Transactional
    public TransportPlan create(TransportPlan transportPlan) {
        return transportPlanRepository.save(transportPlan);
    }
    @Transactional
    public void addDelay(long id, MilestoneDelayDto milestoneDelayDto) {
        TransportPlan transportPlan = transportPlanRepository.findById(id).orElseThrow(NoSuchElementException::new);
        List<Section> startMilestoneSection = transportPlan.getSections().stream().filter(section -> section.getStartPoint().getMilestoneId() == milestoneDelayDto.getMilestoneId()).toList();
        List<Section> endMilestoneSection = transportPlan.getSections().stream().filter(section -> section.getEndPoint().getMilestoneId() == milestoneDelayDto.getMilestoneId()).toList();
        if (startMilestoneSection.isEmpty() && endMilestoneSection.isEmpty()) throw new NullPointerException();
        if (endMilestoneSection.isEmpty()) {
            Milestone endPoint = startMilestoneSection.get(0).getEndPoint();
            endPoint.setPlannedTime(endPoint.getPlannedTime().plusMinutes(milestoneDelayDto.getMilestoneDelay()));
        }
        if (startMilestoneSection.isEmpty()) {
            int orderInPlan = endMilestoneSection.get(0).getOrderInPlan();
            Section nextSection = sectionRepository.findByOrderInPlan(orderInPlan + 1);
            Milestone startPoint = nextSection.getStartPoint();
            startPoint.setPlannedTime(startPoint.getPlannedTime().plusMinutes(milestoneDelayDto.getMilestoneDelay()));
        }
        double min30 = config.getDelay().getMin30();
        double min60 = config.getDelay().getMin60();
        double min120 = config.getDelay().getMin120();
        if (milestoneDelayDto.getMilestoneDelay() > 30 && milestoneDelayDto.getMilestoneDelay() < 60) transportPlan.setIncome((int) recalculateIncome(transportPlan, min30));
        if (milestoneDelayDto.getMilestoneDelay() > 60 && milestoneDelayDto.getMilestoneDelay() < 120) transportPlan.setIncome((int) recalculateIncome(transportPlan, min60));
        if (milestoneDelayDto.getMilestoneDelay() > 120) transportPlan.setIncome((int) recalculateIncome(transportPlan, min120));

    }
    private static double recalculateIncome(TransportPlan transportPlan, double delayFeePercent) {
        return transportPlan.getIncome() * ((100 - delayFeePercent) / 100);
    }

    @Transactional
    public TransportPlan addSection(long id, long sectionId) {
        TransportPlan transportPlan = transportPlanRepository.findById(id).orElseThrow(NoSuchElementException::new);
        Section section = sectionRepository.findById(sectionId).orElseThrow(NoSuchElementException::new);
        transportPlan.getSections().add(section);
        return transportPlanRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
