package com.example.imius.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.model.Singer;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHolder>{
    private Context context;

    private List<Singer> singerList;

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
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Singer singer = singerList.get(position);

        if (singerList == null){
            return;
        }
        holder.tvNameSinger.setText(singer.getNameSinger());
        Picasso.get().load(singer.getImageSinger()).into(holder.imgSinger);
    }

    @Override
    public int getItemCount() {
        return singerList != null ? singerList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView imgSinger;
        private TextView tvNameSinger;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imgSinger = itemView.findViewById(R.id.item_singer_civ_singer_image);
            tvNameSinger = itemView.findViewById(R.id.item_singer_tv_singer_name);
        }
    }
}
