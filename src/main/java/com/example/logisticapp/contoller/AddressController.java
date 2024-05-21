package com.example.logisticapp.contoller;

import com.example.logisticapp.dto.AddressDto;
import com.example.logisticapp.mapper.AddressMapper;
import com.example.logisticapp.model.Address;
import com.example.logisticapp.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;
    @Autowired
    AddressMapper addressMapper;

    @GetMapping
    private List<AddressDto> findAll() {
        return addressMapper.addressesToDtos(addressService.findAll());
    }

    @GetMapping("/{id}")
    private AddressDto findById(@PathVariable long id) {
            Address address;
        try {
            address = addressService.findById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return addressMapper.addressToDto(address);
    }

    @PostMapping
    private AddressDto addNewAddress(@RequestBody @Valid AddressDto addressDto) {
        Address savedAddress;
        try {
            savedAddress = addressService.create(addressMapper.dtoToAddress(addressDto));
        } catch (InputMismatchException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return addressMapper.addressToDto(savedAddress);
    }

    @PutMapping("/{id}")
    private AddressDto updateAddress(@PathVariable long id, @RequestBody @Valid AddressDto addressDto) {
        if (addressDto == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Address updatedAddress;
        try {
            updatedAddress = addressService.update(id, addressMapper.dtoToAddress(addressDto));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return addressMapper.addressToDto(updatedAddress);
    }

    @DeleteMapping("/{id}")
    private void deleteById(@PathVariable long id) {
        addressService.deleteById(id);
    }

    @PostMapping("/search")
    private ResponseEntity<List<AddressDto>> searchBySpecs(@RequestBody AddressDto addressDto, @SortDefault("addressId") Pageable pageable) {
        List<AddressDto> addressDtos;
        long totalElements;
        try {
            Page<Address> page = addressService.findBySpecs(addressMapper.dtoToAddress(addressDto), pageable);
            List<Address> content = page.getContent();
            totalElements = page.getTotalElements();
            addressDtos = addressMapper.addressesToDtos(content);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(totalElements)).body(addressDtos);
    }
}
