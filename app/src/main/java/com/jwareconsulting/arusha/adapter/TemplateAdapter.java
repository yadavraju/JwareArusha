package com.jwareconsulting.arusha.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jwareconsulting.arusha.R;

import java.util.ArrayList;

/**
 * Created by anjulkhanal on 8/4/16.
 */
public class TemplateAdapter extends RecyclerView.Adapter<TemplateAdapter.ViewHolder> {
    private Context context;
    private String TAG = TemplateAdapter.class.getSimpleName();
    private ArrayList<DataModel> dataList;
    private OnItemClickListner onItemClickListner;

    public TemplateAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_adapter_exchange_rate, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {

        dataList.get(i);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvCurreny, tvBuy, tvSell;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCurreny = (TextView) itemView.findViewById(R.id.tv_currency);
            tvBuy = (TextView) itemView.findViewById(R.id.tv_buy);
            tvSell = (TextView) itemView.findViewById(R.id.tv_sell);
        }


        @Override
        public void onClick(View v) {
            if (onItemClickListner != null) {
                onItemClickListner.onClick(getAdapterPosition());
            }
        }
    }

    public void populateView(ArrayList<DataModel> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface OnItemClickListner {
        void onClick(int position);
    }

    public class DataModel {

    }
}
