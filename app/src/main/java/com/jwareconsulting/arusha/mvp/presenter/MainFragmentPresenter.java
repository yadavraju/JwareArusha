package com.jwareconsulting.arusha.mvp.presenter;

import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.mvp.MainFragmentMvp;
import com.jwareconsulting.arusha.ui.fragment.MainFragment;

/**
 * Created by anjulkhanal  on 8/3/16.
 */
public class MainFragmentPresenter implements MainFragmentMvp.Presenter {
    private LandingActivityMvp.Presenter mainPresenter;
//    private LandingActivityMvp.View mvpView;

    public MainFragmentPresenter(MainFragment mainFragment) {
//    mvpView= (LandingActivityMvp.View) mainFragment;
    }

    @Override
    public void OnClick(int id) {
        if (mainPresenter != null) {
            mainPresenter.OnItemClick(id);
        }
    }

    @Override
    public void onAttach(LandingActivityMvp.Presenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    @Override
    public void detach() {
        mainPresenter = null;
    }
}
