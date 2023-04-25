package com.example.imius.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.model.FavoriteSong;
import com.example.imius.model.LibraryPlaylist;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteSongAdapter extends RecyclerView.Adapter<FavoriteSongAdapter.ViewHolder>{

    private Context context;
    private List<FavoriteSong> favoriteSongs;

    public FavoriteSongAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_favorite, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoriteSong favoriteSong = favoriteSongs.get(position);

        if (favoriteSongs == null){
            return;
        }
        holder.tvNameFavoriteSong.setText(favoriteSong.getNameSong());
        holder.tvNameSinger.setText(favoriteSong.getNameSinger());
        Picasso.get().load(favoriteSong.getImgSong()).into(holder.imgFavoriteSong);
    }

    @Override
    public int getItemCount() {
        if (favoriteSongs != null){
            return favoriteSongs.size();
        }
        return 0;
    }

    public List<FavoriteSong> getFavoriteSongs() {
        return favoriteSongs;
    }

    public void setFavoriteSongs(List<FavoriteSong> favoriteSongs) {
        this.favoriteSongs = favoriteSongs;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNameFavoriteSong;
        private ImageView imgFavoriteSong;
        private TextView tvNameSinger;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameFavoriteSong = itemView.findViewById(R.id.item_favorite_tv_name_of_song);
            tvNameSinger = itemView.findViewById(R.id.item_favorite_tv_name_of_singer);
            imgFavoriteSong = itemView.findViewById(R.id.item_favorite_iv_image_of_song);
        }
    }

}
