package com.jwareconsulting.arusha.mvp.presenter;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.jwareconsulting.arusha.model.ChildModel;
import com.jwareconsulting.arusha.model.DirectoryModel;
import com.jwareconsulting.arusha.mvp.DirectoryMvp;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.mvp.model.DirectoryMvpModel;
import com.jwareconsulting.arusha.ui.fragment.DIrectorySubCatagoryFragment;
import com.jwareconsulting.arusha.ui.fragment.DirectoryDetailFragment;

import java.util.ArrayList;

/**
 * Created by anjul khanal on 8/4/16.
 */
public class DirectoryPresenter implements DirectoryMvp.Presenter, DirectoryMvpModel.Callback {
    private AppCompatActivity activity;
    private DirectoryMvp.Model mvpModel;
    private DirectoryMvp.View mvpView;
    private LandingActivityMvp.Presenter mainPresenter;

    public DirectoryPresenter(AppCompatActivity activity, Fragment fragment) {
        this.activity = activity;
        mvpView = (DirectoryMvp.View) fragment;
        mvpModel = new DirectoryMvpModel(this);
        mvpModel.init(activity);
    }

    @Override
    public void OnDataArrived(ArrayList<DirectoryModel> dataList) {
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
    public void onClick(int position) {
        ChildModel childModel = mvpModel.getCatagoryId(position);

        if (childModel.hasChild()) {
//            mainPresenter.replaceFragment(DIrectorySubCatagoryFragment.newInstance(childModel.getCatagory()), DIrectorySubCatagoryFragment.TAG, DIrectorySubCatagoryFragment.getTitle());
            DIrectorySubCatagoryFragment.setTitle(childModel.getCatagoryTitle());
            mainPresenter.replaceFragment(DIrectorySubCatagoryFragment.newInstance(childModel.getCatagory()), DIrectorySubCatagoryFragment.TAG, childModel.getCatagoryTitle());
        } else {
//            mainPresenter.replaceFragment(DirectoryDetailFragment.newInstance(childModel.getCatagory()), DirectoryDetailFragment.TAG, DIrectorySubCatagoryFragment.getTitle());
            mainPresenter.replaceFragment(DirectoryDetailFragment.newInstance(childModel.getCatagory()), DirectoryDetailFragment.TAG,childModel.getCatagoryTitle());
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

