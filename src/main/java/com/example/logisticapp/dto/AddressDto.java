package com.example.logisticapp.dto;

import jakarta.validation.constraints.*;

import java.util.Objects;

public class AddressDto {

    private long id;
    @NotNull
    private int countryCode;
    @NotNull
    private int zipCode;
    @NotEmpty
    private String city;
    @NotEmpty
    private String street;
    @NotNull
    private int houseNumber;
    private int longitude;
    private int latitude;

    public AddressDto(long id, int countryCode, int zipCode, String city, String street, int houseNumber, int longitude, int latitude) {
        this.id = id;
        this.countryCode = countryCode;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public AddressDto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto that = (AddressDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}