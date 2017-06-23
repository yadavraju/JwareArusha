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
import com.jwareconsulting.arusha.adapter.DirectoryDetailAdapter;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.common.MessageHandler;
import com.jwareconsulting.arusha.mvp.DirectorySubcatagoryMvp;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.mvp.presenter.DirectorySubcatagoryPresenter;
import com.jwareconsulting.arusha.rest.response.model.SubCategoriesResponseModel;
import com.jwareconsulting.arusha.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by anjul khanal on 8/3/16.
 */
public class DIrectorySubCatagoryFragment extends Fragment implements DirectorySubcatagoryMvp.View {
    private static String TITLE = "Directory Detail";
    private static String ARG_PARAM = "id";
    private static String ARG_PARAM1 = "search_title";
    public static String TAG = DIrectorySubCatagoryFragment.class.getSimpleName();
    private View view;
    private DirectoryDetailAdapter mAdapter;
    private RecyclerView recyclerView;
    private DirectorySubcatagoryMvp.Presenter mPresenter;
    private EditText etSearch;
    private String catagoryId;
    private LandingActivityMvp.Presenter mainPresenter;
    private ProgressWheel progressWheel;
    private ImageView ivBackground;
    private Typeface font;
//    private String searchTitle;

    public static DIrectorySubCatagoryFragment newInstance(String param) {
        DIrectorySubCatagoryFragment fragment = new DIrectorySubCatagoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
//        args.putString(ARG_PARAM1, searchText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppLog.d(TAG, "onCreate");
        if (getArguments() != null) {
            catagoryId = getArguments().getString(ARG_PARAM);
//            searchTitle = getArguments().getString(ARG_PARAM1, "");
            AppLog.i(TAG, "catagoryId  " + catagoryId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_directory, container, false);
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts" + "/" + getString(R.string.proxima_font_regular));
        init(view);
        setRandomBackbroundImage();
//        etSearch.setText(searchTitle);
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
        mPresenter.onAttach(mainPresenter);
        return view;
    }

    private void init(final View view) {
        getActivity().findViewById(R.id.header).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.footer).setVisibility(View.VISIBLE);
        progressWheel = (ProgressWheel) view.findViewById(R.id.progress_wheel);
        progressWheel.setVisibility(View.VISIBLE);
        etSearch = (EditText) view.findViewById(R.id.et_search);
        etSearch.setTypeface(font);
        etSearch.setHint(getActivity().getResources().getString(R.string.text_hint_direct));
        ivBackground = (ImageView) view.findViewById(R.id.iv_background);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyleview_directory);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DirectoryDetailAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
        mPresenter = new DirectorySubcatagoryPresenter((AppCompatActivity) getActivity(), DIrectorySubCatagoryFragment.this, catagoryId);
        mAdapter.setOnItemClickListner(new DirectoryDetailAdapter.OnItemClickListner() {
            @Override
            public void onClick(int position, String subCatId) {

                mPresenter.onClick(position, subCatId);
                AppUtils.hideKeyBoard(view, getActivity());
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
        MainActivity activity = (MainActivity) context;
        mainPresenter = activity.getPresenter();
        if (getArguments() != null) {
            catagoryId = getArguments().getString(ARG_PARAM);
        }

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.onDetach();
    }

    @Override
    public void populateData(ArrayList<SubCategoriesResponseModel.DirectorySubCAtegoriesModel> datalist) {
        mAdapter.populateView(datalist);
        progressWheel.setVisibility(View.GONE);
    }

    @Override
    public void showMsg(String msg) {
        MessageHandler.toast(getActivity(), msg);
        progressWheel.setVisibility(View.GONE);
    }

   public static String getTitle() {
        return TITLE;
    }

    public static void setTitle(String title) {
        TITLE = title;
    }
}
