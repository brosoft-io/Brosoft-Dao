package io.brosoft.dao.examples.mongodb;

import io.brosoft.dao.annotation.DocumentField;

public class MongoBean {

    @DocumentField(key = "name")
    private String title;
    @DocumentField
    private String genre;
    @DocumentField(key = "release-year")
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
