package com.jwareconsulting.arusha.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jwareconsulting.arusha.common.ValidationUtils;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.ui.activity.MainActivity;
import com.squareup.picasso.Picasso;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.rest.response.model.TopThingsToDoModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass anjulkhanal .
 */
public class ThingsToDoDetailFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = ThingsToDoDetailFragment.class.getCanonicalName();
    TopThingsToDoModel.ThingToData pThingsToDoModel;
    //    private TextView tvThingToDoTitle, tvPhone, tvAddress, tvDate, tvDateTitle, tvDay;
//    private ImageView ivThingTodoImaage, iv_event_time, iv_event_phone, iv_event_location;
    private TextView tvThingToDoTitle, tvPrice, tvAddress, tvDate, tvDateTitle, tvDay;
    private ImageView ivEventImage, iv_event_time, iv_event_price, iv_event_location, iv_location_deact, iv_phone_deact, iv_web_deact, iv_mail_deact, iv_fb_deact, iv_trip_deact, iv_twitter_deact, iv_youtube_deact;
    private TextView tv_hun_charactery;


    private LandingActivityMvp.Presenter mainPresenter;

    public ThingsToDoDetailFragment() {
        // Required empty public constructor
    }

    public ThingsToDoDetailFragment(TopThingsToDoModel.ThingToData pTodoModelModel) {
        this.pThingsToDoModel = pTodoModelModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_top_things_todo, container, false);
        initView(view);
        setData(pThingsToDoModel);
        return view;
    }

    private void initView(View itemView) {
        getActivity().findViewById(R.id.header).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.footer).setVisibility(View.GONE);
        tvThingToDoTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        tvAddress = (TextView) itemView.findViewById(R.id.tv_Location);
        tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        tvDateTitle = (TextView) itemView.findViewById(R.id.date_title);
        tvDay = (TextView) itemView.findViewById(R.id.tv_day);
        ivEventImage = (ImageView) itemView.findViewById(R.id.iv_event_image);
        iv_event_time = (ImageView) itemView.findViewById(R.id.iv_event_time);
        iv_event_price = (ImageView) itemView.findViewById(R.id.iv_event_price);
        iv_event_location = (ImageView) itemView.findViewById(R.id.iv_event_location);

        /*Social media declaration*/
        iv_location_deact = (ImageView) itemView.findViewById(R.id.iv_location_deact);
        iv_phone_deact = (ImageView) itemView.findViewById(R.id.iv_phone_deact);
        iv_web_deact = (ImageView) itemView.findViewById(R.id.iv_web_deact);
        iv_mail_deact = (ImageView) itemView.findViewById(R.id.iv_mail_deact);
        iv_fb_deact = (ImageView) itemView.findViewById(R.id.iv_fb_deact);
        iv_trip_deact = (ImageView) itemView.findViewById(R.id.iv_trip_deact);
        iv_twitter_deact = (ImageView) itemView.findViewById(R.id.iv_twitter_deact);
        iv_youtube_deact = (ImageView) itemView.findViewById(R.id.iv_youtube_deact);
        tv_hun_charactery = (TextView) itemView.findViewById(R.id.tv_hun_character);

        iv_location_deact.setOnClickListener(this);
        iv_phone_deact.setOnClickListener(this);
        iv_web_deact.setOnClickListener(this);
        iv_mail_deact.setOnClickListener(this);
        iv_fb_deact.setOnClickListener(this);
        iv_trip_deact.setOnClickListener(this);
        iv_twitter_deact.setOnClickListener(this);
        iv_youtube_deact.setOnClickListener(this);
    }

    private void setData(TopThingsToDoModel.ThingToData pThingsToDoModel) {
        tvThingToDoTitle.setText(pThingsToDoModel.getThingToDoTitle());
      /*  if (pThingsToDoModel.getField_events_rate() == null) {
        } else {
            StringBuilder Price = new StringBuilder();
            for (int j = 0; j < pThingsToDoModel.getField_events_rate().size(); j++) {
                if (pThingsToDoModel.getField_events_rate().size() > 1) {
                    Price.append(pThingsToDoModel.getField_events_rate().get(j).getEventPrice() + "\n");
                } else {
                    Price.append(pThingsToDoModel.getField_events_rate().get(j).getEventPrice());
                }
                tvPrice.setText(Price.toString());
            }
        }*/

        String price = pThingsToDoModel.getPrice();
        if (price != null) {
            iv_event_price.setVisibility(View.VISIBLE);
            tvPrice.setVisibility(View.VISIBLE);
            tvPrice.setText(price);

        } else {
            tvPrice.setVisibility(View.GONE);
            iv_event_price.setVisibility(View.GONE);
        }

        if (!pThingsToDoModel.getThingToDoFieldAddress().equalsIgnoreCase("")) {
            tvAddress.setText(pThingsToDoModel.getThingToDoFieldAddress());
        } else {
            tvAddress.setVisibility(View.GONE);
            iv_event_location.setVisibility(View.GONE);
        }

        ArrayList<TopThingsToDoModel.FieldOpeningHour> fieldOpeningHour = pThingsToDoModel.getFieldOpeningHour();

        if (fieldOpeningHour != null) {
            iv_event_time.setVisibility(View.VISIBLE);
            tvDate.setVisibility(View.VISIBLE);

            if (fieldOpeningHour.size() > 0) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < fieldOpeningHour.size(); i++) {
                    String day = fieldOpeningHour.get(i).getThingToDay();
                    String time = fieldOpeningHour.get(i).getThingToTimimg();
                    if (!ValidationUtils.isEmpty(day)) {
                        builder.append(day);
                        builder.append("\n");
                    }
                    if (!ValidationUtils.isEmpty(time)) {
                        builder.append(time);
                        if (i < fieldOpeningHour.size() - 1) {
                            builder.append("\n");
                        }
                    }


//                    builder.append()

                }
                tvDate.setText(builder.toString());
            } else {
                tvDate.setVisibility(View.GONE);
                iv_event_time.setVisibility(View.GONE);
            }

        } else {
            tvDate.setVisibility(View.GONE);
            iv_event_time.setVisibility(View.GONE);
        }
        /** if (!(pThingsToDoModel.getEventFieldEventDateFrom().equalsIgnoreCase("") && pThingsToDoModel.getEventFieldEventDateTo().equalsIgnoreCase(""))) {
         if (pThingsToDoModel.getEventFieldEventDateFrom().equalsIgnoreCase(pThingsToDoModel.getEventFieldEventDateTo())) {
         tvDate.setText(AppUtils.DateConvert(pThingsToDoModel.getEventFieldEventDateFrom()) + ", " + pThingsToDoModel.getEventFieldEventTimeFrom() + "hrs" + "-" + pThingsToDoModel.getEventFieldEventTimeTo() + "hrs");
         tvDay.setText(AppUtils.DateConvertDayOnly(pThingsToDoModel.getEventFieldEventDateFrom()));
         tvDateTitle.setText(AppUtils.DateConvertMonthYear(pThingsToDoModel.getEventFieldEventDateFrom()));
         } else {
         tvDate.setText(AppUtils.DateConvert(pThingsToDoModel.getEventFieldEventDateFrom()) + ", " + pThingsToDoModel.getEventFieldEventTimeFrom() + "hrs" + "-" + pThingsToDoModel.getEventFieldEventTimeTo() + "hrs"
         + "\n" + AppUtils.DateConvert(pThingsToDoModel.getEventFieldEventDateTo()) + ", " + pThingsToDoModel.getEventFieldEventTimeTo() + "hrs" + "-" + pThingsToDoModel.getEventFieldEventTimeTo() + "hrs");
         tvDay.setText(AppUtils.DateConvertDayOnly(pThingsToDoModel.getEventFieldEventDateFrom()) + "-" + AppUtils.DateConvertDayOnly(pThingsToDoModel.getEventFieldEventDateTo()));
         tvDateTitle.setText(AppUtils.DateConvertMonthOnly(pThingsToDoModel.getEventFieldEventDateFrom()) + "-" + AppUtils.DateConvertMonthOnly(pThingsToDoModel.getEventFieldEventDateTo() + "," + ""));
         //date_year_only.setVisibility(View.VISIBLE);
         //date_year_only.setText(AppUtils.DateConvertYearOnly(pThingsToDoModel.getEventFieldEventDateFrom()) + "-" + AppUtils.DateConvertYearOnly(pThingsToDoModel.getEventFieldEventDateTo()));
         }
         } else {
         tvDate.setVisibility(View.GONE);
         tvDay.setVisibility(View.GONE);
         tvDateTitle.setVisibility(View.GONE);
         iv_event_time.setVisibility(View.GONE);
         }*/

        Picasso.with(getActivity())
                .load(pThingsToDoModel.getThingToDoImage())
                .placeholder(R.drawable.placeholder)
                .into(ivEventImage);
        String eventTripAdvisorUrl = pThingsToDoModel.getTripAdvisior();
        if (eventTripAdvisorUrl.equalsIgnoreCase("")) {
            iv_trip_deact.setEnabled(false);
        } else {
            iv_trip_deact.setImageResource(R.drawable.trip_advisor_act);
        }
        if (pThingsToDoModel.getFieldFacebook().equalsIgnoreCase("")) {
            iv_fb_deact.setEnabled(false);
        } else {
            iv_fb_deact.setImageResource(R.drawable.fb_act);

        }
        if (pThingsToDoModel.getFieldTwitter().equalsIgnoreCase("")) {
            iv_twitter_deact.setEnabled(false);
        } else {
            iv_twitter_deact.setImageResource(R.drawable.twitter_act);

        }
        if (pThingsToDoModel.getFieldYoutube().equalsIgnoreCase("")) {
            iv_youtube_deact.setEnabled(false);
        } else {
            iv_youtube_deact.setImageResource(R.drawable.youtube_act);
        }
        if (pThingsToDoModel.getInfoDescription().equalsIgnoreCase("")) {
            tv_hun_charactery.setVisibility(View.GONE);
        } else {
            tv_hun_charactery.setText(pThingsToDoModel.getInfoDescription());
        }

        if (pThingsToDoModel.getFieldEmail().equalsIgnoreCase("")) {
            iv_mail_deact.setEnabled(false);
        } else {
            iv_mail_deact.setImageResource(R.drawable.ic_mail_act);

        }
        if (pThingsToDoModel.getThingToDoNumber().equalsIgnoreCase("")) {
            iv_phone_deact.setEnabled(false);
        } else {
            iv_phone_deact.setImageResource(R.drawable.ic_phone_act);
        }
        if (pThingsToDoModel.getFieldWebsite().equalsIgnoreCase("")) {
            iv_web_deact.setEnabled(false);

        } else {
            iv_web_deact.setImageResource(R.drawable.ic_web_act);
        }
        if (pThingsToDoModel.getThingToDoFieldLocation().getLat().equalsIgnoreCase("") && pThingsToDoModel.getThingToDoFieldLocation().getLon().equalsIgnoreCase("")) {
            iv_location_deact.setVisibility(View.GONE);

        } else {
            iv_location_deact.setImageResource(R.drawable.location_act);
        }
    }

    /*private void initView1(View itemView) {
        getActivity().findViewById(R.id.header).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.footer).setVisibility(View.GONE);
        tvThingToDoTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
        tvAddress = (TextView) itemView.findViewById(R.id.tv_price);
        tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        ivThingTodoImaage = (ImageView) itemView.findViewById(R.id.iv_image1);
        iv_event_time = (ImageView) itemView.findViewById(R.id.iv_event_time1);
        iv_event_phone = (ImageView) itemView.findViewById(R.id.tv_event_phone);
        iv_event_location = (ImageView) itemView.findViewById(R.id.tv_event_location);
        *//*iv_event_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        *//*
        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtils.makeCall(getActivity(), pThingsToDoModel.getThingToDoNumber());
            }
        });
        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapMenufragment mapMenufragment =
                        MapMenufragment.plotLocation(pThingsToDoModel.getThingToDoFieldLocation().getLat(), pThingsToDoModel.getThingToDoFieldLocation().getLon(), pThingsToDoModel.getThingToDoFieldAddress());
                mainPresenter.replaceFragment(mapMenufragment, MapMenufragment.TAG, getString(R.string.text_map));
            }
        });
    }
*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        mainPresenter = activity.getPresenter();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_location_deact:
                // AppUtils.openINBrowser(getActivity(),pThingsToDoModel.getEventFieldLocation().getLat());
                Fragment fragment = MapMenufragment.plotLocation(pThingsToDoModel.getThingToDoFieldLocation().getLat(), pThingsToDoModel.getThingToDoFieldLocation().getLon(), pThingsToDoModel.getThingToDoFieldAddress().toString());
                mainPresenter.replaceFragment(fragment, MapMenufragment.TAG, getString(R.string.text_map));
                break;
            case R.id.iv_phone_deact:
                AppUtils.makeCall(getActivity(), pThingsToDoModel.getThingToDoNumber());
                break;
            case R.id.iv_web_deact:
                AppUtils.openINBrowser(getActivity(), pThingsToDoModel.getFieldWebsite());
                break;
            case R.id.iv_mail_deact:
                AppUtils.launchEmailIntent(getActivity(), pThingsToDoModel.getFieldEmail());
                break;
            case R.id.iv_fb_deact:
                AppUtils.openINBrowser(getActivity(), pThingsToDoModel.getFieldFacebook());
                break;
            case R.id.iv_trip_deact:
                AppUtils.openINBrowser(getActivity(), pThingsToDoModel.getTripAdvisior());
                break;
            case R.id.iv_twitter_deact:
                AppUtils.openINBrowser(getActivity(), pThingsToDoModel.getFieldTwitter());
                break;
            case R.id.iv_youtube_deact:
                AppUtils.openINBrowser(getActivity(), pThingsToDoModel.getFieldYoutube());
                break;
        }

    }
}
