package com.jwareconsulting.arusha.mvp;

/**
 * Created by anjul khanal on 8/3/16.
 */
public interface MainFragmentMvp {


    interface Model {


    }

    interface View {

        void checkDefaulRadioButtion(String languageCode);

    }

    interface Presenter {

        void OnClick(int id);

        void onAttach(LandingActivityMvp.Presenter mainPresenter);

        void detach();
    }
}
