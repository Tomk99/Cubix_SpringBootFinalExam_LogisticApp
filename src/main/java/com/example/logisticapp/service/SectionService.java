package com.example.logisticapp.service;

import com.example.logisticapp.model.Section;
import com.example.logisticapp.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository;

    public List<Section> findAll() {
        return sectionRepository.findAll();
    }
}
