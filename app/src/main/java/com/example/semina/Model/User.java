package com.example.semina.Model;

public class User {
    private String id;
    private String username;
    private String ImageUrl;

    public User(String id, String username, String ImageUrl) {
        this.id = id;
        this.username = username;
        this.ImageUrl = ImageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}


