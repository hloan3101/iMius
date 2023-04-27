package com.example.imius.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.activity.PlayMusicActivity;
import com.example.imius.model.FavoriteSong;
import com.example.imius.model.Song;
import com.example.imius.model.SongLibraryPlaylist;

import java.util.ArrayList;

public class PlaylistPlayMusicAdapter extends RecyclerView.Adapter<PlaylistPlayMusicAdapter.ViewHolder> {

    Context context;

    ArrayList<Song> songArrayList;

    public PlaylistPlayMusicAdapter(Context context, ArrayList<Song> songArrayList) {

        this.context = context;
        this.songArrayList = songArrayList;

    }

    @NonNull
    @Override
    public PlaylistPlayMusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_play_music, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistPlayMusicAdapter.ViewHolder holder, int position) {
        Song song = songArrayList.get(position);

        holder.tvNumber.setText(position + 1 + "");
        holder.tvNameSong.setText(song.getNameSong());
        holder.tvNameSinger.setText(song.getNameSinger());
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNumber;
        private TextView tvNameSong;
        private TextView tvNameSinger;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNumber = itemView.findViewById(R.id.item_play_music_number);
            tvNameSong = itemView.findViewById(R.id.item_play_music_name_of_song);
            tvNameSinger = itemView.findViewById(R.id.item_play_music_name_of_singer);

        }
    }
}
