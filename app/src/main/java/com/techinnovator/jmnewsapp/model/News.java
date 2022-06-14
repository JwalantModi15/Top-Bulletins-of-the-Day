package com.techinnovator.jmnewsapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigInteger;

@Entity(tableName = "News_Info")
public class News {

    @PrimaryKey(autoGenerate = false)
    private int id;

    private String img;
    private String title;
    private String author;
    private String url;

    public News(String img, String title, String author, String url) {
        this.img = img;
        this.title = title;
        this.author = author;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }
}
