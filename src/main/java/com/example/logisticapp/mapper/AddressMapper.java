package com.example.logisticapp.mapper;

import com.example.logisticapp.dto.AddressDto;
import com.example.logisticapp.model.Address;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id",source = "addressId")
    AddressDto addressToDto(Address address);
    @InheritInverseConfiguration
    Address dtoToAddress(AddressDto addressDto);
    List<AddressDto> addressesToDtos(List<Address> addresses);
    List<Address> dtosToAddresses(List<AddressDto> addressDtos);
}
