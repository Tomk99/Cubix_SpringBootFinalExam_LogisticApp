package com.example.logisticapp.mapper;

import com.example.logisticapp.dto.AddressDto;
import com.example.logisticapp.model.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto addressToDto(Address address);
    Address dtoToAddress(AddressDto addressDto);
    List<AddressDto> addressesToDtos(List<Address> addresses);
    List<Address> dtosToAddresses(List<AddressDto> addressDtos);
}
