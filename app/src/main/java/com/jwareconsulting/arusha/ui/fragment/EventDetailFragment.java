package com.jwareconsulting.arusha.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.mvp.presenter.DirectoryPresenter;
import com.jwareconsulting.arusha.rest.response.model.EventResponseModel;
import com.jwareconsulting.arusha.ui.activity.MainActivity;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass anjulkhanal .
 */
public class EventDetailFragment extends Fragment implements View.OnClickListener {
    public static String TAG = EventDetailFragment.class.getSimpleName();
    EventResponseModel.EventData pEventModel;
    private TextView tvEventTitle, tvPrice, tvAddress, tvDate, tvDateTitle, tvDay;
    private ImageView ivEventImage, iv_event_time, iv_event_price, iv_event_location, iv_location_deact, iv_phone_deact, iv_web_deact, iv_mail_deact, iv_fb_deact, iv_trip_deact, iv_twitter_deact, iv_youtube_deact;
    private TextView tv_hun_charactery;
    private DirectoryPresenter mPresenter;
    private LandingActivityMvp.Presenter mainPresenter;

    public EventDetailFragment() {
        // Required empty public constructor
    }

    public EventDetailFragment(EventResponseModel.EventData pEventModel) {
        // Required empty public constructor
        this.pEventModel = pEventModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        intitView(view);
        setData(pEventModel);
        return view;
    }

    private void setData(EventResponseModel.EventData pEventModel) {
        tvEventTitle.setText(pEventModel.getEventTitle());
        if (pEventModel.getField_events_rate() == null) {
        } else {
            StringBuilder Price = new StringBuilder();
            for (int j = 0; j < pEventModel.getField_events_rate().size(); j++) {
                if (pEventModel.getField_events_rate().size() > 1) {
                    Price.append(pEventModel.getField_events_rate().get(j).getEventPrice() + "\n");

                } else {
                    Price.append(pEventModel.getField_events_rate().get(j).getEventPrice());
                }
                tvPrice.setText(Price.toString());
            }
        }
        if (!pEventModel.getEventFieldAddress().equalsIgnoreCase("")) {
            tvAddress.setText(pEventModel.getEventFieldAddress());
        } else {
            tvAddress.setVisibility(View.GONE);
            iv_event_location.setVisibility(View.GONE);
        }
        if (!(pEventModel.getEventFieldEventDateFrom().equalsIgnoreCase("") && pEventModel.getEventFieldEventDateTo().equalsIgnoreCase(""))) {
            if (pEventModel.getEventFieldEventDateFrom().equalsIgnoreCase(pEventModel.getEventFieldEventDateTo())) {
                tvDate.setText(AppUtils.DateConvert(pEventModel.getEventFieldEventDateFrom()) + ", " + pEventModel.getEventFieldEventTimeFrom() + "-" + pEventModel.getEventFieldEventTimeTo());
                tvDay.setText(AppUtils.DateConvertDayOnly(pEventModel.getEventFieldEventDateFrom()));
                tvDateTitle.setText(AppUtils.DateConvertMonthYear(pEventModel.getEventFieldEventDateFrom()));
            } else {
                tvDate.setText(AppUtils.DateConvert(pEventModel.getEventFieldEventDateFrom()) + ", " + pEventModel.getEventFieldEventTimeFrom()+ "-" + pEventModel.getEventFieldEventTimeTo()
                        + "\n" + AppUtils.DateConvert(pEventModel.getEventFieldEventDateTo()) + ", " + pEventModel.getEventFieldEventTimeTo() + "-" + pEventModel.getEventFieldEventTimeTo() );
                tvDay.setText(AppUtils.DateConvertDayOnly(pEventModel.getEventFieldEventDateFrom()) + "-" + AppUtils.DateConvertDayOnly(pEventModel.getEventFieldEventDateTo()));
                tvDateTitle.setText(AppUtils.DateConvertMonthOnly(pEventModel.getEventFieldEventDateFrom()) + "-" + AppUtils.DateConvertMonthOnly(pEventModel.getEventFieldEventDateTo() + "," + ""));
                //date_year_only.setVisibility(View.VISIBLE);
                //date_year_only.setText(AppUtils.DateConvertYearOnly(pEventModel.getEventFieldEventDateFrom()) + "-" + AppUtils.DateConvertYearOnly(pEventModel.getEventFieldEventDateTo()));
            }
        } else {
            tvDate.setVisibility(View.GONE);
            tvDay.setVisibility(View.GONE);
            tvDateTitle.setVisibility(View.GONE);
            iv_event_time.setVisibility(View.GONE);
        }
        Picasso.with(getActivity())
                .load(pEventModel.getEventImage())
                .placeholder(R.drawable.placeholder)
                .into(ivEventImage);
        String eventTripAdvisorUrl = pEventModel.getEventTripAdvisorUrl();
        if (eventTripAdvisorUrl.equalsIgnoreCase("")) {
            iv_trip_deact.setEnabled(false);
        } else {
            iv_trip_deact.setImageResource(R.drawable.trip_advisor_act);
        }
        if (pEventModel.getEventFbUrl().equalsIgnoreCase("")) {
            iv_fb_deact.setEnabled(false);
        } else {
            iv_fb_deact.setImageResource(R.drawable.fb_act);

        }
        if (pEventModel.getEventTwitterUrl().equalsIgnoreCase("")) {
            iv_twitter_deact.setEnabled(false);
        } else {
            iv_twitter_deact.setImageResource(R.drawable.twitter_act);

        }
        if (pEventModel.getEventYoutubeUrl().equalsIgnoreCase("")) {
            iv_youtube_deact.setEnabled(false);
        } else {
            iv_youtube_deact.setImageResource(R.drawable.youtube_act);
        }
        if (pEventModel.getEventHundredCharacterField().equalsIgnoreCase("")) {
            tv_hun_charactery.setVisibility(View.GONE);

        } else {
            tv_hun_charactery.setText(pEventModel.getEventHundredCharacterField());
        }

        if (pEventModel.getEventemailrUrl().equalsIgnoreCase("")) {
            iv_mail_deact.setEnabled(false);
        } else {
            iv_mail_deact.setImageResource(R.drawable.ic_mail_act);

        }
        if (pEventModel.getEventContacteUrl().equalsIgnoreCase("")) {
            iv_phone_deact.setEnabled(false);
        } else {
            iv_phone_deact.setImageResource(R.drawable.ic_phone_act);
        }
        if (pEventModel.getEventwebUrl().equalsIgnoreCase("")) {
            iv_web_deact.setEnabled(false);

        } else {
            iv_web_deact.setImageResource(R.drawable.ic_web_act);
        }
        if (pEventModel.getEventFieldLocation().getLat().equalsIgnoreCase("") && pEventModel.getEventFieldLocation().getLon().equalsIgnoreCase("")) {
            iv_location_deact.setVisibility(View.GONE);

        } else {
            iv_location_deact.setImageResource(R.drawable.location_act);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void intitView(View itemView) {
        getActivity().findViewById(R.id.header).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.footer).setVisibility(View.GONE);
        tvEventTitle = (TextView) itemView.findViewById(R.id.tv_title);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_location_deact:
                // AppUtils.openINBrowser(getActivity(),pEventModel.getEventFieldLocation().getLat());
                Fragment fragment = MapMenufragment.plotLocation(pEventModel.getEventFieldLocation().getLat(), pEventModel.getEventFieldLocation().getLon(), pEventModel.getEventFieldAddress().toString());
                mainPresenter.replaceFragment(fragment, MapMenufragment.TAG,getString(R.string.text_map));
                break;
            case R.id.iv_phone_deact:
                AppUtils.makeCall(getActivity(), pEventModel.getEventContacteUrl());
                break;
            case R.id.iv_web_deact:
                AppUtils.openINBrowser(getActivity(), pEventModel.getEventwebUrl());
                break;
            case R.id.iv_mail_deact:
                AppUtils.launchEmailIntent(getActivity(), pEventModel.getEventemailrUrl());
                break;
            case R.id.iv_fb_deact:
                AppUtils.openINBrowser(getActivity(), pEventModel.getEventFbUrl());
                break;
            case R.id.iv_trip_deact:
                AppUtils.openINBrowser(getActivity(), pEventModel.getEventTripAdvisorUrl());
                break;
            case R.id.iv_twitter_deact:
                AppUtils.openINBrowser(getActivity(), pEventModel.getEventTwitterUrl());
                break;
            case R.id.iv_youtube_deact:
                AppUtils.openINBrowser(getActivity(), pEventModel.getEventYoutubeUrl());
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        mainPresenter = activity.getPresenter();
    }
}
