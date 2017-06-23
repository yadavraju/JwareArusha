package com.jwareconsulting.arusha.mvp;

import android.support.v7.app.AppCompatActivity;

import com.jwareconsulting.arusha.model.ExchangeRateModel;
import com.jwareconsulting.arusha.rest.response.model.ExchangeRateResponse;

import java.util.ArrayList;

/**
 * Created by anjulkhanal  on 8/4/16.
 */
public interface ExchangeRateMvp {

    interface Model {


        void init(AppCompatActivity activity);
    }

    interface View {

        void populateData(ArrayList<ExchangeRateModel> datalist, ExchangeRateResponse.AuthorModel authorModel,String date);

        void showMsg(String msg);
    }

    interface Presenter {




    }
}
