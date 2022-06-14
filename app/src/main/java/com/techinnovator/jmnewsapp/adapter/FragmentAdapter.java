package com.techinnovator.jmnewsapp.adapter;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.techinnovator.jmnewsapp.fragment.AllNewsFragment;
import com.techinnovator.jmnewsapp.fragment.BusinessFragment;
import com.techinnovator.jmnewsapp.fragment.EntertainmentFragment;
import com.techinnovator.jmnewsapp.fragment.HealthFragment;
import com.techinnovator.jmnewsapp.fragment.ScienceFragment;
import com.techinnovator.jmnewsapp.fragment.SportsFragment;
import com.techinnovator.jmnewsapp.fragment.TechnologyNewsFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    private Application application;
    private Context context;
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Application application) {
        super(fragmentManager, lifecycle);
        this.application = application;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment=null;

        switch (position){
            case 0:
                fragment = new AllNewsFragment(application);
                break;
            case 1:
                fragment = new TechnologyNewsFragment();
                break;
            case 2:
                fragment = new EntertainmentFragment();
                break;
            case 3:
                fragment = new BusinessFragment();
                break;
            case 4:
                fragment = new HealthFragment();
                break;
            case 5:
                fragment = new ScienceFragment();
                break;
            case 6:
                fragment = new SportsFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
