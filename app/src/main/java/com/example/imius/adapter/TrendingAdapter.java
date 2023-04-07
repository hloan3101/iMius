package com.example.imius.adapter;

import android.annotation.SuppressLint;
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
import com.example.imius.activity.PlaylistActivity;
import com.example.imius.model.Trending;
import com.example.imius.databinding.FragmentTrendingBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.ViewHolder> {
    Context context;

    private FragmentTrendingBinding binding;

    List<Trending> arrayTrending;

    View view;

    public TrendingAdapter(Context context, ArrayList<Trending> arrayTrending){
        this.context = context;
        this.arrayTrending = arrayTrending;
    }

    public List<Trending> getArrayTrending() {
        return arrayTrending;
    }

    public void setArrayTrending(List<Trending> arrayTrending) {
        this.arrayTrending = arrayTrending;
    }

    public TrendingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_trending, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Trending trending = arrayTrending.get(position);
        holder.tvNameTrending.setText(trending.getNameTrending());
        Picasso.get().load(trending.getImgTrending()).into(holder.imgTrending);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlaylistActivity.class);
                intent.putExtra("intentTrending", arrayTrending.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayTrending != null ? arrayTrending.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgTrending;
        TextView tvNameTrending;
        public ViewHolder(View itemView){
            super(itemView);
            imgTrending = itemView.findViewById(R.id.item_trending_iv_trending_image);
            tvNameTrending = imgTrending.findViewById(R.id.item_trending_tv_trending_name);

        }

    }
}
