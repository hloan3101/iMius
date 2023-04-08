package com.example.imius.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.activity.PlaylistActivity;
import com.example.imius.model.Singer;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHolder>{
    private Context context;

    List<Singer> singerList;

    public SingerAdapter(Context context) {
        this.context = context;
    }

    public SingerAdapter(Context context, ArrayList<Singer> singerArrayList){
        this.context = context;
        this.singerList = singerArrayList;
    }

    public List<Singer> getSingerList(){
        return singerList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_singer, parent, false);

        return new ViewHolder(view);
    }

    public void setSingerList(List<Singer> singerList) {
        this.singerList = singerList;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Singer singer = singerList.get(position);
        holder.tvNameSinger.setText(singer.getNameSinger());
        Picasso.get().load(singer.getImageSinger()).into(holder.imgSinger);

    }

    @Override
    public int getItemCount() {
        return singerList != null ? singerList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        de.hdodenhof.circleimageview.CircleImageView imgSinger;
        TextView tvNameSinger;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imgSinger = itemView.findViewById(R.id.item_library_singer_avatar_of_singer);
            tvNameSinger = itemView.findViewById(R.id.item_singer_tv_singer_name);
        }
    }
}
