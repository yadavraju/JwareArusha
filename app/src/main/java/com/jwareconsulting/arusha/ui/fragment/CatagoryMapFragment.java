package com.jwareconsulting.arusha.ui.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.adapter.MapRecycleViewAdapter;
import com.jwareconsulting.arusha.common.AppCache;
import com.jwareconsulting.arusha.common.AppLog;
import com.jwareconsulting.arusha.common.IconManager;
import com.jwareconsulting.arusha.common.MessageHandler;
import com.jwareconsulting.arusha.common.ValidationUtils;
import com.jwareconsulting.arusha.map.MapAbstractFragment;
import com.jwareconsulting.arusha.model.DirectoryModel;
import com.jwareconsulting.arusha.model.ServiceProviderModel;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.ui.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by anjulkhanal on 9/5/16.
 */
public class CatagoryMapFragment extends MapAbstractFragment implements View.OnClickListener {
    public static final String TAG = CatagoryMapFragment.class.getSimpleName();
    private View mView;
    private ImageView ivCancel;
    private static String ARG_PARAM = "id";
    private String catagoryId;
    private ArrayList<ServiceProviderModel> dataList;
    private View infoWindow;
    private TextView tvInfoTitle,tvDirection;
    private ImageView ivDirection;
    private EditText etSearch;
    private RecyclerView recyclerView;
    private MapRecycleViewAdapter mAdapter;
    private String catagoryName;
    private Typeface font;
    private LandingActivityMvp.Presenter mainPresenter;

    public static CatagoryMapFragment newInstance(String param) {
        CatagoryMapFragment fragment = new CatagoryMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getInflatedView() {
        return mView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            catagoryId = getArguments().getString(ARG_PARAM);
        }

    }

    @Override
    protected void createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        infoWindow = inflater.inflate(R.layout.layout_info_window, null);
        getActivity().findViewById(R.id.header).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.footer).setVisibility(View.VISIBLE);
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts"+"/"+getString(R.string.proxima_font_regular));
        tvInfoTitle = (TextView) infoWindow.findViewById(R.id.tv_info_title);
        try {
            mView = inflater.inflate(R.layout.fragment_map_catagory, container, false);
        } catch (Exception e) {

        }
        tvDirection = (TextView) mView.findViewById(R.id.tv_direction);
        tvDirection.setOnClickListener(this);
        ivDirection = (ImageView) mView.findViewById(R.id.iv_direction);
        ivDirection.setOnClickListener(this);
        etSearch = (EditText) mView.findViewById(R.id.et_search);
        etSearch.setTypeface(font);

        initaliseView(mView);
    }

    private void initaliseView(View view) {
        ivCancel = (ImageView) view.findViewById(R.id.iv_cancel);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch.setText("");
                recyclerView.setVisibility(View.GONE);
                ivCancel.setVisibility(View.GONE);
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleview_map);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MapRecycleViewAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setVisibility(View.GONE);
        mAdapter.setOnItemClickListner(new MapRecycleViewAdapter.OnItemClickListner() {
            @Override
            public void onClick(DirectoryModel model) {
//                AppUtils.hideKeyboard(getActivity());
                recyclerView.setVisibility(View.GONE);
                etSearch.setText("");
                int position = model.getPosition();
                ServiceProviderModel.Location location = dataList.get(position).getLocation();
                if (location != null) {
                    String lat = location.getLat();
                    String lon = location.getLon();
                    boolean isEmpty = ValidationUtils.isEmpty(lat) || ValidationUtils.isEmpty(lon);
                    if (!isEmpty) {
                        getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)), ZOOM_LEVEL + 4));
                    }
                } else {
                    MessageHandler.toast(getActivity(), getResources().getString(R.string.no_location));
                }


            }
        });


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (recyclerView.getVisibility() == View.GONE) {
                    recyclerView.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0) {
                    if (ivCancel.getVisibility() == View.GONE) {
                        ivCancel.setVisibility(View.VISIBLE);
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterData(s.toString());

            }
        });
       /* etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Toast.makeText(getActivity(), "Out of focus", Toast.LENGTH_SHORT).show();
                }

            }
        });*/


    }

    private void filterData(String s) {
        ArrayList<DirectoryModel> directoryList = new ArrayList<>();
       try{
           if (dataList != null) {
               if (dataList.size() > 0) {
                   for (int i = 0; i < dataList.size(); i++) {
                       String title = dataList.get(i).getTitle();
                       if (title.toLowerCase().contains(s.toLowerCase())) {
                           DirectoryModel directoryModel = new DirectoryModel();
                           directoryModel.setName(title);
                           directoryModel.setPosition(i);
                           directoryModel.setIconResources(IconManager.getResources(catagoryName));
                           directoryList.add(directoryModel);
                       }

                   }
                   mAdapter.populateView(directoryList);

               }


           }
       }catch (Exception e){
           AppLog.printStackStrace(e);
       }



    }

    @Override
    protected void process() {

    }

    @Override
    protected int getFragmentId() {
        return R.id.map_catagory;
    }

   /* protected void initaliseView(View view) {
        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.recycleview_map);
        recyclerView.setVisibility(View.GONE);

    }*/

    @Override
    protected void setInfoWindowAdapter() {
        getMap().setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                tvInfoTitle.setText(marker.getTitle());
                return infoWindow;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });

    }

    @Override
    protected void performMapAnimation() {

    }

    @Override
    protected void populatedMarkers() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        dataList = AppCache.getMapDataCatagoryDataList();
        int size = dataList.size();

        for (int i = 0; i < size; i++) {

            if (dataList.get(i).getLocation() != null) {
                String lat = dataList.get(i).getLocation().getLat();
                String lon = dataList.get(i).getLocation().getLon();
                boolean isEmpty = ValidationUtils.isEmpty(lat) || ValidationUtils.isEmpty(lon);
                catagoryName = "null";
                try {
                    catagoryName = dataList.get(i).getCatagory().getName();
                } catch (Exception e) {
                    AppLog.printStackStrace(e);
                }

                if (!isEmpty) {
                    Double lat1 = Double.parseDouble(lat);
                    Double lng1 = Double.parseDouble(lon);
                    builder.include(new LatLng(lat1, lng1));
                    addMarker(Double.parseDouble(lat), Double.parseDouble(lon), IconManager.getResources(catagoryName), dataList.get(i).getTitle());
                }
                LatLngBounds bounds = builder.build();
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds,
                        100);
                getMap().animateCamera(cu);

            }
        }

//        for (int i = 0; )
    }

//    private void addMarker(Double latitude, Double longitude, int iconResources, String title) {
//        getMap().addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(iconResources))
//                .position(new LatLng(latitude, longitude))
//                .title(title));
//
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_direction:
            case R.id.iv_direction:
                if (mainPresenter != null) {
                    mainPresenter.replaceFragment(DirectionFragment.newInstance(catagoryName), DirectionFragment.TAG, "Direction");
                }
                break;
        }

    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        mainPresenter = activity.getPresenter();
    }

    @Override
    public void onMapClick() {

        if (recyclerView.getVisibility() == View.VISIBLE) {
            recyclerView.setVisibility(View.GONE);
        }
    }
}
