package com.example.semina.Model;



public class Hotel {

    private String image;
    private String  name;
    private String Des;
    private String Address;





    public Hotel() {
    }

    public Hotel(String image, String name, String Des, String Address) {
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
}



