package com.example.semina.Model;

public class Data {

    private String date;
    private String image;
    private String  name;
    private String  phone;
    private String Des;
    private String Address;

    public Data() {
    }

    public Data(String image, String name, String phone, String Des, String Address) {
        this.image = image;
        this.name = name;
        this.phone = phone;
        this.Des = Des;
        this.Address = Address;

    }

    public Data(String image, String name,  String Des, String Address) {
        this.image = image;
        this.name = name;
        this.Des = Des;
        this.Address = Address;

    }





    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getDes() {
        return Des;
    }

    public void setDes(String Des) {
        this.Des = Des;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}



