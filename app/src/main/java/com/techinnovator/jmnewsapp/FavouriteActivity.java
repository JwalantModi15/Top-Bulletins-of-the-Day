package com.techinnovator.jmnewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.techinnovator.jmnewsapp.adapter.FavouriteNewsAdapter;
import com.techinnovator.jmnewsapp.model.News;

import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FavouriteNewsAdapter favouriteNewsAdapter;
    NewsViewModel newsViewModel;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        setTitle("Favorites");
        recyclerView = findViewById(R.id.recyclerView3);
        textView = findViewById(R.id.txtEmpty);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(NewsViewModel.class);
        newsViewModel.getAllFavNews().observe(FavouriteActivity.this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {

                if(news.size()==0){
                    textView.setVisibility(View.VISIBLE);
                }
                else{
                    textView.setVisibility(View.GONE);
                }

                favouriteNewsAdapter.setNewsList(news);

            }
        });
        favouriteNewsAdapter = new FavouriteNewsAdapter(new FavouriteNewsAdapter.ClickListener() {
            @Override
            public void OnCardClick(String s) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent intent = builder.build();
                intent.launchUrl(FavouriteActivity.this, Uri.parse(s));
            }
        });
        recyclerView.setAdapter(favouriteNewsAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }
}