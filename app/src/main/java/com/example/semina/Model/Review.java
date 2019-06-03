package com.example.semina.Model;

public class Review {
    private String id;
    private String name_avatar;
    private String  name_review;
    private String  review;
    private String Image_Url;

    public Review(String id, String name_avatar, String name_review, String review, String image_Url) {
        this.id = id;
        this.name_avatar = name_avatar;
        this.name_review = name_review;
        this.review = review;
        Image_Url = image_Url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_avatar() {
        return name_avatar;
    }

    public void setName_avatar(String name_avatar) {
        this.name_avatar = name_avatar;
    }

    public String getName_review() {
        return name_review;
    }

    public void setName_review(String name_review) {
        this.name_review = name_review;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getImage_Url() {
        return Image_Url;
    }

    public void setImage_Url(String image_Url) {
        Image_Url = image_Url;
    }
}
