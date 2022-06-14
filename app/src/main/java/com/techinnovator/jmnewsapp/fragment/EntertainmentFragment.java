package com.techinnovator.jmnewsapp.fragment;

import android.app.Application;
import android.net.Uri;
import android.os.Bundle;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.techinnovator.jmnewsapp.NewsViewModel;
import com.techinnovator.jmnewsapp.R;
import com.techinnovator.jmnewsapp.RetrofitApi;
import com.techinnovator.jmnewsapp.adapter.EntertainmentNewsAdapter;
import com.techinnovator.jmnewsapp.adapter.NewsInfoAdapter;
import com.techinnovator.jmnewsapp.model.AllNews;
import com.techinnovator.jmnewsapp.model.Article;
import com.techinnovator.jmnewsapp.model.News;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EntertainmentFragment extends Fragment {

    RecyclerView recyclerView;
    List<Article> newsList = new ArrayList<>();
    EntertainmentNewsAdapter entertainmentNewsAdapter;
    ProgressBar progressBar;
    NewsViewModel newsViewModel;
    Application application;
    public EntertainmentFragment() {
        // Required empty public constructor
    }

    public EntertainmentFragment(Application application) {
        this.application = application;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_news, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar = view.findViewById(R.id.progressBar);
        newsViewModel = new NewsViewModel(application);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);

        Call<AllNews> call = retrofitApi.getAllData("entertainment");

        call.enqueue(new Callback<AllNews>() {
            @Override
            public void onResponse(Call<AllNews> call, Response<AllNews> response) {
                if(response.isSuccessful()){
                    newsList = response.body().getArticles();
                    progressBar.setVisibility(View.GONE);
                    entertainmentNewsAdapter.updateList(newsList, new EntertainmentNewsAdapter.OnClickListener() {
                        @Override
                        public void itemClick(Article article) {
                            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                            CustomTabsIntent intent = builder.build();
                            intent.launchUrl(getContext(), Uri.parse(article.getUrl()));
                        }

                        @Override
                        public void favClick(News news, ImageButton btnFav) {
                            try {
                                News N = newsViewModel.getNews(news.getId());
                                if(N==null){
                                    newsViewModel.insertNews(news);
                                    btnFav.setImageResource(R.drawable.ic_fav_news);
                                }
                                else{
                                    newsViewModel.deleteNews(news);
                                    btnFav.setImageResource(R.drawable.ic_select_fav);
                                }

                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<AllNews> call, Throwable t) {
                System.out.println("error");
                System.out.println(t.toString());
            }
        });

        entertainmentNewsAdapter = new EntertainmentNewsAdapter();
        recyclerView.setAdapter(entertainmentNewsAdapter);

        return view;
    }
}