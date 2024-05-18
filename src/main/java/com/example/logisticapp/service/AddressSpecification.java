package com.example.logisticapp.service;

import com.example.logisticapp.model.Address;
import com.example.logisticapp.model.Address_;
import org.springframework.data.jpa.domain.Specification;

public class AddressSpecification {
    public static Specification<Address> cityStartsWith(String city) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(Address_.CITY)),city.toLowerCase()+"%");
    }
    public static Specification<Address> streetNameStartsWith(String streetName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(Address_.STREET)),streetName.toLowerCase()+"%");
    }
}
