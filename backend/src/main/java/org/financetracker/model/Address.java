package org.financetracker.model;

import java.util.Objects;

public class Address {
    private int id;
    private String country;
    private String city;
    private String street;
    private String building;
    private String stage;
    private String apartment;
    private String doorcode;

    public Address(String country, String city, String street, String building, String stage, String apartment, String doorcode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
        this.stage = stage;
        this.apartment = apartment;
        this.doorcode = doorcode;
    }

    public Address(int id, String country, String city, String street, String building, String stage, String apartment, String doorcode) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
        this.stage = stage;
        this.apartment = apartment;
        this.doorcode = doorcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getDoorcode() {
        return doorcode;
    }

    public void setDoorcode(String doorcode) {
        this.doorcode = doorcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && Objects.equals(country, address.country) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(building, address.building) && Objects.equals(stage, address.stage) && Objects.equals(apartment, address.apartment) && Objects.equals(doorcode, address.doorcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, building, stage, apartment, doorcode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", stage='" + stage + '\'' +
                ", apartment='" + apartment + '\'' +
                ", doorcode='" + doorcode + '\'' +
                '}';
    }
}
