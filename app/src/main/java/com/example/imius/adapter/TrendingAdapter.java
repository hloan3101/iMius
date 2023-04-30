package com.example.imius.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.activity.PlaylistActivity;
import com.example.imius.data.DataLocalManager;
import com.example.imius.model.Trending;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

//import de.hdodenhof.circleimageview.CircleImageView;


public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.ViewHolder> {
    private Context context;

    private List<Trending> trendingList;

    public TrendingAdapter(Context context) {
        this.context = context;
    }
    public TrendingAdapter(Context context, ArrayList<Trending> trendingArrayList){
        this.context = context;
        this.trendingList = trendingArrayList;
    }

    public List<Trending> getTrendingList() {
        return trendingList;
    }

    private void callPlaylistActivity (Trending trending){
        DataLocalManager.setIdTrending(trending.getIdTrending());

        Intent intent = new Intent(context, PlaylistActivity.class);

        Bundle bundle =  new Bundle();

        bundle.putString("nameTrending", trending.getNameTrending());
        bundle.putString("imageTrending", trending.getImageTrending());
        bundle.putBoolean("checkTrending", true);
        intent.putExtras(bundle);

        context.startActivity(intent);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_trending, parent, false);
        return new ViewHolder(view);
    }
    public void setTrendingList(List<Trending> trendingList) {
        this.trendingList = trendingList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Trending trending = trendingList.get(position);

        if(trendingList == null){
            return;
        }
        Picasso.get().load(trending.getImageTrending()).into(holder.imgTrending);
        holder.tvNameTrending.setText(trending.getNameTrending());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPlaylistActivity(trending);
            }
        });

    }

    @Override
    public int getItemCount() {
        return trendingList != null ? trendingList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgTrending;
        private TextView tvNameTrending;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imgTrending = itemView.findViewById(R.id.item_newrelease_iv_newrelease_image);
            tvNameTrending = itemView.findViewById(R.id.item_newrelease_tv_newrelease_name);

        }

    }
}
