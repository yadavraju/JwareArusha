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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.jwareconsulting.arusha.common.AppLog;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.adapter.DirectoryAdapter;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.common.MessageHandler;
import com.jwareconsulting.arusha.model.DirectoryModel;
import com.jwareconsulting.arusha.mvp.DirectoryMvp;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.mvp.presenter.DirectoryPresenter;
import com.jwareconsulting.arusha.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by anjulkhanal on 8/3/16.
 */
public class DirectoryFragment extends Fragment implements DirectoryMvp.View {
    public static final String TAG = DirectoryFragment.class.getSimpleName();
    private static String TITLE = "Directory Detail";
    private static String ARG_PARAM = "id";
    private View view;
    private DirectoryAdapter mAdapter;
    private RecyclerView recyclerView;
    private DirectoryMvp.Presenter mPresenter;
    private EditText etSearch;
    private ProgressWheel progressWheel;
    private ImageView ivBackground;
    private MainActivity activity;
    private Typeface font;

    public static DirectoryFragment newInstance(String param) {
        DirectoryFragment fragment = new DirectoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppLog.d(TAG,"onCreate");
        view = inflater.inflate(R.layout.fragment_directory, container, false);
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts"+"/"+getString(R.string.proxima_font_regular));
        init(view);
        setRandomBackbroundImage();
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

    private void init(final View view) {
        getActivity().findViewById(R.id.header).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.footer).setVisibility(View.VISIBLE);
        progressWheel = (ProgressWheel) view.findViewById(R.id.progress_wheel);
        progressWheel.setVisibility(View.VISIBLE);
        etSearch = (EditText) view.findViewById(R.id.et_search);
        etSearch.setTypeface(font);
        ivBackground = (ImageView) view.findViewById(R.id.iv_background);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyleview_directory);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DirectoryAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);


        LandingActivityMvp.Presenter mainPresenter = activity.getPresenter();
        mPresenter = new DirectoryPresenter((AppCompatActivity) getActivity(), DirectoryFragment.this);
        mPresenter.onAttach(mainPresenter);
        mAdapter.setOnItemClickListner(new DirectoryAdapter.OnItemClickListner() {
            @Override
            public void onClick(int position) {
                mPresenter.onClick(position);
                AppUtils.hideKeyBoard(view,getActivity());
                etSearch.setText("");

            }
        });
    }

    private void setRandomBackbroundImage() {
        int[] mbgIds = new int[]{R.drawable.zebra, R.drawable.tower, R.drawable.mt_meru};
        Random rnd = new Random();
        Integer u = mbgIds[rnd.nextInt(mbgIds.length)];
        Log.e(TAG, "IMAGE_GET" + u);
        ivBackground.setBackgroundResource(u);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.onDetach();
    }

    @Override
    public void populateData(ArrayList<DirectoryModel> datalist) {
        mAdapter.populateView(datalist);
        progressWheel.setVisibility(View.GONE);
    }

    @Override
    public void showMsg(String msg) {
        MessageHandler.toast(getActivity(), msg);
        progressWheel.setVisibility(View.GONE);
    }
}
