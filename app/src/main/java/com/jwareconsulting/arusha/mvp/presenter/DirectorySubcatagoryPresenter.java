package com.jwareconsulting.arusha.mvp.presenter;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.jwareconsulting.arusha.mvp.DirectorySubcatagoryMvp;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.mvp.model.DirectorySubcatagoryMvpModel;
import com.jwareconsulting.arusha.rest.response.model.SubCategoriesResponseModel;
import com.jwareconsulting.arusha.ui.fragment.DIrectorySubCatagoryFragment;
import com.jwareconsulting.arusha.ui.fragment.DirectoryDetailFragment;

import java.util.ArrayList;

/**
 * Created by anjulkhanal  on 8/10/16.
 */
public class DirectorySubcatagoryPresenter implements DirectorySubcatagoryMvp.Presenter, DirectorySubcatagoryMvpModel.Callback {
    private DirectorySubcatagoryMvp.Model mvpModel;
    private DirectorySubcatagoryMvp.View mvpView;
    private LandingActivityMvp.Presenter mainPresenter;

    public DirectorySubcatagoryPresenter(AppCompatActivity activity, Fragment fragment, String catagoryId) {
        mvpView = (DirectorySubcatagoryMvp.View) fragment;
        mvpModel = new DirectorySubcatagoryMvpModel(this);
        mvpModel.init(activity, catagoryId);


    }

    @Override
    public void OnDataArrived(ArrayList<SubCategoriesResponseModel.DirectorySubCAtegoriesModel> dataList) {
        mvpView.populateData(dataList);
    }

    @Override
    public void OnFailure(String msg) {
        mvpView.showMsg(msg);
    }

    @Override
    public void setSearchKeyWord(String s) {
        mvpModel.filterData(s);
    }

    @Override
    public void onClick(int position, String subCatId) {
        if (position == 0) {
            mainPresenter.replaceFragment(DirectoryDetailFragment.newInstance1(subCatId,position), DirectoryDetailFragment.TAG,mvpModel.getTitle(position));
        } else {
            mainPresenter.replaceFragment(DirectoryDetailFragment.newInstance(subCatId), DirectoryDetailFragment.TAG,mvpModel.getTitle(position));
        }


    }

    @Override
    public void onAttach(LandingActivityMvp.Presenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    @Override
    public void onDetach() {
        mainPresenter = null;
    }
}
