package com.example.moviedetail;

public class MovieData {
    private String title, img_url, rating, url;

    public MovieData(String title, String img_url, String rating, String url) {
        this.title = title;
        this.img_url = img_url;
        this.rating = rating;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getRating() {
        return rating;
    }

    public String getUrl() {
        return url;
    }
}
