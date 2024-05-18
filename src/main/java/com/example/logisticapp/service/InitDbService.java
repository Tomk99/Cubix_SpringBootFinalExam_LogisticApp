package com.example.logisticapp.service;

import com.example.logisticapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitDbService {

    @Autowired
    private AddressRepository addressRepository;

    public void initDb() {
        addressRepository.deleteAll();
        System.out.println("\033[0;35m"+"DATABASE INITIALIZED!"+"\033[0m");
    }
}
