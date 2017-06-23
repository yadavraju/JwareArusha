package com.jwareconsulting.arusha.ui.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.maps.android.PolyUtil;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.adapter.DirectionAutoCompleteAdapter;
import com.jwareconsulting.arusha.adapter.PlaceAutocompleteAdapter;
import com.jwareconsulting.arusha.common.AppCache;
import com.jwareconsulting.arusha.common.AppLog;
import com.jwareconsulting.arusha.common.AppText;
import com.jwareconsulting.arusha.common.AppUtils;
import com.jwareconsulting.arusha.common.IconManager;
import com.jwareconsulting.arusha.common.MessageHandler;
import com.jwareconsulting.arusha.common.PrefenceUtil;
import com.jwareconsulting.arusha.common.ValidationUtils;
import com.jwareconsulting.arusha.map.MapAbstractFragment;
import com.jwareconsulting.arusha.map.model.Bounds;
import com.jwareconsulting.arusha.model.AutoCompleteModel;
import com.jwareconsulting.arusha.model.MapServiceProviderModel;
import com.jwareconsulting.arusha.model.ServiceProviderModel;
import com.jwareconsulting.arusha.rest.ApiManager;
import com.jwareconsulting.arusha.rest.response.model.GetDirectionApiResponse;
import com.jwareconsulting.arusha.rest.response.model.MapServiceProviderResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anjulkhanal on 9/5/16.
 */
public class DirectionFragment extends MapAbstractFragment {
    public static final String TAG = DirectionFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "catagory_title";
    private View mView;
    private static final LatLngBounds ARUSHA_BOUND = new LatLngBounds(
            new LatLng(-3.465314, 36.882691), new LatLng(-2.712065, 36.383649));
    private ArrayList<ServiceProviderModel> dataList;
    private View infoWindow;
    private DirectionAutoCompleteAdapter autoCompleteAdapter;
    private RelativeLayout rlDistanceInfo;
    private TextView tvInfoTitle, tvDistance, tvDuration;
    private AutoCompleteTextView tvOrigin, tvDestination;
    private ImageView imgCross;
    private Callback<GetDirectionApiResponse> apiCallback;
    private String prefix = " ( ";
    private String postfix = " )";
    private String concatString = ", Arusha, Tanzania";
    private PlaceAutocompleteAdapter mAdapter;
    private ArrayList<String> places;
    private ArrayList<String> cordinateList;
    private String originLocation = "";
    private String destinationLocation = "";
    private ArrayList<MapServiceProviderModel> mapServiceProviderModelArrayList;
    private Typeface font;
    private boolean hasArgument;
    private String catagoryTitle;

    /* public static DirectionFragment plotLocation(String param) {
         DirectionFragment fragment = new DirectionFragment();
         Bundle args = new Bundle();
         args.putString(ARG_PARAM, param);
         fragment.setArguments(args);
         return fragment;
     }
 */

    public static DirectionFragment newInstance(String param) {
        DirectionFragment fragment = new DirectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param);
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

        loadArguments();

/*
        if (getArguments() != null) {
            catagoryId = getArguments().getString(ARG_PARAM);
        }
*/

    }

    @Override
    protected void createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        infoWindow = inflater.inflate(R.layout.layout_info_window, null);
        getActivity().findViewById(R.id.header).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.footer).setVisibility(View.VISIBLE);
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts" + "/" + getString(R.string.proxima_font_regular));
        tvInfoTitle = (TextView) infoWindow.findViewById(R.id.tv_info_title);
        try {
            mView = inflater.inflate(R.layout.fragment_direction, container, false);
        } catch (Exception e) {
            AppLog.printStackStrace(e);
        }
        tvDestination = (AutoCompleteTextView) mView.findViewById(R.id.tv_destination);
        tvOrigin = (AutoCompleteTextView) mView.findViewById(R.id.tv_start_location);
        tvOrigin.setTypeface(font);
        tvDestination.setTypeface(font);
        rlDistanceInfo = (RelativeLayout) mView.findViewById(R.id.rl_distance_info);
        mAdapter = new PlaceAutocompleteAdapter(getActivity(), getmGoogleApiClient(), ARUSHA_BOUND,
                null);
//        autoCompleteAdapter = new DirectionAutoCompleteAdapter(getActivity(), getDatalist());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(), R.layout.row_adapter_autocomplete, MapMenufragment.locationName);
        tvDestination.setAdapter(adapter);
      /*  tvDestination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                destinationLocation = MapMenufragment.locationHashMap.get(tvDestination.getText());
                AppLog.i(TAG, "destination cordinate:" + destinationLocation);

            }
        });
      */
        tvOrigin.setAdapter(adapter);
        /*tvOrigin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                originLocation = MapMenufragment.locationHashMap.get(tvOrigin.getText());

            }

        });
        */
//        rlDistanceInfo.setVisibility(View.GONE);
        tvDistance = (TextView) mView.findViewById(R.id.tv_distance);
        tvDuration = (TextView) mView.findViewById(R.id.tv_time);
        imgCross = (ImageView) mView.findViewById(R.id.iv_cross);
        tvOrigin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 1) {
                    tvOrigin.setError(getString(R.string.text_required));
                } else {
                    tvOrigin.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tvDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 1) {
                    tvDestination.setError(getString(R.string.text_required));
                } else {
                    tvDestination.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        imgCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOrigin.setText("");
                tvDestination.setText("");
            }
        });

        tvOrigin.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (validate()) {
                        callDirectionApi();
                    }
                    return true;
                }
                return false;
            }
        });
        tvDestination.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (validate()) {
                        callDirectionApi();
                    }
                    return true;
                }
                return false;
            }
        });

        apiCallback = new Callback<GetDirectionApiResponse>() {
            @Override
            public void onResponse(Call<GetDirectionApiResponse> call, Response<GetDirectionApiResponse> response) {

                if (response.isSuccessful()) {

                    GetDirectionApiResponse directionApiResponse = response.body();

                    if (directionApiResponse.isSuccess()) {
                        getMap().clear();
                        PolylineOptions polylineOptions = new PolylineOptions();
                        polylineOptions.width(15);
                        polylineOptions.color(getResources().getColor(R.color.polyline));
                        String point = directionApiResponse.getRoutes().get(0).getOverview_polyline().getPoints();
//                        MessageHandler.toast(getActivity(), point);
                        List<LatLng> line = PolyUtil.decode(point);
                        polylineOptions.addAll(line);

                        getMap().addPolyline(polylineOptions);

                        Bounds.NorthEast northEast = directionApiResponse.getRoutes().get(0).getBounds().getNorthEast();
                        Bounds.SouthEast southEast = directionApiResponse.getRoutes().get(0).getBounds().getSouthEast();


                        Double latStart, latEnd;
                        Double lngStart, lngEnd;

                        latStart = directionApiResponse.getRoutes().get(0).getLegs().get(0).getStartLocation().getLat();
                        lngStart = directionApiResponse.getRoutes().get(0).getLegs().get(0).getStartLocation().getLng();

                        latEnd = directionApiResponse.getRoutes().get(0).getLegs().get(0).getEndLocation().getLat();
                        lngEnd = directionApiResponse.getRoutes().get(0).getLegs().get(0).getEndLocation().getLng();

                        String duration = directionApiResponse.getRoutes().get(0).getLegs().get(0).getDuration().getText();
                        String distance = directionApiResponse.getRoutes().get(0).getLegs().get(0).getDistance().getText();

                        tvDistance.setText(prefix + distance + postfix);
                        tvDuration.setText(duration);
                        rlDistanceInfo.setVisibility(View.VISIBLE);

                        if (latStart != null && lngStart != null) {
                            addMarker(latStart, lngStart, R.drawable.ic_marker_current_location,
                                    tvOrigin.getText().toString());
                        }
                        if (lngEnd != null && latEnd != null) {
                            addMarker(latEnd, lngEnd, R.drawable.ic_marker_current_location,
                                    tvDestination.getText().toString());

                        }
                        populatedMarkers();

                        LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        Double lat1 = northEast.getLat();
                        Double lng1 = northEast.getLng();

                        Double lng2 = southEast.getLng();
                        Double lat2 = southEast.getLat();

                        builder.include(new LatLng(lat1, lng1));
                        builder.include(new LatLng(lat2, lng2));


                        LatLngBounds bounds = builder.build();
                        int padding = 100; // offset from edges of the map
                        // in pixels
                        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds,
                                padding);
                        getMap().animateCamera(cu);
//                        rlDistanceInfo.setVisibility(View.GONE);


                    } else {
                        MessageHandler.toast(getActivity(), getResources().getString(R.string.failed));
                    }


                }
            }

            @Override
            public void onFailure(Call<GetDirectionApiResponse> call, Throwable t) {
                AppLog.e(TAG, t.getMessage());
                MessageHandler.toast(getActivity(), getResources().getString(R.string.failed));
            }
        };


    }


    /**
     * @return true if validation succed
     */
    private boolean validate() {
        if (ValidationUtils.isEmpty(tvOrigin.getText().toString())) {

            tvOrigin.setError(getString(R.string.text_required));
            return false;
        }
        if (ValidationUtils.isEmpty(tvDestination.getText().toString())) {
            tvDestination.setError(getString(R.string.text_required));
            return false;
        }

        return true;

    }

    private void callDirectionApi() {
        AppUtils.hideKeyboard(getActivity());
        if (AppUtils.hasInternet(getActivity())) {

            String org = tvOrigin.getText().toString();
            AppLog.d(TAG, "origin text field is:" + org);
            String dest = tvDestination.getText().toString();
            originLocation = MapMenufragment.locationHashMap.get(org);
            destinationLocation = MapMenufragment.locationHashMap.get(dest);
            AppLog.d(TAG, "destination text field is:" + dest);
            AppLog.d(TAG, "orign is :" + originLocation + "  ," + "desination is:" + destinationLocation);
            if (originLocation == null) {
                originLocation = org + concatString;
            }
            if (destinationLocation == null) {
                destinationLocation = dest + originLocation;
            }
            ApiManager.getRoutingDirectionApi(apiCallback, originLocation, destinationLocation);
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
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(arushaCenterLatLog, 1));
        getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(arushaCenterLatLog.latitude, arushaCenterLatLog.longitude), ZOOM_LEVEL + 4));
    }

    @Override
    protected void populatedMarkers() {

        if (!hasArgument) {
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
                        if (!isEmpty) {

//                        Double lat1 = Double.parseDouble(lat);
//                        Double lng1 = Double.parseDouble(lon);
//                        builder.include(new LatLng(lat1, lng1));

//                        addMarker(Double.parseDouble(lat), Double.parseDouble(lon), R.drawable.ic_marker_airport, mapServiceProviderModelArrayList.get(i).getCatagory().get(0).getName());


                            addMarker(Double.parseDouble(lat), Double.parseDouble(lon), IconManager.getResources(mapServiceProviderModelArrayList.get(i).getCatagory().get(0).getName()), mapServiceProviderModelArrayList.get(i).getTitle());
                        } else {
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
        } else {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            ArrayList<ServiceProviderModel> datasList = AppCache.getMapDataCatagoryDataList();
            System.out.print(datasList);
            for (int i = 0; i < datasList.size(); i++) {

                if (datasList.get(i).getLocation() != null) {
                    String lat = datasList.get(i).getLocation().getLat();
                    String lon = datasList.get(i).getLocation().getLon();
                    boolean isEmpty = ValidationUtils.isEmpty(lat) || ValidationUtils.isEmpty(lon);
                    String catagoryName = "null";
                    try {
                        catagoryName = datasList.get(i).getCatagory().getName();
                    } catch (Exception e) {
                        AppLog.printStackStrace(e);
                    }

                    try {
                        if (!isEmpty) {
                            Double lat1 = Double.parseDouble(lat);
                            Double lng1 = Double.parseDouble(lon);
                            builder.include(new LatLng(lat1, lng1));
                            addMarker(lat1, lng1, IconManager.getResources(catagoryName), datasList.get(i).getTitle());
                        }
                        LatLngBounds bounds = builder.build();
                        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds,
                                100);
                        getMap().animateCamera(cu);
                    } catch (Exception e) {
                        AppLog.printStackStrace(e);
                    }


                }
            }

        }

        /////////////////////////////////
    }

    private void loadArguments() {
        if (getArguments() != null) {
            hasArgument = true;
            catagoryTitle = getArguments().getString(ARG_PARAM1);
        }
    }

    public void launchPlaceAutoSuggestion() {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(getActivity());
            startActivityForResult(intent, AppText.PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }

    }

}
