package com.jwareconsulting.arusha.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.rest.response.model.SubCategoriesResponseModel;

import java.util.ArrayList;

/**
 * Created by anjulkhanal on 8/4/16.
 */
public class DirectoryDetailAdapter extends RecyclerView.Adapter<DirectoryDetailAdapter.ViewHolder> {
    private Context context;
    private String TAG = DirectoryDetailAdapter.class.getSimpleName();
    private ArrayList<SubCategoriesResponseModel.DirectorySubCAtegoriesModel> dataList;
    private OnItemClickListner onItemClickListner;
    private LandingActivityMvp.Presenter mainPresenter;

    public DirectoryDetailAdapter(Context context) {
        dataList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_adapter_directory, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.tvSearchItem.setText(dataList.get(i).getSubCatName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvSearchItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSearchItem = (TextView) itemView.findViewById(R.id.tv_search_item);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onItemClickListner.onClick(getAdapterPosition(), dataList.get(getAdapterPosition()).getSubCatId());
        }
    }

    public void populateView(ArrayList<SubCategoriesResponseModel.DirectorySubCAtegoriesModel> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface OnItemClickListner {
        void onClick(int position, String subCatId);
    }

}
