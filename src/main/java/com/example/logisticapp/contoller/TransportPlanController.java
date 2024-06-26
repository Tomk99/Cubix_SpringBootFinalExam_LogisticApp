package com.example.logisticapp.contoller;

import com.example.logisticapp.dto.MilestoneDelayDto;
import com.example.logisticapp.dto.TransportPlanDto;
import com.example.logisticapp.mapper.TransportPlanMapper;
import com.example.logisticapp.service.TransportPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

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
        try {
            transportPlanService.addDelay(id,milestoneDelayDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/addSection/{sectionId}")
    private TransportPlanDto addSection(@PathVariable long id, @PathVariable long sectionId) {
        return transportPlanMapper.planToDto(transportPlanService.addSection(id,sectionId));
    }

    @DeleteMapping
    private void deleteAll() {
        transportPlanService.deleteAll();
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable long id) {
        transportPlanService.deleteById(id);
    }
}
