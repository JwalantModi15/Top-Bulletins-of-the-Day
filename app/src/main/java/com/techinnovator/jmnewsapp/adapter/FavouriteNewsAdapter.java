package com.techinnovator.jmnewsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techinnovator.jmnewsapp.R;
import com.techinnovator.jmnewsapp.model.News;

import java.util.ArrayList;
import java.util.List;

public class FavouriteNewsAdapter extends RecyclerView.Adapter<FavouriteNewsAdapter.FavNewsHolder>{

    private List<News> newsList = new ArrayList<>();
    private ClickListener clickListener;

    public FavouriteNewsAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setNewsList(List<News> list){
        newsList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FavNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_header, parent, false);
        return new FavNewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavNewsHolder holder, int position) {
        Picasso.get().load(newsList.get(position).getImg()).into(holder.imgFav);
        holder.txtTitleFav.setText(newsList.get(position).getTitle());
        holder.txtAuthorFav.setText(newsList.get(position).getAuthor());
        holder.cardViewFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.OnCardClick(newsList.get(position).getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class FavNewsHolder extends RecyclerView.ViewHolder{

        ImageView imgFav;
        TextView txtTitleFav;
        TextView txtAuthorFav;
        CardView cardViewFav;

        public FavNewsHolder(@NonNull View itemView) {
            super(itemView);

            imgFav = itemView.findViewById(R.id.imgFav);
            txtTitleFav = itemView.findViewById(R.id.txtTitleFav);
            txtAuthorFav = itemView.findViewById(R.id.txtAuthorFav);
            cardViewFav = itemView.findViewById(R.id.cardViewFav);
        }
    }
    public interface ClickListener{
        void OnCardClick(String s);
    }
}
