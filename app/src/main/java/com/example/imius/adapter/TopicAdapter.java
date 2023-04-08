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
import com.example.imius.databinding.FragmentTrendingBinding;
import com.example.imius.model.TopicModel;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder>{
    private Context context;

    List<TopicModel> topicModelList;
    View view;
    public  TopicAdapter(Context context, ArrayList<TopicModel> arrayTopic){
        this.context = context;
        this.topicModelList = arrayTopic;
    }

    public List<TopicModel> getTopicModelList() {
        return topicModelList;
    }

    public void setTopicModelList(List<TopicModel> topicModelList) {
        this.topicModelList = topicModelList;
    }
    public TopicAdapter(Context context){
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
    public void onBindViewHolder(@NonNull TopicAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        TopicModel topicModel = topicModelList.get(position);
        holder.tvNameTopic.setText(topicModel.getNameTopic());
        Picasso.get().load(topicModel.getImageTopic()).into(holder.imgTopic);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlaylistActivity.class);
                intent.putExtra("intentTopic", String.valueOf(topicModelList.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicModelList != null ? topicModelList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgTopic;
        TextView tvNameTopic;
        public ViewHolder(View itemView){
            super(itemView);
            imgTopic = itemView.findViewById(R.id.item_trending_iv_trending_image);
            tvNameTopic = imgTopic.findViewById(R.id.item_trending_tv_trending_name);

        }

    }

}
