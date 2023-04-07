package com.example.imius.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.activity.PlaylistActivity;
import com.example.imius.model.Singer;

import com.example.imius.databinding.FragmentSingerBinding;
import com.example.imius.model.Trending;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHolder>{
    Context context;
    private FragmentSingerBinding binding;
    ArrayList<Singer> singerArrayList;
    View view;

    public SingerAdapter(Context context) {
        this.context = context;
    }

    public SingerAdapter(Context context, ArrayList<Singer> singerArrayList){
        this.context = context;
        this.singerArrayList = singerArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_singer, parent, false);

        return new ViewHolder(view);
    }

    public void setSingerArrayList(ArrayList<Singer> singerArrayList) {
        this.singerArrayList = singerArrayList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Singer singer = singerArrayList.get(position);
        holder.tvNameSinger.setText(singer.getNameSinger());
        Picasso.get().load(singer.getAvatarSinger()).into(holder.imgSinger);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlaylistActivity.class);
                intent.putExtra("NameSinger", singerArrayList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return singerArrayList != null ? singerArrayList.size() : 0;
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
