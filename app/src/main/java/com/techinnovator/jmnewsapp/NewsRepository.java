package com.techinnovator.jmnewsapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.techinnovator.jmnewsapp.database.NewsDatabase;
import com.techinnovator.jmnewsapp.model.News;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewsRepository {

    private LiveData<List<News>> list;
    private NewsDao newsDao;

    public NewsRepository(Context context) {
        newsDao = NewsDatabase.getNewsDatabase(context).getNewsDao();
        list = newsDao.getFavNews();
    }

    public void insertRepo(News news){
        new insertAsyncTask().execute(news);
    }

    public News getNewsRepo(int n) throws ExecutionException, InterruptedException {
        News news = new GetFavNewsAsyncTask(n).execute().get();
        return news;
    }

    public void deleteRepo(News news){
        new deleteAsyncTask().execute(news);
    }

    public LiveData<List<News>> getFavNewsRepo(){
        return list;
    }

    public class insertAsyncTask extends AsyncTask<News, Void, Void>{

        @Override
        protected Void doInBackground(News... news) {
            newsDao.insert(news[0]);
            return null;
        }
    }

    public class deleteAsyncTask extends AsyncTask<News, Void, Void>{

        @Override
        protected Void doInBackground(News... news) {
            newsDao.delete(news[0]);
            return null;
        }
    }

    public class GetFavNewsAsyncTask extends AsyncTask<Void, Void, News>{

        int n;

        public GetFavNewsAsyncTask(int n) {
            this.n = n;
        }

        @Override
        protected News doInBackground(Void... voids) {
            News news = newsDao.getNewsById(n);
            return news;
        }
    }
}
