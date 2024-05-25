package com.example.logisticapp.contoller;

import com.example.logisticapp.dto.MilestoneDelayDto;
import com.example.logisticapp.dto.TransportPlanDto;
import com.example.logisticapp.mapper.TransportPlanMapper;
import com.example.logisticapp.service.TransportPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlanController {

    @Autowired
    TransportPlanService transportPlanService;
    @Autowired
    TransportPlanMapper transportPlanMapper;

    @GetMapping
    private List<TransportPlanDto> findAll() {
        return transportPlanMapper.plansToDtos(transportPlanService.findAll());
    }

    @PostMapping
    private TransportPlanDto createNewPlan(@RequestBody TransportPlanDto transportPlanDto) {
        return transportPlanMapper.planToDto(transportPlanService.create(transportPlanMapper.dtoToPlan(transportPlanDto)));
    }

    @PostMapping("/{id}/delay")
    private void delay(@PathVariable long id, @RequestBody MilestoneDelayDto milestoneDelayDto) {
        transportPlanService.addDelay(id,milestoneDelayDto);
    }
}
