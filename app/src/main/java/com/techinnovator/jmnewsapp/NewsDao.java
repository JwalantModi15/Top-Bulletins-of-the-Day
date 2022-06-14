package com.techinnovator.jmnewsapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.techinnovator.jmnewsapp.model.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    void insert(News news);

    @Delete
    void delete(News news);

    @Query("Select * From News_Info Order by id Asc")
    LiveData<List<News>> getFavNews();

    @Query("Select * from News_Info where id = :newsId")
    News getNewsById(int newsId);
}
