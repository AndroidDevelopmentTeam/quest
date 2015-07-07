package com.android.androiddevteam.quest.google_map;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 06.07.15
 */

public class GoogleMapManager
        implements
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMapLongClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final int DEFAULT_ZOOM = 20;
    private static final int DEFAULT_BEARING = 0;
    private static final int DEFAULT_ADDRESS_INDEX = 0;
    private static final long LOCATION_REFRESH_TIME = 30000;
    private static final long LOCATION_FASTER_REFRESH_TIME = LOCATION_REFRESH_TIME / 2;
    private static final float LOCATION_MIN_DISTANCE = 10;

    private GoogleMap googleMap;
    private Context context;
    private LatLng myCurrentPosition;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private boolean isConnected = false;

    public GoogleMapManager(GoogleMap googleMap, Context context) {
        this.googleMap = googleMap;
        this.context = context;
        buildGoogleApiClient();
        customizeGoogleMap();
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
    }

    protected void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(LOCATION_REFRESH_TIME);
        locationRequest.setFastestInterval(LOCATION_FASTER_REFRESH_TIME);

        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        connectGoogleApiClient();
    }

    private void connectGoogleApiClient(){
        if (!isConnected){
            googleApiClient.connect();
        }
    }

    public void disconnectGoogleApiClient(){
        if (isConnected){
            googleApiClient.disconnect();
        }
    }

    private void customizeGoogleMap(){
        googleMap.setMyLocationEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.setIndoorEnabled(true);

        setListenersToGoogleMap();

        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setMyLocationButtonEnabled(false);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
    }

    private void setListenersToGoogleMap() {
        googleMap.setOnMarkerDragListener(this);
        googleMap.setOnMapClickListener(this);
        googleMap.setOnMapLongClickListener(this);
    }

    public void moveToMyPosition(){
        if (myCurrentPosition != null){
            moveCameraToPosition(myCurrentPosition);
        }
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
            addresses = geoCoder.getFromLocationName(searchLocation, DEFAULT_ZOOM);
            if (addresses.size() > DEFAULT_ADDRESS_INDEX) {
                LatLng addressPosition
                        = new LatLng(addresses.get(DEFAULT_ADDRESS_INDEX).getLatitude(),
                        addresses.get(DEFAULT_ADDRESS_INDEX).getLongitude());
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(addressPosition, DEFAULT_ZOOM));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void currentLocationChanged(LatLng myNewPosition){
        myCurrentPosition = myNewPosition;
    }

    //////////MarkerDragListener
    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    //////////MapClickListener
    @Override
    public void onMapClick(LatLng latLng) {

    }

    //////////MapLongClickListener
    @Override
    public void onMapLongClick(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(latLng.toString())
                .draggable(true);
    }

    //////////GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener
    @Override
    public void onConnected(Bundle bundle) {
        isConnected = true;
        if (myCurrentPosition == null){
            Location lastKnownPosition = LocationServices.FusedLocationApi.getLastLocation(
                    googleApiClient);

            if (lastKnownPosition != null) {
                currentLocationChanged(
                        new LatLng(lastKnownPosition.getLatitude(), lastKnownPosition.getLongitude()));
            }
            moveToMyPosition();
        }
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        connectGoogleApiClient();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        isConnected = false;
        connectGoogleApiClient();
    }

    public void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    public void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        myCurrentPosition = new LatLng(location.getLatitude(), location.getLongitude());
    }
}
