package com.example.imius.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.activity.PlaylistActivity;
import com.example.imius.data.DataLocalManager;
import com.example.imius.model.TopicModel;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder>{
    private Context context;

    private View view;

    private List<TopicModel> topicModelList;
    public TopicAdapter(Context context){
        this.context = context;
    }
    public  TopicAdapter(Context context, ArrayList<TopicModel> arrayTopic){
        this.context = context;
        this.topicModelList = arrayTopic;
    }

    public List<TopicModel> getTopicModelList() {
        return topicModelList;
    }
    private void callPlaylistActivity(TopicModel topic){
        DataLocalManager.setIdTopic(topic.getIdTopic());
        Intent intent = new Intent(context, PlaylistActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("nameTopic", topic.getNameTopic());
        bundle.putString("imageTopic", topic.getImageTopic());
        bundle.putBoolean("checkTrending", true);
        intent.putExtras(bundle);

        context.startActivity(intent);
    }
    public void setTopicModelList(List<TopicModel> topicModelList) {
        this.topicModelList = topicModelList;
        notifyDataSetChanged();
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

        if(topicModelList == null){
            return;
        }

        Picasso.get().load(topicModel.getImageTopic()).into(holder.imgTopic);
        holder.tvNameTopic.setText(topicModel.getNameTopic());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPlaylistActivity(topicModel);
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
            imgTopic = itemView.findViewById(R.id.item_newrelease_iv_newrelease_image);
            tvNameTopic = itemView.findViewById(R.id.item_newrelease_tv_newrelease_name);
        }

    }

}
