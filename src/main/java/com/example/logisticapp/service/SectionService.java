package com.example.logisticapp.service;

import com.example.logisticapp.model.Section;
import com.example.logisticapp.repository.MilestoneRepository;
import com.example.logisticapp.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    MilestoneRepository milestoneRepository;

    public List<Section> findAll() {
        return sectionRepository.findAll();
    }
    @Transactional
    public Section create(Section section) {
        return sectionRepository.save(section);
    }
    @Transactional
    public Section setStartPoint(long id, long startPointId) {
        Section section = sectionRepository.findById(id).orElseThrow(NoSuchElementException::new);
        section.setStartPoint(milestoneRepository.findById(startPointId).orElseThrow(NoSuchElementException::new));
        return sectionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
    @Transactional
    public Section setEndPoint(long id, long endPointId) {
        Section section = sectionRepository.findById(id).orElseThrow(NoSuchElementException::new);
        section.setEndPoint(milestoneRepository.findById(endPointId).orElseThrow(NoSuchElementException::new));
        return sectionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
