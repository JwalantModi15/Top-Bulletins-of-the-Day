package com.techinnovator.jmnewsapp;

import com.techinnovator.jmnewsapp.model.AllNews;
import com.techinnovator.jmnewsapp.model.Article;
import com.techinnovator.jmnewsapp.model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("top-headlines?country=in&apiKey=57dd6bde1aaa40e5a55fcd7e4ce35f8b")
    Call<AllNews> getAllData(@Query("category") String name);
}
