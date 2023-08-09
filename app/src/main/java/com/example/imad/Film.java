package com.example.imad;

public class Film {

    private String title;
    private String year;
    private String description;
    private String image;
    private String released;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getYear() {
        return year;
    }

    public Film(String title, String year, String description, String image, String released) {
        this.title = title;
        this.year = year;
        this.description = description;
        this.image = image;
        this.released = released;
    }
}
