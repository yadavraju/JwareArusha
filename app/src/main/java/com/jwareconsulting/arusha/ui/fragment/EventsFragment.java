package com.jwareconsulting.arusha.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jwareconsulting.arusha.common.AppText;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.adapter.EventListAdapter;
import com.jwareconsulting.arusha.common.AppCache;
import com.jwareconsulting.arusha.common.AppLog;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.common.SharedPreferencesManager;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.rest.ApiManager;
import com.jwareconsulting.arusha.rest.response.model.EventResponseModel;
import com.jwareconsulting.arusha.ui.activity.MainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anjulkhanal  on 8/3/16.
 */
public class EventsFragment extends Fragment {

    public static final String TAG = EventsFragment.class.getSimpleName();
    RecyclerView rv_event;
    private View view;
    private ProgressWheel progressWheel;
    private EventListAdapter eventListAdapter;
    LandingActivityMvp.Presenter mainPresenter;
    SharedPreferencesManager sharedPreferences;
    private LandingActivityMvp.View landingActivityView;
    private Callback<EventResponseModel> callback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_events, container, false);
        sharedPreferences = new SharedPreferencesManager(getActivity());
        init(view);
        if (AppUtils.hasInternet(getActivity())) {
            topEventTask();
        } else {
            progressWheel.setVisibility(View.GONE);
            if (AppCache.getCachedEventRate(getActivity())!=null) {
                EventResponseModel eventResponseModel = AppCache.getCachedEventRate(getActivity());
                setAdapterData(eventResponseModel.getDataList());
            }else {
                Toast.makeText(getActivity(), getString(R.string.no_cache_data), Toast.LENGTH_SHORT).show();
            }
        }
        return view;
    }

    private void topEventTask() {

        callback=new Callback<EventResponseModel>() {
            @Override
            public void onResponse(Call<EventResponseModel> call, Response<EventResponseModel> response) {
                AppLog.v(TAG, response.toString());
                progressWheel.setVisibility(View.GONE);
                if (response.code() == 200) {
                    EventResponseModel serviceProviderResponse = response.body();
                    if (serviceProviderResponse.isSuccess()) {
                        String lastUpdate = serviceProviderResponse.getLastUpdate();
                        if (lastUpdate != null) {
                            AppCache.cacheEventRate(getActivity(),serviceProviderResponse);
                        }
                        setAdapterData(serviceProviderResponse.getDataList());

                    } else {
                        Toast.makeText(getActivity(), serviceProviderResponse.getMessage() + "", Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 204) {
                    fetchFromCache(getActivity());

                }
                else if (response.code() == 500) {
                    Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventResponseModel> call, Throwable t) {
                progressWheel.setVisibility(View.GONE);
            }
        };

        ApiManager.getEventApi(getActivity(),callback);
    }

    private void setAdapterData(ArrayList<EventResponseModel.EventData> dataList) {
        eventListAdapter = new EventListAdapter(getActivity(), dataList);
        rv_event.setAdapter(eventListAdapter);
        eventListAdapter.setOnItemClickListner(new EventListAdapter.OnItemClickListner() {
            @Override
            public void onClick(int position, EventResponseModel.EventData pEventModel) {
                mainPresenter.replaceFragment(new EventDetailFragment(pEventModel),EventDetailFragment.TAG, getActivity().getString(R.string.text_events));
                //landingActivityView.changeAds(AppText.KEY_ADS_DIRECTORY_CATEGORY_LIST);
            }
        });
    }

    private void init(View view) {
        getActivity().findViewById(R.id.header).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.footer).setVisibility(View.VISIBLE);
        progressWheel = (ProgressWheel) view.findViewById(R.id.pb_loading);
        progressWheel.setVisibility(View.VISIBLE);
        rv_event = (RecyclerView) view.findViewById(R.id.rv_event);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_event.setLayoutManager(mLayoutManager);


    }
    public void fetchFromCache(Context context) {
        progressWheel.setVisibility(View.GONE);
        if (AppCache.getCachedEventRate(getActivity())!=null) {
            EventResponseModel eventResponseModel = AppCache.getCachedEventRate(getActivity());
            setAdapterData(eventResponseModel.getDataList());
        }else {
            Toast.makeText(context, getString(R.string.no_cache_data), Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        mainPresenter = activity.getPresenter();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
