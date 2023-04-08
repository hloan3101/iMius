package com.example.imius.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.activity.PlaylistActivity;
import com.example.imius.model.Trending;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.ViewHolder> {
    private Context context;

    List<Trending> trendingList;


    public TrendingAdapter(Context context, ArrayList<Trending> arrayTrending){
        this.context = context;
        this.trendingList = arrayTrending;
    }

    public List<Trending> getTrendingList() {
        return trendingList;
    }

    public void setTrendingList(List<Trending> trendingList) {
        this.trendingList = trendingList;
    }


    public TrendingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_trending, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Trending trending = trendingList.get(position);
        holder.tvNameTrending.setText(trending.getNameTrending());
        Picasso.get().load(trending.getImageTrending()).into(holder.imgTrending);

    }

    @Override
    public int getItemCount() {
        return trendingList != null ? trendingList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgTrending;
        TextView tvNameTrending;
        public ViewHolder(View itemView){
            super(itemView);
            imgTrending = itemView.findViewById(R.id.item_trending_iv_trending_image);
            tvNameTrending = imgTrending.findViewById(R.id.item_trending_tv_trending_name);

        }

    }
}
