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
import com.example.imius.model.NewReleaseModel;
import com.example.imius.viewmodel.NewReleaseViewModel;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NewReleaseAdapter extends RecyclerView.Adapter<NewReleaseAdapter.ViewHolder> {
    private Context context;
    private List<NewReleaseModel> newReleaseModelList;

    public NewReleaseAdapter(Context context){
        this.context = context;
    }

    public NewReleaseAdapter(Context context, ArrayList<NewReleaseModel> newReleaseModelList) {
        this.context = context;
        this.newReleaseModelList = newReleaseModelList;
    }

    public List<NewReleaseModel> getNewReleaseModelList() {
        return newReleaseModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_new_release, parent, false);

        return new ViewHolder(view);
    }

    public void setNewReleaseModelList(List<NewReleaseModel> newReleaseModelList) {
        this.newReleaseModelList = newReleaseModelList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull NewReleaseAdapter.ViewHolder holder, int position) {
        NewReleaseModel newReleaseModel = newReleaseModelList.get(position);

        if (newReleaseModelList == null){
            return;
        }
        holder.tvNameNewRelease.setText(newReleaseModel.getNameNewRelease());
        Picasso.get().load(newReleaseModel.getImageNewRelease()).into(holder.imgNewRelease);
    }

    @Override
    public int getItemCount() {
        return newReleaseModelList != null ? newReleaseModelList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgNewRelease;
        private TextView tvNameNewRelease;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgNewRelease = itemView.findViewById(R.id.item_newrelease_iv_newrelease_image);
            tvNameNewRelease = itemView.findViewById(R.id.item_newrelease_tv_newrelease_name);
        }
    }
}
