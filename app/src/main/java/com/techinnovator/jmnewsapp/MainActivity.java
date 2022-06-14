package com.techinnovator.jmnewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.techinnovator.jmnewsapp.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> arr = new ArrayList<>();

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Top Bulletins");
        tabLayout = findViewById(R.id.tabView);
        viewPager2 = findViewById(R.id.viewPager2);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle(), getApplication());

        viewPager2.setAdapter(fragmentAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, true, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Headlines");
                        break;
                    case 1:
                        tab.setText("Technology");
                        break;
                    case 2:
                        tab.setText("Entertainment");
                        break;
                    case 3:
                        tab.setText("Business");
                        break;
                    case 4:
                        tab.setText("Health");
                        break;
                    case 5:
                        tab.setText("Science");
                        break;
                    case 6:
                        tab.setText("Sports");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(R.id.fav==item.getItemId()){
            startActivity(new Intent(MainActivity.this, FavouriteActivity.class));
        }
        return true;
    }
}