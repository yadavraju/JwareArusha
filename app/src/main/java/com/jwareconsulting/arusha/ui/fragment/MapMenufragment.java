package com.jwareconsulting.arusha.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.adapter.MapRecycleViewAdapter;
import com.jwareconsulting.arusha.common.AppCache;
import com.jwareconsulting.arusha.common.AppLog;
import com.jwareconsulting.arusha.common.IconManager;
import com.jwareconsulting.arusha.common.MessageHandler;
import com.jwareconsulting.arusha.common.PrefenceUtil;
import com.jwareconsulting.arusha.common.ValidationUtils;
import com.jwareconsulting.arusha.map.MapAbstractFragment;
import com.jwareconsulting.arusha.model.DirectoryModel;
import com.jwareconsulting.arusha.model.MapServiceProviderModel;
import com.jwareconsulting.arusha.model.ServiceProviderModel;
import com.jwareconsulting.arusha.mvp.DirectorySubcatagoryMvp;
import com.jwareconsulting.arusha.mvp.LandingActivityMvp;
import com.jwareconsulting.arusha.mvp.model.DirectorySubcatagoryMvpModel;
import com.jwareconsulting.arusha.rest.ApiManager;
import com.jwareconsulting.arusha.rest.response.model.DirectoryResponse;
import com.jwareconsulting.arusha.rest.response.model.MapServiceProviderResponse;
import com.jwareconsulting.arusha.rest.response.model.ServiceProviderResponse;
import com.jwareconsulting.arusha.rest.response.model.SubCategoriesResponseModel;
import com.jwareconsulting.arusha.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by anjul khanal on 8/3/16.
 */

public class MapMenufragment extends MapAbstractFragment implements View.OnClickListener, DirectorySubcatagoryMvpModel.Callback {
    public static final String TAG = MapMenufragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "key_lat";
    private static final String ARG_PARAM2 = "key_long";
    private static final String ARG_PARAM3 = "key_discription";
    public static ArrayList<String> locationName = new ArrayList<>();
    public static HashMap<String, String> locationHashMap = new HashMap<>();
    private View infoWindow;
    private View mView;
    private ImageView ivCancel;
    private RecyclerView recyclerView;
    private ArrayList<ServiceProviderModel> serviceProviderModelArrayList;
    private retrofit2.Callback<ServiceProviderResponse> apiCallback;
    private MapRecycleViewAdapter mAdapter;
    private LandingActivityMvp.Presenter mainPresenter;
    private ProgressDialog progressDialog;
    private TextView tvInfoTitle, tvDirection;
    private EditText etSearch;
    private ImageView ivDirection;
    private ArrayList<DirectoryModel> directoryModelDataList;
    private ArrayList<DirectoryModel> filteredDataList;
    private boolean isFiltered;
    private ArrayList<MapServiceProviderModel> mapServiceProviderModelArrayList;
    private HashMap<String, String> hashMap;
    private String CATAGORY_NAME = "Category";
    private boolean hasArgument;
    private String placeLatitude;
    private String placeLongitude;
    private String placeDiscription;
    private Typeface font;
    private DirectorySubcatagoryMvp.Model mvpModel;


    public static MapMenufragment plotLocation(String latitude, String longitude, String title) {
        MapMenufragment fragment = new MapMenufragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, latitude);
        args.putString(ARG_PARAM2, longitude);
        args.putString(ARG_PARAM3, title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts" + "/" + getString(R.string.proxima_font_regular));
        filteredDataList = new ArrayList<DirectoryModel>();
        hashMap = new HashMap<String, String>();
        loadArguments();
    }

    private void loadArguments() {
        if (getArguments() != null) {
            hasArgument = true;
            placeLatitude = getArguments().getString(ARG_PARAM1);
            placeLongitude = getArguments().getString(ARG_PARAM2);
            placeDiscription = getArguments().getString(ARG_PARAM3);
        }
    }


    @Override
    protected View getInflatedView() {
        return mView;
    }

    @Override
    protected void createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        infoWindow = inflater.inflate(R.layout.layout_info_window, null);
        tvInfoTitle = (TextView) infoWindow.findViewById(R.id.tv_info_title);
        try {
            mView = inflater.inflate(R.layout.fragment_map, container, false);
        } catch (Exception e) {
            AppLog.printStackStrace(e);
        }
        //  mView = inflater.inflate(R.layout.fragment_map, container, false);
        tvDirection = (TextView) mView.findViewById(R.id.tv_direction);
        ivCancel = (ImageView) mView.findViewById(R.id.iv_cancel);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch.setText("");
                ivCancel.setVisibility(View.GONE);
            }
        });
        ivDirection = (ImageView) mView.findViewById(R.id.iv_direction);
        etSearch = (EditText) mView.findViewById(R.id.et_search);
        etSearch.setTypeface(font);
        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerView.getVisibility() == View.GONE) {
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
        tvDirection.setOnClickListener(this);
        ivDirection.setOnClickListener(this);
        initaliseView(mView);
    }

    private void filterData(String s) {
        AppLog.v(TAG, "typed string :s" + s);
        filteredDataList.clear();
        if (directoryModelDataList != null) {
            for (int i = 0; i < directoryModelDataList.size(); i++) {
                if (directoryModelDataList.get(i).getName().toLowerCase().contains(s.toLowerCase())) {
                    filteredDataList.add(directoryModelDataList.get(i));
                }
            }

            for (int j = 0; j < mapServiceProviderModelArrayList.size(); j++) {
                String catagoryName = mapServiceProviderModelArrayList.get(j).getCatagory().get(0).getName();
                String title = mapServiceProviderModelArrayList.get(j).getTitle();
                if (title.toLowerCase().contains(s.toLowerCase())) {
                    DirectoryModel directoryModel = new DirectoryModel();
                    directoryModel.setName(title);
                    directoryModel.setThumnailImage(hashMap.get(catagoryName));
                    directoryModel.setCatagory(true);
                    directoryModel.setPosition(j);
                    directoryModel.setTid("66");
                    filteredDataList.add(directoryModel);
                }
            }
//            mAdapter.populateView(filteredDataList);
            if (s.trim().length() < 1) {
                setFilterFlag(false);
                mAdapter.populateView(directoryModelDataList);
                AppLog.d(TAG, "Validation failed");
            } else {
                setFilterFlag(true);
                AppLog.d(TAG, "validation not failed");
                mAdapter.populateView(filteredDataList);
            }

          /* if (!ValidationUtils.isEmpty(s)) {
                setFilterFlag(false);
                mAdapter.populateView(directoryModelDataList);
                AppLog.d(TAG,"Validation failed");
            } else {
                setFilterFlag(true);
                mAdapter.populateView(filteredDataList);
            }
*/
        } else {
            AppLog.v(TAG, "datalist is null");
        }

    }

    @Override
    protected void process() {
        if (PrefenceUtil.hasDirectoryData(getActivity())) {
            String jsonString = PrefenceUtil.getDirectoryData(getActivity());
            DirectoryResponse directoryResponse = new Gson().fromJson(jsonString, DirectoryResponse.class);
            directoryModelDataList = directoryResponse.getDataList();
            setFilterFlag(false);
            mAdapter.populateView(directoryModelDataList);
            for (int i = 0; i < directoryModelDataList.size(); i++) {
                hashMap.put(directoryModelDataList.get(i).getName(), directoryModelDataList.get(i).getThumnailImage());
            }
        } else {
            MessageHandler.toast(getActivity(), getActivity().getResources().getString(R.string.no_data));
        }

    }

    @Override
    protected int getFragmentId() {
        return R.id.map;
    }


    protected void initaliseView(View view) {
        getActivity().findViewById(R.id.header).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.footer).setVisibility(View.VISIBLE);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleview_map);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MapRecycleViewAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (recyclerView.getVisibility() == View.GONE) {
                    recyclerView.setVisibility(View.VISIBLE);
                }
                if (s.length() > 0) {


                    if (ivCancel.getVisibility() == View.GONE) {
                        ivCancel.setVisibility(View.VISIBLE);
                    }
                } else {
                    ivCancel.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filterData(s.toString());

            }
        });
        mAdapter.setOnItemClickListner(new MapRecycleViewAdapter.OnItemClickListner() {

                                           @Override
                                           public void onClick(DirectoryModel model) {

                                               if (mainPresenter != null) {
                                                   recyclerView.setVisibility(View.GONE);

                                                   if (model.isCatagory()) {
                                                       if (model.hasChild()) {
                                                           mvpModel = null;
                                                           progressDialog.show();
                                                           mvpModel = new DirectorySubcatagoryMvpModel(MapMenufragment.this);
                                                           mvpModel.init((AppCompatActivity) getActivity(), model.getTid());
                                                       } else {
                                                           progressDialog.show();
                                                           ApiManager.getServiceProviderByCatagory(getActivity(), apiCallback, model.getTid());
                                                           CATAGORY_NAME = model.getName(); //todo think about it as well
                                                       }
                                                   } else {
                                                       AppLog.v(TAG, "not a catagory");
                                                       MapServiceProviderModel dataModel = mapServiceProviderModelArrayList.get(model.getPosition());
                                                       if (dataModel.getLocation() != null) {
                                                           String lat = dataModel.getLocation().getLat();
                                                           String lon = dataModel.getLocation().getLon();
                                                           boolean isEmpty = ValidationUtils.isEmpty(lat) || ValidationUtils.isEmpty(lon);
                                                           if (!isEmpty) {
                                                               getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)), ZOOM_LEVEL + 4));
                                                           }
                                                       }
                                                   }


                                               }
                                           }

                                       }

        );

    }

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
        if (!hasArgument) {
            getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(arushaCenterLatLog, 1));
            getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(arushaCenterLatLog.latitude, arushaCenterLatLog.longitude), ZOOM_LEVEL + 4));

        } else {
            double lat = Double.parseDouble(placeLatitude);
            double longitude = Double.parseDouble(placeLongitude);
            getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, longitude), 1));
            getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, longitude), ZOOM_LEVEL + 4));
            addMarker(lat, longitude, R.drawable.ic_marker_current_location, placeDiscription).showInfoWindow();

        }
    }

    @Override
    protected void populatedMarkers() {
        locationName.clear();
        if (PrefenceUtil.hasMapServiceProvider(getActivity())) {
//            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            String jsonString = PrefenceUtil.getMapServiceProvider(getActivity());
            AppLog.v(TAG, "Json string is :" + jsonString);
            MapServiceProviderResponse serviceProviderResponse = new Gson().fromJson(jsonString, MapServiceProviderResponse.class);
            mapServiceProviderModelArrayList = serviceProviderResponse.getDataList();
            for (int i = 0; i < mapServiceProviderModelArrayList.size(); i++) {
                if (mapServiceProviderModelArrayList.get(i).getLocation() != null) {
                    String lat = mapServiceProviderModelArrayList.get(i).getLocation().getLat();
                    String lon = mapServiceProviderModelArrayList.get(i).getLocation().getLon();
                    boolean isEmpty = ValidationUtils.isEmpty(lat) || ValidationUtils.isEmpty(lon);
                    locationHashMap.put(mapServiceProviderModelArrayList.get(i).getTitle(), lat + "," + lon);
//                    locationCordinate.add(lat + "," + lon);
                    locationName.add(mapServiceProviderModelArrayList.get(i).getTitle());
                    if (!isEmpty) {

//                        Double lat1 = Double.parseDouble(lat);
//                        Double lng1 = Double.parseDouble(lon);
//                        builder.include(new LatLng(lat1, lng1));

//                        addMarker(Double.parseDouble(lat), Double.parseDouble(lon), R.drawable.ic_marker_airport, mapServiceProviderModelArrayList.get(i).getCatagory().get(0).getName());


                        addMarker(Double.parseDouble(lat), Double.parseDouble(lon), IconManager.getResources(mapServiceProviderModelArrayList.get(i).getCatagory().get(0).getName()), mapServiceProviderModelArrayList.get(i).getTitle());
                    } else {
//                        locationCordinate.add(lat + "," + lon);
                    }

                } else {
                    AppLog.e(TAG, "invalid location format for " + mapServiceProviderModelArrayList.get(i).getTitle());
                }
/*
                LatLngBounds bounds = builder.build();
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds,
                        100);
                getMap().moveCamera(cu);
                getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(bounds.getCenter().latitude, bounds.getCenter().longitude), ZOOM_LEVEL));
*/
            }
        } else {
            AppLog.d(TAG, "doesnot have  serviceprovider");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        mainPresenter = activity.getPresenter();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(context.getResources().getString(R.string.loading));
        apiCallback = new retrofit2.Callback<ServiceProviderResponse>() {

            @Override
            public void onResponse(Call<ServiceProviderResponse> call, Response<ServiceProviderResponse> response) {

                if (response.code() == 200) {
                    ServiceProviderResponse serviceProviderResponse = response.body();
                    if (serviceProviderResponse.isSuccess()) {
                        serviceProviderModelArrayList = serviceProviderResponse.getDataList();
                        AppCache.setMapDataCatagoryDataList(serviceProviderModelArrayList);
                        progressDialog.dismiss();
                        mainPresenter.replaceFragment(CatagoryMapFragment.newInstance("66"), CatagoryMapFragment.TAG, CATAGORY_NAME);
//                        getActivity().startActivity(new Intent(getActivity(), MapActivity.class));
                    } else {
                        MessageHandler.toast(getActivity(), serviceProviderResponse.getMessage());
                    }

                } else if (response.code() == 500) {
                    MessageHandler.toast(getActivity(), getResources().getString(R.string.server_error));
                } else {
                    MessageHandler.toast(getActivity(), getResources().getString(R.string.error) + response.code());
                }

            }

            @Override
            public void onFailure(Call<ServiceProviderResponse> call, Throwable t) {
                MessageHandler.toast(getActivity(), t.getMessage());
            }
        };

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainPresenter = null;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {


        return true;
    }

    @Override
    public void onMapClick() {

        if (recyclerView.getVisibility() == View.VISIBLE) {
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_direction:
            case R.id.iv_direction:
                if (mainPresenter != null) {
                    mainPresenter.replaceFragment(new DirectionFragment(), DirectionFragment.TAG, "Direction");
                }
                break;
        }

    }

    private void setFilterFlag(boolean value) {
        isFiltered = value;
    }

    @Override
    public void OnDataArrived(ArrayList<SubCategoriesResponseModel.DirectorySubCAtegoriesModel> dataList) {


        if (dataList != null) {

            if (dataList.size() > 0) {
                String catagoryId = dataList.get(0).getSubCatId();
                ApiManager.getServiceProviderByCatagoryForShowAllList(getActivity(), apiCallback, catagoryId);

            }
        }

    }

    @Override
    public void OnFailure(String msg) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        MessageHandler.toast(getActivity(), msg);
    }
}

