package com.jwareconsulting.arusha.ui.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jwareconsulting.arusha.common.AppLog;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.adapter.DirectoryLastPageAdapter;
import com.jwareconsulting.arusha.common.MessageHandler;
import com.jwareconsulting.arusha.model.ServiceProviderModel;
import com.jwareconsulting.arusha.mvp.DirectoryDetailMvp;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.mvp.presenter.DirectoryDetailPresenter;
import com.jwareconsulting.arusha.ui.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by anjulkhanal on 8/3/16.
 */
public class DirectoryDetailFragment extends Fragment implements DirectoryDetailMvp.View {
    public static final String TAG = DirectoryDetailFragment.class.getSimpleName();
    private View view;
    private DirectoryLastPageAdapter mAdapter;
    private RecyclerView recyclerView;
    private DirectoryDetailMvp.Presenter mPresenter;
    private ProgressWheel progressWheel;
    private static String ARG_PARAM = "id";
    private static String ARG_POSITION = "position";
    private String catagoryId;
    private int position;
    private TextView noDataText;
    private EditText etSearch;
    private MainActivity activity;
    private Typeface font;

    public static DirectoryDetailFragment newInstance(String param) {
        DirectoryDetailFragment fragment = new DirectoryDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    public static DirectoryDetailFragment newInstance1(String param,int position) {
        DirectoryDetailFragment fragment = new DirectoryDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        args.putInt(ARG_POSITION,position);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppLog.d(TAG,"onCreate");
        if (getArguments() != null) {
            catagoryId = getArguments().getString(ARG_PARAM);
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_directory_detail, container, false);
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts"+"/"+getString(R.string.proxima_font_regular));
        init(view);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.setSearchKeyWord(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    private void init(View view) {
        getActivity().findViewById(R.id.header).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.footer).setVisibility(View.VISIBLE);
        etSearch = (EditText) view.findViewById(R.id.et_search);
        etSearch.setTypeface(font);
        progressWheel = (ProgressWheel) view.findViewById(R.id.progress_wheel);
        progressWheel.setVisibility(View.VISIBLE);
        noDataText = (TextView)view.findViewById(R.id.tv_no_data);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyleview_directory_detail);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DirectoryLastPageAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);

        LandingActivityMvp.Presenter mainPresenter = activity.getPresenter();
        mPresenter = new DirectoryDetailPresenter((AppCompatActivity) getActivity(), DirectoryDetailFragment.this,catagoryId,position);
        mPresenter.onAttach(mainPresenter);
        mAdapter.setOnItemClickListner(new DirectoryLastPageAdapter.OnItemClickListner() {

            @Override
            public void onClick(int intentType, String data) {
                mPresenter.onClick(intentType,data);
            }

            @Override
            public void openInMap(String latitude, String longitude, String title) {
                mPresenter.openInMap(latitude,longitude,title);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            catagoryId = getArguments().getString(ARG_PARAM);

        }
        activity = (MainActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.onDetach();
    }




    @Override
    public void populateData(ArrayList<ServiceProviderModel> datalist) {
        mAdapter.populateView(datalist);
        progressWheel.setVisibility(View.GONE);

       if (datalist!=null) {
           if (datalist.size() > 0) {
               noDataText.setVisibility(View.GONE);
           } else {
               noDataText.setVisibility(View.VISIBLE);
           }
           progressWheel.setVisibility(View.GONE);
       }else {
           noDataText.setVisibility(View.VISIBLE);
           progressWheel.setVisibility(View.GONE);
       }
    }

    @Override
    public void showMsg(String msg) {
        MessageHandler.toast(getActivity(), msg);
        progressWheel.setVisibility(View.GONE);
    }

}
