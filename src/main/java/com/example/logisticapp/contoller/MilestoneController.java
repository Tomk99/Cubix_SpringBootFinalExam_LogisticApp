package com.example.logisticapp.contoller;

import com.example.logisticapp.dto.MilestoneDto;
import com.example.logisticapp.mapper.MilestoneMapper;
import com.example.logisticapp.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/milestones")
public class MilestoneController {

    @Autowired
    MilestoneService milestoneService;
    @Autowired
    MilestoneMapper milestoneMapper;

    @GetMapping
    public List<MilestoneDto> findAll() {
        return milestoneMapper.milestonesToDtos(milestoneService.findAll());
    }

    @PostMapping
    public MilestoneDto createNewMilestone(@RequestBody MilestoneDto milestoneDto) {
        return milestoneMapper.milestoneToDto(milestoneService.create(milestoneMapper.dtoToMilestone(milestoneDto)));
    }
}
