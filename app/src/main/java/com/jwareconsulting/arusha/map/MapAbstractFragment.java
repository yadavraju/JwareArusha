package com.jwareconsulting.arusha.map;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.common.AppLog;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by anjul khanal on 5/5/16.
 */

public abstract class MapAbstractFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    public float ZOOM_LEVEL = 13;
    private String mCurrentAddress;
    public static String TAG = MapAbstractFragment.class.getSimpleName();
    private Marker mCurrentLocationMarker;
    private static Location LOCATION;
    public Marker currentLocationMarker;
    private View view;
    public LatLng arushaCenterLatLog;
    public LatLng arushaCenterLatLog1;
    public LatLng arushaCenterLatLog2;
//    private FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//
        arushaCenterLatLog = new LatLng(-3.375448300000, 36.699072700000);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
//                .enableAutoManage(getActivity(), 0 /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .build();
        mGoogleApiClient.connect();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
   /*if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_map, container, false);
        } catch (InflateException e) {
        }*/
        createView(inflater, container, savedInstanceState);
        initMap();
        process();
        View inflatedView = getInflatedView();
       /* fab = (FloatingActionButton) inflatedView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLocation.getLatitude(), mLocation.getLongitude()), ZOOM_LEVEL));
            }
        });*/

        return inflatedView;
    }

    protected abstract View getInflatedView();

    protected abstract void createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void process();


    private void initMap() {
        SupportMapFragment fragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(getFragmentId()));
        if (fragment != null) {
            fragment.getMapAsync(this);
        }

    }

    protected abstract int getFragmentId();


    @Override
    public void onResume() {
        super.onResume();
        initMap();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;
        setUpMap();
        //startDemo();
    }

    private void setUpMap() {
        mMap.getUiSettings().setZoomGesturesEnabled(true);
//        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);

//        mMap.setOnMarkerClickListener(this);
//        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setOnMapClickListener(this);
    }


    protected abstract void setInfoWindowAdapter();

    protected GoogleMap getMap() {
        return mMap;
    }

    protected String getCurrentAddress() {
        try {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(mLocation.getLatitude(), mLocation.getLongitude(), 1);
            StringBuilder sb = new StringBuilder();
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                sb.append(address.getLocality()).append("\n");
                sb.append(address.getCountryName());
            }
            mCurrentAddress = sb.toString();
        } catch (IOException e) {
            AppLog.printStackStrace(e);
            AppLog.e(TAG, e.toString());
        }
        return mCurrentAddress;
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLocation != null) {
           /* LatLngBounds AUSTRALIA = new LatLngBounds(
                    new LatLng(-44, 113), new LatLng(-10, 154));
           mMap.moveCamera(CameraUpdateFactory.newLatLngZoom());*/
//
//            mMap.setOnMarkerClickListener(this);
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(arushaCenterLatLog, 1));
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(arushaCenterLatLog.latitude, arushaCenterLatLog.longitude), ZOOM_LEVEL));
            setInfoWindowAdapter();
            createCurrentLocation(mLocation.getLatitude(), mLocation.getLongitude());
            performMapAnimation();
            populatedMarkers();
//            setInfoWindowClickeListner();
        } else {
//            fab.setImageResource(R.drawable.ic_gps_disable);
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.disable_location), Toast.LENGTH_SHORT).show();
            setInfoWindowAdapter();
//            createCurrentLocation(mLocation.getLatitude(), mLocation.getLongitude());
            performMapAnimation();
            populatedMarkers();
        }
    }

    protected abstract void performMapAnimation();

    protected abstract void populatedMarkers();


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    /*  @Override
      public boolean onMarkerClick(Marker marker) {

          if (marker.equals(currentLocationMarker)) {
              marker.hideInfoWindow();
          } else {
  //            marker.showInfoWindow();
              Projection projection = mMap.getProjection();
              LatLng markerLocation = marker.getPosition();
              Point mappoint = projection.toScreenLocation(markerLocation);
              mappoint.set(mappoint.x, mappoint.y);
       //       mMap.animateCamera(CameraUpdateFactory.newLatLng(mMap.getProjection().fromScreenLocation(mappoint)));
          }
       return   onMarkerClicked();
s



      }
    protected abstract boolean onMarkerClicked();
  */

    protected void createCurrentLocation(double latitude, double longitude) {
        currentLocationMarker = getMap().addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_current_location))
                .position(new LatLng(latitude, longitude)));
        currentLocationMarker.setTitle(getResources().getString(R.string.you_are_here));
        //      onCurrentLocationMarkerCreated(currentLocationMarker);
    }

    //  protected abstract void onCurrentLocationMarkerCreated(Marker currentLocationMarker);

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

   /* @Override
    public boolean onMarkerClick(Marker marker) {

        return false;
    }*/

    /**
     * not used till now ,may be needed in future
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
      /*  if (marker.equals(currentLocationMarker)) {
            currentLocationMarker.hideInfoWindow();
        }*/

        marker.showInfoWindow();

        return onClickedMarker(marker);
    }

    private boolean onClickedMarker(Marker marker) {
        return true;
    }


    public Marker addMarker(Double latitude, Double longitude, int iconResources, String title) {
        Marker marker = getMap().addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(iconResources))
                .position(new LatLng(latitude, longitude))
                .title(title));
        marker.showInfoWindow();
        return marker;
    }

    public void showPolyLines(PolylineOptions polylineOptions) {


    }

    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        onMapClick();
    }

    /*@Override
    protected void setInfoWindowClickeListner() {

    }*/

    public void onMapClick() {

    }
}