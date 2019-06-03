package com.example.semina.Model;

public class User {
    private String id;
    private String username;
    private String ImageURL;
    private String review;


    public User(String id, String username, String ImageURL, String review){
        this.id = id;
        this.username = username;
        this.ImageURL = ImageURL;
        this.review = review;

    }
    public User(){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
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

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String status) {
        this.review = review;
    }


}


