package com.techinnovator.jmnewsapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.techinnovator.jmnewsapp.model.News;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewsViewModel extends AndroidViewModel {

    private LiveData<List<News>> list;
    private NewsRepository newsRepository;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
        list = newsRepository.getFavNewsRepo();
    }

    public void insertNews(News news){
        newsRepository.insertRepo(news);
    }
    public News getNews(int n) throws ExecutionException, InterruptedException {
        News news = newsRepository.getNewsRepo(n);
        return news;
    }

    public void deleteNews(News news){
        newsRepository.deleteRepo(news);
    }

    public LiveData<List<News>> getAllFavNews(){
        return list;
    }
}
