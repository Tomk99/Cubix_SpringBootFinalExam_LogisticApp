package com.example.logisticapp.dto;

import jakarta.validation.constraints.*;

import java.util.Objects;

public class AddressDto {

    private long id;
    @NotNull
    private int countryCode;
    @NotEmpty
    private String zipCode;
    @NotEmpty
    private String city;
    @NotEmpty
    private String street;
    @NotEmpty
    private String houseNumber;
    private int longitude;
    private int latitude;

    public AddressDto(long id, int countryCode, String zipCode, String city, String street, String houseNumber, int longitude, int latitude) {
        this.id = id;
        this.countryCode = countryCode;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
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
