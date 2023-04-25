package com.example.imius.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.activity.PlayMusicActivity;
import com.example.imius.constants.Constants;
import com.example.imius.data.DataLocalManager;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.Song;
import com.example.imius.model.SongLibraryPlaylist;
import com.example.imius.repository.MusicRepository;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        SongLibraryPlaylist songLibraryPlaylist = songLibraryPlaylistList.get(position);

        if (songLibraryPlaylistList == null){
            return;
        }

        holder.tvNameSong.setText(songLibraryPlaylist.getNameSong());
        holder.tvNameSinger.setText(songLibraryPlaylist.getNameSinger());
        Picasso.get().load(songLibraryPlaylist.getImageSong()).into(holder.imgSong);
        checkLikeSong(holder, songLibraryPlaylist);

        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnLike(holder, songLibraryPlaylist);
            }
        });
    }


    public void checkLikeSong (ViewHolder holder, SongLibraryPlaylist songLibraryPlaylist){
        MusicRepository repository = new MusicRepository();

        repository.checkLikeSong(DataLocalManager.getUsernameData(), songLibraryPlaylist.getIdSong())
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.body() != null){
                        //    Toast.makeText(context, response.body().getIsSuccess(), Toast.LENGTH_LONG).show();
                            if (response.body().getIsSuccess().equals(Constants.successfully)){
                                holder.btnLike.setImageResource(R.drawable.ic_loved);
                            }else {
                                holder.btnLike.setImageResource(R.drawable.ic_love);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void setBtnLike (ViewHolder holder, SongLibraryPlaylist songLibraryPlaylist){
        MusicRepository repository = new MusicRepository();

        repository.updateLikeOfNumber(songLibraryPlaylist.getIdSong())
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.body() != null){
                            //    Toast.makeText(context, response.body().getIsSuccess(), Toast.LENGTH_LONG).show();
                            if (response.body().getIsSuccess().equals(Constants.successfully)){
                                if (response.body().getMessage().equals(Constants.DELETE)){
                                    deleteLikeSong(songLibraryPlaylist.getIdSong());
                                    holder.btnLike.setImageResource(R.drawable.ic_love);
                                }else {
                                    updateNumberOfLike(songLibraryPlaylist);
                                    holder.btnLike.setImageResource(R.drawable.ic_loved);
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void updateNumberOfLike(SongLibraryPlaylist songLibraryPlaylist) {
        MusicRepository repository = new MusicRepository();
        Song song = new Song(songLibraryPlaylist.getIdSong(), songLibraryPlaylist.getNameSong(),
                songLibraryPlaylist.getImageSong(), songLibraryPlaylist.getNameSinger(),
                songLibraryPlaylist.getLinkSong());

   //     repository.insertLoveSong(DataLocalManager.getUsernameData(), song);
        repository.insertLoveSong(DataLocalManager.getUsernameData(), song).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
//                if (response.body().getIsSuccess().equals(Constants.successfully)){
//                    Toast.makeText(context, "insert success", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(context, "insert failed", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteLikeSong(int idSong) {
        MusicRepository repository = new MusicRepository();
 //       repository.deleteLikeSong(DataLocalManager.getUsernameData(), idSong);

        repository.deleteLikeSong(DataLocalManager.getUsernameData(), idSong).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
//                if (response.body().getIsSuccess().equals(Constants.successfully)){
//                    Toast.makeText(context, "delete success", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(context, "delete failed", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (songLibraryPlaylistList != null){
            return  songLibraryPlaylistList.size();
        }
        return 0;
    }

    public List<SongLibraryPlaylist> getSongLibraryPlaylistList() {
        return songLibraryPlaylistList;
    }

    public void setSongLibraryPlaylistList(List<SongLibraryPlaylist> songLibraryPlaylistList) {
        this.songLibraryPlaylistList = songLibraryPlaylistList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout layoutItem;
        private TextView tvNameSong;
        private TextView tvNameSinger;
        private ImageView imgSong;

        private ImageView btnLike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
      //      layoutItem = itemView.findViewById(R.id.item_playlist);
            tvNameSong = itemView.findViewById(R.id.item_playlist_tv_name_of_song);
            tvNameSinger = itemView.findViewById(R.id.item_playlist_tv_name_of_singer);
            imgSong = itemView.findViewById(R.id.item_playlist_iv_image_of_song);
            btnLike = itemView.findViewById(R.id.item_playlist_iv_love);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("library_song", songLibraryPlaylistList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
