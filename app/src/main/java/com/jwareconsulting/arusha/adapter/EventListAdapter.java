package com.jwareconsulting.arusha.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppLog;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.rest.response.model.EventResponseModel;

import java.util.ArrayList;

/**
 * Created by anjulkhanal  on 8/30/16.
 */
public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {
    Context context;
    ArrayList<EventResponseModel.EventData> mEventInfo;
    EventResponseModel.EventData pEventModel;
    private OnItemClickListner onItemClickListner;

    public EventListAdapter(Context context, ArrayList<EventResponseModel.EventData> mEventInfo) {
        this.context = context;
        this.mEventInfo = mEventInfo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_adapter_event, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        pEventModel = mEventInfo.get(i);
        viewHolder.tvEventTitle.setText(pEventModel.getEventTitle());
//        AppLog.d(TAG, String.valueOf(pEventModel.getField_events_rate()));

        if (pEventModel.getField_events_rate() == null) {
        } else {
            StringBuilder Price = new StringBuilder();
            for (int j = 0; j < pEventModel.getField_events_rate().size(); j++) {
                if (pEventModel.getField_events_rate().size() > 1) {
                    Price.append(pEventModel.getField_events_rate().get(j).getEventPrice() + "\n");
                } else {
                    Price.append(pEventModel.getField_events_rate().get(j).getEventPrice());
                }
                viewHolder.tvPrice.setText(Price.toString());
            }
        }
        if (!pEventModel.getEventFieldAddress().equalsIgnoreCase("")) {
            viewHolder.tvAddress.setText(pEventModel.getEventFieldAddress());
        } else {
            viewHolder.tvAddress.setVisibility(View.GONE);
            viewHolder.iv_event_location.setVisibility(View.GONE);
//            viewHolder.tvAddress.setText("N/A");
        }
        if (!(pEventModel.getEventFieldEventDateFrom().equalsIgnoreCase("") && pEventModel.getEventFieldEventDateTo().equalsIgnoreCase(""))) {
//            String eventFieldEventTimeFrom = pEventModel.getEventFieldEventTimeFrom();
            String eventFieldEventTimeTo ="";
            String eventFieldEventTimeFrom="";
            try {

          //      eventFieldEventTimeFrom = AppUtils.getCharacterAfterSymbol(",",pEventModel.getEventFieldEventTimeFrom());
                eventFieldEventTimeFrom = pEventModel.getEventFieldEventTimeFrom();
            } catch (Exception e) {
                AppLog.printStackStrace(e);
            }
            try {

//                eventFieldEventTimeTo = AppUtils.getCharacterAfterSymbol(",",pEventModel.getEventFieldEventTimeTo());
                eventFieldEventTimeTo = pEventModel.getEventFieldEventTimeTo();
            } catch (Exception e) {
                AppLog.printStackStrace(e);
            }

            if (pEventModel.getEventFieldEventDateFrom().equalsIgnoreCase(pEventModel.getEventFieldEventDateTo())) {
                viewHolder.tvDate.setText(AppUtils.DateConvert(pEventModel.getEventFieldEventDateFrom()) + ", " + eventFieldEventTimeFrom +  "-" + eventFieldEventTimeTo);
                viewHolder.tvDay.setText(AppUtils.DateConvertDayOnly(pEventModel.getEventFieldEventDateFrom()));
                viewHolder.tvDateTitle.setText(AppUtils.formatDate(pEventModel.getEventFieldEventDateFrom(),pEventModel.getEventFieldEventDateTo()));
            }else {
                viewHolder.tvDate.setText(AppUtils.DateConvert(pEventModel.getEventFieldEventDateFrom()) + ", " + eventFieldEventTimeFrom + "-" + eventFieldEventTimeTo
                        + "\n"+AppUtils.DateConvert(pEventModel.getEventFieldEventDateTo()) + ", " + eventFieldEventTimeTo + "-" + eventFieldEventTimeTo);
                viewHolder.tvDay.setText(AppUtils.DateConvertDayOnly(pEventModel.getEventFieldEventDateFrom())+"-"+AppUtils.DateConvertDayOnly(pEventModel.getEventFieldEventDateTo()));
                viewHolder.tvDateTitle.setText(AppUtils.formatDate(pEventModel.getEventFieldEventDateFrom(),pEventModel.getEventFieldEventDateTo()));
            }
        } else {
            viewHolder.tvDate.setVisibility(View.GONE);
            viewHolder.tvDay.setVisibility(View.GONE);
            viewHolder.tvDateTitle.setVisibility(View.GONE);
            viewHolder.iv_event_time.setVisibility(View.GONE);
//            viewHolder.tvDate.setText("N/A");
//            viewHolder.tvDay.setText("N/A");
//            viewHolder.tvDateTitle.setText("N/A");
        }
    }


    @Override
    public int getItemCount() {
        return mEventInfo.size();
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface OnItemClickListner {
        void onClick(int position, EventResponseModel.EventData pEventModel);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvEventTitle, tvPrice, tvAddress, tvDate, tvDateTitle, tvDay;//,date_year_only;
        ImageView iv_event_time, iv_event_price, iv_event_location;

        public ViewHolder(final View itemView) {
            super(itemView);
            tvEventTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_Location);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvDateTitle = (TextView) itemView.findViewById(R.id.date_title);
            tvDay = (TextView) itemView.findViewById(R.id.tv_day);
            iv_event_time = (ImageView) itemView.findViewById(R.id.iv_event_time);
            iv_event_price = (ImageView) itemView.findViewById(R.id.iv_event_price);
            iv_event_location = (ImageView) itemView.findViewById(R.id.iv_event_location);
//            date_year_only=(TextView)itemView.findViewById(R.id.date_year_only);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListner.onClick(getAdapterPosition(), mEventInfo.get(getAdapterPosition()));
        }

    }
}
