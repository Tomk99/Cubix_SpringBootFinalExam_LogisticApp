package com.example.logisticapp.service;

import com.example.logisticapp.model.Address;
import com.example.logisticapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.logisticapp.service.AddressSpecification.*;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(long id) {
        return addressRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Address create(Address address) {
        if (address.getAddressId() != 0) throw new InputMismatchException();
        return addressRepository.save(address);
    }
    @Transactional
    public Address update(long id, Address address) {
        addressRepository.findById(id).orElseThrow(NoSuchElementException::new);
        address.setAddressId(id);
        return addressRepository.save(address);
    }
    @Transactional
    public void deleteById(long id) {
        addressRepository.deleteById(id);
    }

    public Page<Address> findBySpecs(Address address, Pageable pageable) {

        String city = address.getCity();
        String street = address.getStreet();
        int countryCode = address.getCountryCode();
        int zipCode = address.getZipCode();

        if (city.isBlank() && street.isBlank() && countryCode == 0 && zipCode == 0) {
            throw new NoSuchElementException();
        }

        Specification<Address> specs = Specification.where(null);
        if (!city.isEmpty()) {
            specs = specs.and(cityStartsWith(city));
        }
        if (!street.isEmpty()) {
            specs = specs.and(streetNameStartsWith(street));
        }
        if (countryCode > 0) {
            specs = specs.and(countryCodeEquals(countryCode));
        }
        if (zipCode > 0) {
            specs = specs.and(zipCodeEquals(zipCode));
        }

        return addressRepository.findAll(specs, pageable);
    }
}
