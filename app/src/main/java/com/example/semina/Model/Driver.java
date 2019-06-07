package com.example.semina.Model;

public class Driver {
    private String  name;
    private String  phone;
    private String Address;

    public Driver() {
    }
    public Driver(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        Address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
