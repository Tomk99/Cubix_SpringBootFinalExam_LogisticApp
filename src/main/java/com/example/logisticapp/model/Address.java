package com.example.logisticapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private long addressId;
    private int countryCode;
    private int zipCode;
    private String city;
    private String street;
    private int houseNumber;
    private int longitude;
    private int latitude;

    public Address(long addressId,
                   int countryCode,
                   int zipCode,
                   String city,
                   String street,
                   int houseNumber,
                   int longitude,
                   int latitude) {
        this.addressId = addressId;
        this.countryCode = countryCode;
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Address() {

    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
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
        Address address = (Address) o;
        return addressId == address.addressId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId);
    }
}
