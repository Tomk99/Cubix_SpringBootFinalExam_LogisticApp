package com.example.logisticapp.contoller;

import com.example.logisticapp.dto.SectionDto;
import com.example.logisticapp.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionController {
    @Autowired
    SectionService sectionService;

    @GetMapping
    public List<SectionDto> findAll() {
        return sectionService.findAll();
    }
}
