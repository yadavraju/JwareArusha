package com.jwareconsulting.arusha.mvp;

import android.support.v7.app.AppCompatActivity;

import com.jwareconsulting.arusha.model.ServiceProviderModel;

import java.util.ArrayList;

/**
 * Created by anjulkhanal  on 8/4/16.
 */
public interface DirectoryDetailMvp {

    interface Model {
        void init(AppCompatActivity activity, String catagoryId,int position);

        void filterData(String s);
    }

    interface View {

        void populateData(ArrayList<ServiceProviderModel> datalist);

        void showMsg(String msg);
    }

    interface Presenter {

        void setSearchKeyWord(String s);

        void onClick(int intentType, String data);

        void onAttach(LandingActivityMvp.Presenter mainPresenter);

        void onDetach();

        void openInMap(String latitude, String longitude, String title);
    }
}
