package com.example.imius.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.model.ChartsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChartsAdapter extends RecyclerView.Adapter<ChartsAdapter.ViewHolder> {
    private Context context;
    private List<ChartsModel> chartsModelList;
    public ChartsAdapter(Context context){
        this.context = context;
    }

    public ChartsAdapter(Context context, List<ChartsModel> chartsModelList) {
        this.context = context;
        this.chartsModelList = chartsModelList;
    }
    public List<ChartsModel> getChartsModelList(){
        return chartsModelList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_charts, parent, false);

        return new ViewHolder(view);
    }
    public void setChartsModelList(List<ChartsModel> chartsModelList){
        this.chartsModelList = chartsModelList;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChartsModel chartsModel = chartsModelList.get(position);

        if(chartsModelList == null){
            return;
        }
        holder.tvNameChart.setText(chartsModel.getNameChart());
        Picasso.get().load(chartsModel.getImageChart()).into(holder.imgChart);
    }

    @Override
    public int getItemCount() {
        return chartsModelList != null ? chartsModelList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgChart;
        private TextView tvNameChart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgChart = itemView.findViewById(R.id.item_charts_iv_charts_image);
            tvNameChart = itemView.findViewById(R.id.item_charts_tv_charts_name);
        }
    }
}
