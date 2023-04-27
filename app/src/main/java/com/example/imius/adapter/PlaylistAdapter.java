package com.example.imius.adapter;

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
import com.example.imius.activity.PlayMusicActivity;

import com.example.imius.model.Song;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    private Context context;
    private List<Song> songList;

    public PlaylistAdapter(Context context) {
        this.context = context;
    }

    public PlaylistAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_playlist, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.tvNameSong.setText(song.getNameSong());
        holder.tvNameSinger.setText(song.getNameSinger());
        Picasso.get().load(song.getImageSong()).into(holder.imgSong);

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNameSong, tvNameSinger;
        private ImageView imgSong, imgTim;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameSong = itemView.findViewById(R.id.item_playlist_tv_name_of_song);
            tvNameSinger = itemView.findViewById(R.id.item_playlist_tv_name_of_singer);
            imgSong = itemView.findViewById(R.id.item_playlist_iv_image_of_song);
            imgTim = itemView.findViewById(R.id.item_playlist_iv_love);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("song", songList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
