package com.jwareconsulting.arusha.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.ui.fragment.CatagoryMapFragment;

/**
 * Created by root on 8/1/16.
 */
public class MapActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView ivBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appbar_main);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ivBack = (ImageView) findViewById(R.id.iv_back_arrow);
        setSupportActionBar(toolbar);
        initDefaultFragment();


    }

    private void initDefaultFragment() {
        CatagoryMapFragment catagoryMapFragment = CatagoryMapFragment.newInstance("66");
//        toolbarTitle.setText(activity.getString(R.string.activity_title_landing));
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction()
                .add(R.id.content_frag, catagoryMapFragment, CatagoryMapFragment.TAG)
//                .addToBackStack(mainFragment.TAG)
                .commit();
    }
}
