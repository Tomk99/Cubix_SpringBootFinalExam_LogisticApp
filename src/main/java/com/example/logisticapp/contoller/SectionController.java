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
    public List<SectionDto> findAll() {
        return sectionMapper.sectionsToDtos(sectionService.findAll());
    }
    @PostMapping
    public SectionDto createNewSection(@RequestBody SectionDto sectionDto) {
        return sectionMapper.sectionToDto(sectionService.create(sectionMapper.dtoToSection(sectionDto)));
    }
}
