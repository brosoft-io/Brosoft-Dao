package io.brosoft.dao.examples.mongodb;

import io.brosoft.dao.annotation.MongoField;

public class MongoBean {

    @MongoField(key = "name")
    private String title;
    @MongoField
    private String genre;
    @MongoField(key = "release-year")
    private int year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
