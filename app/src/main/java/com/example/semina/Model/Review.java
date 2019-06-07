package com.example.semina.Model;

public class Review {
    private String  review_content;
    private String image_review;
    private String nameuser;
    private String imageuser;

    public Review(String review_content, String image_review) {
        this.review_content = review_content;
        this.image_review = image_review;
    }

    public Review(String nameuser, String imageuser, String review_content, String image_review) {

        this.review_content = review_content;
        this.image_review = image_review;
        this.nameuser=nameuser;
        this.imageuser=imageuser;

    }

    public String getNameuser() {
        return nameuser;
    }

    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    public String getImageuser() {
        return imageuser;
    }

    public void setImageuser(String imageuser) {
        this.imageuser = imageuser;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    public String getImage_review() {
        return image_review;
    }

    public void setImage_review(String image_review) {
        this.image_review = image_review;
    }
}
