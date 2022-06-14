package com.techinnovator.jmnewsapp.adapter;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techinnovator.jmnewsapp.NewsViewModel;
import com.techinnovator.jmnewsapp.R;
import com.techinnovator.jmnewsapp.model.AllNews;
import com.techinnovator.jmnewsapp.model.Article;
import com.techinnovator.jmnewsapp.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsInfoAdapter extends RecyclerView.Adapter<NewsInfoAdapter.NewsHolder>{

    List<Article> list = new ArrayList<>();
    OnClickListener clickListener;
    NewsViewModel newsViewModel;
    Application application;

    public void updateList(List<Article> list, OnClickListener clickListener){
        this.list = list;
        this.clickListener = clickListener;
        notifyDataSetChanged();
    }

    public NewsInfoAdapter(Application application){
        this.application = application;
    }
    public NewsInfoAdapter(){
    }
    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_header, parent, false);
        NewsHolder holder = new NewsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.txt.setText(list.get(position).getTitle());
        holder.txtAuthor.setText(list.get(position).getAuthor());
        Picasso.get().load(list.get(position).getUrlToImage()).into(holder.img);

//        newsViewModel = new NewsViewModel(application);
//        News N = new News(list.get(position).getUrlToImage(), list.get(position).getTitle(), list.get(position).getAuthor(), list.get(position).getUrl());
//        N.setId(position);
//
//        try {
//            if(newsViewModel.getNews(N.getId())==null){
//                holder.btnFav.setImageResource(R.drawable.ic_select_fav);
//            }
//            else{
//                holder.btnFav.setImageResource(R.drawable.ic_fav_news);
//            }
//
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemClick(list.get(position));
            }
        });
//        holder.btnFav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                News news = new News(list.get(position).getUrlToImage(), list.get(position).getTitle(), list.get(position).getAuthor(), list.get(position).getUrl());
//                news.setId(position);
//                clickListener.favClick(news, holder.btnFav);
//            }
//        });
        holder.btnFav.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder{

        TextView txt;
        TextView txtAuthor;
        ImageView img;
        CardView cardView;
        ImageButton btnFav;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.textView);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            img = itemView.findViewById(R.id.img);
            cardView = itemView.findViewById(R.id.cardView);
            btnFav = itemView.findViewById(R.id.btnFav);
        }
    }
    public interface OnClickListener{
        void itemClick(Article article);
        void favClick(News news, ImageButton btnFav);
    }
}
