package com.jwareconsulting.arusha.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.model.DirectoryModel;

import java.util.ArrayList;

/**
 * Created by anjulkhanal on 8/4/16.
 */
public class MapRecycleViewAdapter extends RecyclerView.Adapter<MapRecycleViewAdapter.ViewHolder> {
    private Context context;
    private String TAG = MapRecycleViewAdapter.class.getSimpleName();
    private OnItemClickListner onItemClickListner;
    private ArrayList<DirectoryModel> dataList;

    public MapRecycleViewAdapter(Context context) {
        this.context = context;
        dataList = new ArrayList<DirectoryModel>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_adapter_map_recycleview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.tvCatagory.setText(dataList.get(i).getName());

        if (dataList.get(i).getIconResources() != 0) {
            holder.ivCatagory.setImageResource(dataList.get(i).getIconResources());
        } else {
            Picasso.with(context)
                    .load(dataList.get(i).getThumnailImage())
                    .into(holder.ivCatagory);
        }
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void populateView(ArrayList<DirectoryModel> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvCatagory;
        ImageView ivCatagory;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCatagory = (TextView) itemView.findViewById(R.id.tv_catagory);
            ivCatagory = (ImageView) itemView.findViewById(R.id.iv_catagory);
            tvCatagory.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (onItemClickListner != null) {
                onItemClickListner.onClick(dataList.get(getAdapterPosition()));
            }
        }
    }


    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface OnItemClickListner {
        void onClick(DirectoryModel model);
    }


}
