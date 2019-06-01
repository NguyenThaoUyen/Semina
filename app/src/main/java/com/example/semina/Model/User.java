package com.example.semina.Model;

public class User {
    private String id;
    private String username;
    private String ImageUrl;
    private String review;

    public User(String id, String username, String ImageUrl, String review) {
        this.id = id;
        this.username = username;
        this.ImageUrl = ImageUrl;
        this.review=review;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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


