package com.example.logisticapp.contoller;

import com.example.logisticapp.dto.SectionDto;
import com.example.logisticapp.mapper.SectionMapper;
import com.example.logisticapp.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionController {
    @Autowired
    SectionService sectionService;
    @Autowired
    SectionMapper sectionMapper;

    @GetMapping
    private List<SectionDto> findAll() {
        return sectionMapper.sectionsToDtos(sectionService.findAll());
    }
    @PostMapping
    private SectionDto createNewSection(@RequestBody SectionDto sectionDto) {
        return sectionMapper.sectionToDto(sectionService.create(sectionMapper.dtoToSection(sectionDto)));
    }
    @PutMapping("/{id}/startPoint/{startPointId}")
    private SectionDto setStartPoint(@PathVariable long id, @PathVariable long startPointId) {
        return sectionMapper.sectionToDto(sectionService.setStartPoint(id,startPointId));
    }
    @PutMapping("/{id}/endPoint/{endPointId}")
    private SectionDto setEndPoint(@PathVariable long id, @PathVariable long endPointId) {
        return sectionMapper.sectionToDto(sectionService.setEndPoint(id,endPointId));
    }
}
