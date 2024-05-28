package com.example.logisticapp.service;

import com.example.logisticapp.model.LogisticUser;
import com.example.logisticapp.repository.AddressRepository;
import com.example.logisticapp.repository.LogisticUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class InitDbService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private LogisticUserRepository logisticUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initDb() {
        addressRepository.deleteAll();
        System.out.println("\033[0;35m"+"DATABASE INITIALIZED!"+"\033[0m");
    }

    public void initUsers() {
        if (!logisticUserRepository.existsById("address1")) {
            logisticUserRepository.save(new LogisticUser("address1", passwordEncoder.encode("pass"), Set.of("AddressManager")));
        }
        if (!logisticUserRepository.existsById("transport1")) {
            logisticUserRepository.save(new LogisticUser("transport1", passwordEncoder.encode("pass"), Set.of("TransportManager")));
        }
    }
}
