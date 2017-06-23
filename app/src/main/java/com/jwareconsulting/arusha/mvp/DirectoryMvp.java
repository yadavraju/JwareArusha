package com.jwareconsulting.arusha.mvp;

import android.support.v7.app.AppCompatActivity;

import com.jwareconsulting.arusha.model.ChildModel;
import com.jwareconsulting.arusha.model.DirectoryModel;

import java.util.ArrayList;

/**
 * Created by anjulkhanal  on 8/4/16.
 */
public interface DirectoryMvp {

    interface Model {

        void init(AppCompatActivity activity);

        void filterData(String s);

        ChildModel getCatagoryId(int position);
    }

    interface View {

        void populateData(ArrayList<DirectoryModel> datalist);

        void showMsg(String msg);
    }

    interface Presenter {

        void setSearchKeyWord(String s);

        void onClick(int position);

        void onAttach(LandingActivityMvp.Presenter mainPresenter);

        void onDetach();
    }
}
