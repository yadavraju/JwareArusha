package com.jwareconsulting.arusha.mvp;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.jwareconsulting.arusha.ui.view.RadioGroupPlus;

/**
 * Created by anjulkhanal  on 8/3/16.
 */
public interface LandingActivityMvp {

    interface Model {


    }

    interface View {
        void changeAds(String title);

        void closeDrawer();
    }

    interface Presenter {

        void OnItemClick(int id);

        void setNavigationView(NavigationView navigationView);

        void replaceFragment(Fragment fragment, String tag, String subtitle);

        void replaceFragment(Fragment fragment, String tag, String subtitle, boolean value);

        void loadFragment(Toolbar toolbar);

        void setTitle(String title);

        void onBackStackChanged(String name, ImageView imageView);

        void onOrderClicked(RadioGroupPlus view);
    }
}
