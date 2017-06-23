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

import com.pnikosis.materialishprogress.ProgressWheel;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.adapter.ThingToDoAdapter;
import com.jwareconsulting.arusha.common.AppCache;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.rest.ApiManager;
import com.jwareconsulting.arusha.rest.response.model.TopThingsToDoModel;
import com.jwareconsulting.arusha.ui.activity.MainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anjulkhanal  on 8/3/16.
 */
public class TopThingsTodoFragment extends Fragment {

    public static final String TAG = TopThingsTodoFragment.class.getSimpleName();
    private View view;
    RecyclerView rv_event;
    private ProgressWheel progressWheel;
    private ThingToDoAdapter thingToDoListAdapter;
    LandingActivityMvp.Presenter mainPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_top_ten, container, false);
        init(view);
        if (AppUtils.hasInternet(getActivity())) {
            topTenThingsTask();
        } else {
            progressWheel.setVisibility(View.GONE);
            if (AppCache.getCachedThingsToDoData(getActivity()) != null) {
                TopThingsToDoModel topThingsToDoModel = AppCache.getCachedThingsToDoData(getActivity());
                setAdapterData(topThingsToDoModel.getDataList());
            } else {
                Toast.makeText(getActivity(), getString(R.string.no_cache_data), Toast.LENGTH_SHORT).show();
            }
        }

        return view;
    }

    private void init(View view) {
        getActivity().findViewById(R.id.header).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.footer).setVisibility(View.VISIBLE);
        progressWheel = (ProgressWheel) view.findViewById(R.id.pb_loading);
        rv_event = (RecyclerView) view.findViewById(R.id.rv_event);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_event.setLayoutManager(mLayoutManager);
    }

    private void topTenThingsTask() {
        progressWheel.setVisibility(View.VISIBLE);
        Callback<TopThingsToDoModel> callback = new Callback<TopThingsToDoModel>() {
            @Override
            public void onResponse(Call<TopThingsToDoModel> call, Response<TopThingsToDoModel> response) {
                progressWheel.setVisibility(View.GONE);
                if (response.code() == 200) {
                    TopThingsToDoModel serviceProviderResponse = response.body();
                    if (serviceProviderResponse.isSuccess()) {
                        String lastUpdate = serviceProviderResponse.getLastUpdate();
                        if (lastUpdate != null) {
                            AppCache.cacheThingsToDoData(getActivity(), serviceProviderResponse);
                        }
                        setAdapterData(serviceProviderResponse.getDataList());

                    } else {
                        Toast.makeText(getActivity(), serviceProviderResponse.getMessage() + "", Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 204) {
                    fetchFromCache(getActivity());
                } else if (response.code() == 500) {
                    Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TopThingsToDoModel> call, Throwable t) {
                progressWheel.setVisibility(View.GONE);
            }
        };
        ApiManager.getTopThingsTodo(getActivity(), callback);
    }

    private void fetchFromCache(Context context) {
        progressWheel.setVisibility(View.GONE);
        if (AppCache.getCachedThingsToDoData(getActivity()) != null) {
            TopThingsToDoModel topThingsToDoModel = AppCache.getCachedThingsToDoData(getActivity());
            setAdapterData(topThingsToDoModel.getDataList());
        } else {
            Toast.makeText(context, getString(R.string.no_cache_data), Toast.LENGTH_SHORT).show();
        }
    }

    private void setAdapterData(ArrayList<TopThingsToDoModel.ThingToData> dataList) {
        thingToDoListAdapter = new ThingToDoAdapter(getActivity(), dataList);
        rv_event.setAdapter(thingToDoListAdapter);
        thingToDoListAdapter.setOnItemClickListner(new ThingToDoAdapter.OnItemClickListner() {
            @Override
            public void onClick(int position, TopThingsToDoModel.ThingToData pTodoModelModel) {
                mainPresenter.replaceFragment(new ThingsToDoDetailFragment(pTodoModelModel), ThingsToDoDetailFragment.TAG, getActivity().getString(R.string.drawer_top_10_things));
            }
        });
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
}
