package com.jwareconsulting.arusha.mvp;

import android.support.v7.app.AppCompatActivity;

import com.jwareconsulting.arusha.rest.response.model.SubCategoriesResponseModel;

import java.util.ArrayList;

/**
 * Created by anjulkhanal  on 8/4/16.
 */
public interface DirectorySubcatagoryMvp {

    interface Model {
        void init(AppCompatActivity activity, String catagoryId);

        void filterData(String s);

        String getTitle(int postion);

        void fetchDetail(int position);
    }

    interface View {

        void populateData(ArrayList<SubCategoriesResponseModel.DirectorySubCAtegoriesModel> datalist);

        void showMsg(String msg);
    }

    interface Presenter {

        void setSearchKeyWord(String s);

        void onClick(int position, String subCatId);

        void onAttach(LandingActivityMvp.Presenter mainPresenter);


        void onDetach();
    }
}
