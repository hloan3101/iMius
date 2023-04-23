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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.activity.PlayMusicActivity;
import com.example.imius.constants.Constants;
import com.example.imius.data.DataLocalManager;
import com.example.imius.fragment.SearchFragment;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.Song;
import com.example.imius.repository.LibraryRepository;
import com.example.imius.repository.MusicRepository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Song> listSongs;
//    private ArrayList<Song> keyOfSong;

    private int checkLike = 0;

    private SearchFragment searchFragment = new SearchFragment();

    public SongAdapter(Context context) {
        this.context = context;

    }

    public SongAdapter(Context context, ArrayList<Song> listSongs) {
        this.context = context;
        this.listSongs = listSongs;
        notifyDataSetChanged();
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

        if (DataLocalManager.getCheckSearch()) {
            holder.imgLoveButton.setImageResource(R.drawable.ic_add_circle);
            holder.imgLoveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   checkSongLibraryPlaylist(song);
                }
            });
        } else {
            holder.imgLoveButton.setImageResource(R.drawable.ic_love);
            holder.imgLoveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkLike++;
                    if (checkLike % 2 != 0) {
                        updateNumberOfLike(song);
                    } else {
                        deleteLikeSong(song);
                    }
                }
            });
        }

    }

    public void updateNumberOfLike(Song song) {
        MusicRepository repository = new MusicRepository();

        repository.addNumberOfLike(song.getIdSong(), 1);
        repository.insertLoveSong(DataLocalManager.getUsernameData(), song);
    }

    public void deleteLikeSong(Song song) {
        MusicRepository repository = new MusicRepository();

        repository.subNumberOfLike(song.getIdSong());
        repository.deleteLikeSong(DataLocalManager.getUsernameData(), song.getIdSong());
    }

    public void checkSongLibraryPlaylist (Song song){
        LibraryRepository repository = new LibraryRepository();

        repository.checkSongLibraryPlaylist(DataLocalManager.getIdLibraryPlaylist(), song.getIdSong())
                .enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body() != null){
                    if (!response.body().getIsSuccess().equals(Constants.successfully)){
                  //      Toast.makeText(context, String.valueOf(response.body().equals(Constants.successfully)))
                       addSongLibraryPlaylist(song);
                    }
                    else {
                        Toast.makeText(context, R.string.song_exist, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addSongLibraryPlaylist(Song song) {
        LibraryRepository repository = new LibraryRepository();

        repository.insertSongLibraryPlaylist(DataLocalManager.getIdLibraryPlaylist(), song.getIdSong(),
                song.getNameSong(), song.getNameSinger(), song.getImgSong(),
                song.getLinkSong()).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body() != null) {
                    Toast.makeText(context, R.string.song_insert_success, Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(context, R.string.song_insert_failed, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listSongs != null) {
            return listSongs.size();
        }
        return 0;
    }

    public ArrayList<Song> getListSongs() {
        return listSongs;
    }

    public void setListSongs(ArrayList<Song> listSongs) {
        this.listSongs = listSongs;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, PlayMusicActivity.class);
//                    intent.putExtra("song", listSongs.get(getPosition()));
//                    context.startActivity(intent);
//                }
//            });
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