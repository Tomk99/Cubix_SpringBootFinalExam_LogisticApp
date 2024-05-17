package com.example.logisticapp.contoller;

import com.example.logisticapp.dto.AddressDto;
import com.example.logisticapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @GetMapping
    private List<AddressDto> findAll() {
        return addressRepository.findAll();
    }
}