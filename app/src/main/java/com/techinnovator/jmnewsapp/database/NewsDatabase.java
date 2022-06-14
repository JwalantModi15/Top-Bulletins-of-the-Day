package com.techinnovator.jmnewsapp.database;

import android.content.Context;
import android.content.Entity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.techinnovator.jmnewsapp.NewsDao;
import com.techinnovator.jmnewsapp.model.News;

@Database(entities = News.class, version = 7)
public abstract class NewsDatabase extends RoomDatabase {

    private static NewsDatabase newsDatabase;
    public abstract NewsDao getNewsDao();

    public static synchronized NewsDatabase getNewsDatabase(Context context){
        if(newsDatabase==null){
            newsDatabase = Room.databaseBuilder(context, NewsDatabase.class, "news-fav-database").build();
        }
        return newsDatabase;
    }

}
