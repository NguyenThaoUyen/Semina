package com.example.semina.Model;

public class Data {

    private String image;
    private String  title;
    private String  content;

    public Data() {
    }

    public Data (String title, String image, String content) {
        this.title = title;
        this.image = image;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}



