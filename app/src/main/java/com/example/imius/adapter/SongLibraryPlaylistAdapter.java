package com.example.imius.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.model.SongLibraryPlaylist;

import java.util.List;

public class SongLibraryPlaylistAdapter extends RecyclerView.Adapter<SongLibraryPlaylistAdapter.ViewHolder>{

    private Context context;
    private List<SongLibraryPlaylist> songLibraryPlaylistList;


    public SongLibraryPlaylistAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_playlist, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout layoutItem;
        private TextView tvNameSong;
        private TextView tvNameSinger;
        private ImageView imgSong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.item_playlist);
            tvNameSong = itemView.findViewById(R.id.item_playlist_tv_name_of_song);
            tvNameSinger = itemView.findViewById(R.id.item_playlist_tv_name_of_singer);
            imgSong = itemView.findViewById(R.id.item_playlist_iv_image_of_song);
        }
    }
}
