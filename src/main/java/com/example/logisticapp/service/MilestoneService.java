package com.example.logisticapp.service;

import com.example.logisticapp.model.Address;
import com.example.logisticapp.model.Milestone;
import com.example.logisticapp.repository.AddressRepository;
import com.example.logisticapp.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MilestoneService {

    @Autowired
    MilestoneRepository milestoneRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Milestone> findAll() {
        return milestoneRepository.findAll();
    }

    @Transactional
    public Milestone create(Milestone milestone) {
        return milestoneRepository.save(milestone);
    }

    @Transactional
    public Milestone setAddress(long id, long addressId) {
        Milestone milestone = milestoneRepository.findById(id).orElseThrow(NoSuchElementException::new);
        milestone.setAddress(addressRepository.findById(addressId).orElseThrow(NoSuchElementException::new));
        return milestoneRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
