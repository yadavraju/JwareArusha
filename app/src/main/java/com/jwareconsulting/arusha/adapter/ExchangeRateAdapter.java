package com.jwareconsulting.arusha.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.model.ExchangeRateModel;
import com.jwareconsulting.arusha.rest.response.model.ExchangeRateResponse;

import java.util.ArrayList;

/**
 * Created by anjulkhanal on 8/4/16.
 */
public class ExchangeRateAdapter extends RecyclerView.Adapter<ExchangeRateAdapter.ViewHolder> {
    private Context context;
    private String TAG = ExchangeRateAdapter.class.getSimpleName();
    private ArrayList<ExchangeRateModel> dataList;
    private ExchangeRateResponse.AuthorModel authorModel;
    private OnItemClick onItemClick;

    public ExchangeRateAdapter(Context context) {
        this.context = context;
        dataList = new ArrayList<>();
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
        holder.tvBuy.setText(dataList.get(i).getBuy());
        holder.tvCurreny.setText(dataList.get(i).getTitle());
        holder.tvSell.setText(dataList.get(i).getSell());
        if (dataList.size() > 0) {
            if (i == dataList.size() - 1) {
                holder.relativeLayout.setVisibility(View.VISIBLE);
                holder.tvAuthorTitle.setText(authorModel.getName());
                holder.tvTelephone.setText(authorModel.getUserPhone());
                holder.tvLocation.setText(authorModel.getUserAddress().getAddress());
            } else {
                holder.relativeLayout.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCurreny, tvBuy, tvSell, tvAuthorTitle, tvTelephone, tvLocation;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCurreny = (TextView) itemView.findViewById(R.id.tv_currency);
            tvBuy = (TextView) itemView.findViewById(R.id.tv_buy);
            tvSell = (TextView) itemView.findViewById(R.id.tv_sell);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.layout_udated_by);
            tvAuthorTitle = (TextView) itemView.findViewById(R.id.tv_author_title);
            tvTelephone = (TextView) itemView.findViewById(R.id.tv_telephone);
            tvLocation = (TextView) itemView.findViewById(R.id.tv_location);
            tvTelephone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppUtils.makeCall((Activity) context, authorModel.getUserPhone());
                }
            });

            tvLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick != null) {
                        onItemClick.showInMap(authorModel.getUserAddress().getLat(), authorModel.getUserAddress().getLon()
                                , authorModel.getUserAddress().getAddress());
                    }
                }
            });
        }

    }

    public void populateView(ArrayList<ExchangeRateModel> dataList, ExchangeRateResponse.AuthorModel model) {
        this.dataList = dataList;
        this.authorModel = model;

        notifyDataSetChanged();
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {

        void showInMap(String latitude, String longitude, String address);


    }

}
