package com.jwareconsulting.arusha.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.rest.response.model.TopThingsToDoModel;

import java.util.ArrayList;

/**
 * Created by anjulkhanal  on 8/31/16.
 */
public class ThingToDoAdapter extends RecyclerView.Adapter<ThingToDoAdapter.ViewHolder> {
    Context context;
    ArrayList<TopThingsToDoModel.ThingToData> mThingsToDoInfo;
    TopThingsToDoModel.ThingToData pThingsToDoModel;
    private OnItemClickListner onItemClickListner;

    public ThingToDoAdapter(Context context, ArrayList<TopThingsToDoModel.ThingToData> mThingsToDoInfo) {
        this.context = context;
        this.mThingsToDoInfo = mThingsToDoInfo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_adapter_things_to_do, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        pThingsToDoModel = mThingsToDoInfo.get(i);
        viewHolder.tvThingToDoTitle.setText(pThingsToDoModel.getThingToDoTitle());
        if (!pThingsToDoModel.getThingToDoNumber().equalsIgnoreCase("")) {
            viewHolder.tvPhone.setText(pThingsToDoModel.getThingToDoNumber());
        } else {
            viewHolder.tvPhone.setVisibility(View.GONE);
            viewHolder.iv_event_phone.setVisibility(View.GONE);
        }
//        Log.e("Raju", String.valueOf(pThingsToDoModel.getField_events_rate()));
        if (pThingsToDoModel.getFieldOpeningHour() == null) {
            viewHolder.ivEventTime.setVisibility(View.GONE);
            viewHolder.tvDate.setVisibility(View.GONE);
        } else {
            if (pThingsToDoModel.getFieldOpeningHour().size() > 0) {
                StringBuilder Price = new StringBuilder();
                for (int j = 0; j < pThingsToDoModel.getFieldOpeningHour().size(); j++) {
                    Price.append(pThingsToDoModel.getFieldOpeningHour().get(j).getThingToDay() + ", " + pThingsToDoModel.getFieldOpeningHour().get(j).getThingToTimimg() + "\n");
                    viewHolder.tvDate.setText(Price.toString());
                }
            } else {
                viewHolder.ivEventTime.setVisibility(View.GONE);
                viewHolder.tvDate.setVisibility(View.GONE);
            }

        }
        if (!pThingsToDoModel.getThingToDoFieldAddress().equalsIgnoreCase("")) {
            viewHolder.tvAddress.setText(pThingsToDoModel.getThingToDoFieldAddress());
        } else {
            viewHolder.tvAddress.setVisibility(View.GONE);
            viewHolder.iv_event_location.setVisibility(View.GONE);
        }


        if (!pThingsToDoModel.getMoreInfo().equalsIgnoreCase("")) {

            viewHolder.tvExtraDescription.setText(pThingsToDoModel.getMoreInfo());
            viewHolder.tvExtraDescription.setVisibility(View.VISIBLE);
        } else {

            viewHolder.tvExtraDescription.setVisibility(View.GONE);
        }

        Picasso.with(context)
                .load(pThingsToDoModel.getThingToDoImageLarge())
                .into(viewHolder.ivThingTodoImaage);
    }


    @Override
    public int getItemCount() {
        return mThingsToDoInfo.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvThingToDoTitle, tvPhone, tvAddress, tvDate, tvDateTitle, tvDay, tvExtraDescription;
        ImageView ivThingTodoImaage, ivEventTime, iv_event_phone, iv_event_location;

        public ViewHolder(final View itemView) {
            super(itemView);
            tvThingToDoTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_price);
            tvDate = (TextView) itemView.findViewById(R.id.tv_time);
//            tvDateTitle =(TextView)itemView.findViewById(R.id.date_title);
//            tvDay =(TextView)itemView.findViewById(R.id.tv_day);
            ivThingTodoImaage = (ImageView) itemView.findViewById(R.id.iv_image);
            ivEventTime = (ImageView) itemView.findViewById(R.id.tv_event_time);
            iv_event_phone = (ImageView) itemView.findViewById(R.id.tv_event_phone);
            iv_event_location = (ImageView) itemView.findViewById(R.id.tv_event_location);
            tvExtraDescription = (TextView) itemView.findViewById(R.id.tv_extra_description);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onItemClickListner.onClick(getAdapterPosition(), mThingsToDoInfo.get(getAdapterPosition()));
        }

    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface OnItemClickListner {
        void onClick(int position, TopThingsToDoModel.ThingToData pEventModel);
    }
}

