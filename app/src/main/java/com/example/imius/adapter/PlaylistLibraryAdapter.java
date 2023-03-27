package com.example.imius.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.model.PlaylistLibrary;
import com.example.imius.viewmodel.PlaylistLibraryViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistLibraryAdapter extends RecyclerView.Adapter<PlaylistLibraryAdapter.ViewHolder> {

    private Context context;
    private List<PlaylistLibrary> playlistLibraryList;

    public PlaylistLibraryAdapter (Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_library_playlist, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlaylistLibrary playlistLibrary = playlistLibraryList.get(position);

        if (playlistLibraryList == null){
            return;
        }
        holder.tvNamePlaylistLibrary.setText(holder.tvNamePlaylistLibrary.getText().toString());
        Picasso.get().load(playlistLibrary.getImageLibraryPlaylist()).into(holder.imgPlaylistLibrary);
    }

    @Override
    public int getItemCount() {
        if (playlistLibraryList != null){
            return playlistLibraryList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNamePlaylistLibrary;
        private ImageView imgPlaylistLibrary;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamePlaylistLibrary = itemView.findViewById(R.id.item_library_playlist_tv_name);
            imgPlaylistLibrary = itemView.findViewById(R.id.item_library_playlist_img);
        }
    }
}
