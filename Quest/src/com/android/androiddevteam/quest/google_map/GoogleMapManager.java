package com.android.androiddevteam.quest.google_map;

import android.app.Activity;
import android.content.Context;
import android.location.*;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 06.07.15
 */

public class GoogleMapManager {

    private static final int DEFAULT_ZOOM = 16;
    private static final int DEFAULT_BEARING = 0;

    private GoogleMap googleMap;
    private Context context;

    public GoogleMapManager(GoogleMap googleMap, Context context) {
        this.googleMap = googleMap;
        this.context = context;
        customizeGoogleMap();
    }

    private void customizeGoogleMap(){
        googleMap.setMyLocationEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setMyLocationEnabled(true);
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
    }

    private void addMarker(MarkerOptions markerOptions){
        googleMap.addMarker(markerOptions);
    }

    private void moveCameraToPosition(LatLng position){
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)
                .zoom(DEFAULT_ZOOM)
                .bearing(DEFAULT_BEARING)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void searchLocation(String searchLocation) {
        Geocoder geoCoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geoCoder.getFromLocationName(searchLocation, 1);
            if (addresses.size() > 0) {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude()), 16));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToMyPosition(){
        LocationManager locationManager = (LocationManager) context.getSystemService(Activity.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location myLocation = locationManager.getLastKnownLocation(provider);
        double latitude = myLocation.getLatitude();
        double longitude = myLocation.getLongitude();
        LatLng myPosition = new LatLng(latitude, longitude);

        moveCameraToPosition(myPosition);
    }
}
