package com.jwareconsulting.arusha.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.ui.activity.MainActivity;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.adapter.ExchangeRateAdapter;
import com.jwareconsulting.arusha.model.ExchangeRateModel;
import com.jwareconsulting.arusha.mvp.ExchangeRateMvp;
import com.jwareconsulting.arusha.mvp.presenter.ExchangeRatePresenter;
import com.jwareconsulting.arusha.rest.response.model.ExchangeRateResponse;

import java.util.ArrayList;

/**
 * Created by root on 8/3/16.
 */
public class ExchangeRateFragment extends Fragment implements ExchangeRateMvp.View {
    public static final String TAG = ExchangeRateAdapter.class.getSimpleName();
    private View view;
    private RecyclerView recyclerView;
    private ExchangeRateAdapter mAdapter;
    private ExchangeRateMvp.Presenter mPresenter;
    private ProgressWheel progressWheel;
    private TextView tv_exchangerate_date;
    private LandingActivityMvp.Presenter mainPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exchange_rate, container, false);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void init(View view) {
        initView(view);
        mPresenter = new ExchangeRatePresenter((AppCompatActivity) getActivity(), this);
        getActivity().findViewById(R.id.header).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.footer).setVisibility(View.VISIBLE);
        mAdapter.setOnItemClick(new ExchangeRateAdapter.OnItemClick() {
            @Override
            public void showInMap(String latitude, String longitude, String address) {
                mainPresenter.replaceFragment(MapMenufragment.plotLocation(latitude, longitude, address),
                        MapMenufragment.TAG, getActivity().getString(R.string.text_map));
            }
        });
    }

    private void initView(View view) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleview_exchange_rate);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ExchangeRateAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
        progressWheel = (ProgressWheel) view.findViewById(R.id.progress_wheel);
        tv_exchangerate_date = (TextView) view.findViewById(R.id.tv_exchangerate_date);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        mainPresenter = activity.getPresenter();
    }

    @Override
    public void populateData(ArrayList<ExchangeRateModel> datalist, ExchangeRateResponse.AuthorModel authorModel, String date) {
        mAdapter.populateView(datalist, authorModel);
        progressWheel.setVisibility(View.GONE);
        tv_exchangerate_date.setText(date);
    }

    @Override
    public void showMsg(String msg) {
        progressWheel.setVisibility(View.GONE);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

}
