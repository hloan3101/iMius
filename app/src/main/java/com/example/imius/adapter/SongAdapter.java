package com.example.imius.adapter;

import android.app.appsearch.AppSearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.activity.PlayMusicActivity;
import com.example.imius.data.DataLocalManager;
import com.example.imius.fragment.SearchFragment;
import com.example.imius.model.Song;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Song> listSongs;
//    private ArrayList<Song> keyOfSong;

    private SearchFragment searchFragment = new SearchFragment();

    public SongAdapter(Context context) {
        this.context = context;

    }

    public SongAdapter(Context context, ArrayList<Song> listSongs) {
        this.context = context;
        this.listSongs = listSongs;
//        this.keyOfSong = listSongs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = listSongs.get(position);

        holder.tvNameOfSong.setText(song.getNameSong());
        holder.tvSinger.setText(song.getNameSinger());
        Picasso.get().load(song.getImgSong()).into(holder.imgImageOfSong);

        if (DataLocalManager.getCheckSearch()){
            holder.imgLoveButton.setImageResource(R.drawable.ic_add_circle);
        } else {
            holder.imgLoveButton.setImageResource(R.drawable.ic_love);
        }

    }

    @Override
    public int getItemCount() {
        if (listSongs != null){
            return listSongs.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgImageOfSong;
        private TextView tvNameOfSong;
        private TextView tvSinger;
        public ImageView imgLoveButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgImageOfSong = itemView.findViewById(R.id.item_search_iv_image_of_song);
            imgLoveButton = itemView.findViewById(R.id.item_search_iv_love);
            tvNameOfSong = itemView.findViewById(R.id.item_search_tv_name_of_song);
            tvSinger = itemView.findViewById(R.id.item_search_tv_name_of_singer);

//            imgLoveButton.setVisibility(View.GONE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("song", listSongs.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//               String search = charSequence.toString();
//               if (search.isEmpty()){
//                   listSongs = keyOfSong;
//               } else {
//                   ArrayList<Song> newListSong = new ArrayList<>();
//                   for (Song song: keyOfSong) {
//                        if (song.getNameSong().toLowerCase().contains(search.toLowerCase())){
//                            newListSong.add(song);
//                        }
//                   }
//
//                   listSongs = newListSong;
//               }
//
//               FilterResults results = new FilterResults();
//               results.values = listSongs;
//
//               return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                listSongs = (ArrayList<Song>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }
}
