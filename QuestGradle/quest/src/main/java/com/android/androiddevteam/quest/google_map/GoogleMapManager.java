package com.android.androiddevteam.quest.google_map;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.activity.ActNewQuest;
import com.android.androiddevteam.quest.dialogfragments.dialog_ok.DlgFragTitle;
import com.android.androiddevteam.quest.structure.PointItem;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.*;
import com.google.maps.android.ui.IconGenerator;

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
        LocationListener, GoogleMap.OnMarkerClickListener {

    private static final int DEFAULT_ZOOM = 20;
    private static final int DEFAULT_BEARING = 0;
    private static final int DEFAULT_ADDRESS_INDEX = 0;
    private static final long LOCATION_REFRESH_TIME = 30000;
    private static final long LOCATION_FASTER_REFRESH_TIME = LOCATION_REFRESH_TIME / 2;
    private static final float LOCATION_MIN_DISTANCE = 10;
    private static final int MAP_TYPE_OFFSET = 1;
    private static final int ICON_GENERATOR_STYLE_OFFSET = 1;
    private static final int MIN_COUNT_FOR_SHOW_SLIDE = 2;

    private static final int START_POLYLINE_OFFSET = 2;
    private static final int FINISH_POLYLINE_OFFSET = 1;

    private static final int SIZE_OF_DISTANCE_CONTAINER = 1;
    private static final int INDEX_OF_DISTANCE_CONTAINER = 0;

    public static final String METERS = " m.";

    private static final int POINTS_LIST_ID = R.id.slidingDrawer_pointsList;

    private static final String DEF_MARKER_NAME = "Point";

    private GoogleMap googleMap;
    private Context actNQContext;
    private LatLng myCurrentPosition;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private IconGenerator iconGenerator;


    private boolean isConnected = false;

    public GoogleMapManager(GoogleMap googleMap, Context actNQContext) {
        this.googleMap = googleMap;
        this.actNQContext = actNQContext;

        buildGoogleApiClient();
        customizeGoogleMap();
        prepareIconGenerator();
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(actNQContext)
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

    private void customizeGoogleMap() {
        googleMap.setMyLocationEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.setIndoorEnabled(true);

        setListenersToGoogleMap();

        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setMyLocationButtonEnabled(false);
        uiSettings.setMapToolbarEnabled(true);
//        uiSettings.setZoomControlsEnabled(true);
    }

    private void setListenersToGoogleMap() {
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMarkerDragListener(this);
        googleMap.setOnMapClickListener(this);
        googleMap.setOnMapLongClickListener(this);
    }

    private void prepareIconGenerator() {
        iconGenerator = new IconGenerator(actNQContext);
//        iconGenerator.setColor(Color.BLUE);
//        iconGenerator.setStyle(IconGenerator.STYLE_BLUE);
    }

    public void moveToMyPosition(){
        if (myCurrentPosition != null){
            moveCameraToPosition(myCurrentPosition);
        }
    }

    private void addMarker(MarkerOptions markerOptions){
        googleMap.addMarker(markerOptions);
    }

    public void switchMapType(){
        if (googleMap.getMapType() >= GoogleMap.MAP_TYPE_HYBRID){
            googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        } else {
            googleMap.setMapType(googleMap.getMapType() + MAP_TYPE_OFFSET);
        }
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
        Geocoder geoCoder = new Geocoder(actNQContext, Locale.getDefault());
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

    //////////MarkerClickListener
    @Override
    public boolean onMarkerClick(Marker marker) {
        DlgFragTitle dlgFragTitle = new DlgFragTitle();
        dlgFragTitle.setMessage(marker.getTitle());
        dlgFragTitle
                .show(((ActNewQuest) actNQContext).getSupportFragmentManager(), dlgFragTitle.getDialogTag());
        return true;
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
        DlgFragTitle fragTitle = new DlgFragTitle();
        fragTitle.setMessage("hi");
        fragTitle.show(((FragmentActivity) actNQContext).getSupportFragmentManager(), fragTitle.getDialogTag());
    }

    //////////MapLongClickListener
    @Override
    public void onMapLongClick(LatLng latLng) {
        int pointsListSize = ((ActNewQuest) actNQContext).getPoints().size();
        setStyleToIconGenerator();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions
                .position(latLng)
                .title("" + pointsListSize)
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon("" + pointsListSize)));

        PointItem pointItem = new PointItem(markerOptions, IconGenerator.getStyleColor(iconGenerator.getStyle()));

        ((ActNewQuest) actNQContext).addPointToList(pointItem);

        pointsListSize = ((ActNewQuest) actNQContext).getPoints().size();

        addMarker(markerOptions);

        if (pointsListSize >= MIN_COUNT_FOR_SHOW_SLIDE){

            PolylineOptions polylineOptions = new PolylineOptions();
            polylineOptions.add(((ActNewQuest) actNQContext).getPoint(pointsListSize - START_POLYLINE_OFFSET).getPointPosition());
            polylineOptions.add(((ActNewQuest) actNQContext).getPoint(pointsListSize - FINISH_POLYLINE_OFFSET).getPointPosition());
            polylineOptions.color(IconGenerator.getStyleColor(iconGenerator.getStyle()));
            googleMap.addPolyline(polylineOptions);

            ((ActNewQuest) actNQContext).showHidePointsList(true);
        }
    }

    private void setStyleToIconGenerator(){
        if (iconGenerator.getStyle() >= IconGenerator.STYLE_ORANGE){
            iconGenerator.setStyle(IconGenerator.STYLE_RED);
        } else if(iconGenerator.getStyle() == IconGenerator.STYLE_DEFAULT
                || iconGenerator.getStyle() == IconGenerator.STYLE_WHITE){
            iconGenerator.setStyle(IconGenerator.STYLE_RED);
        } else {
            iconGenerator.setStyle(iconGenerator.getStyle() + ICON_GENERATOR_STYLE_OFFSET);
        }
    }

    public static String distanceBetweenPointsString(LatLng point1, LatLng point2){
        float[] results = new float[SIZE_OF_DISTANCE_CONTAINER];
        Location.distanceBetween(point1.latitude,
                point1.longitude,
                point2.latitude,
                point2.longitude,
                results);

        return Float.valueOf(results[INDEX_OF_DISTANCE_CONTAINER]).intValue() + METERS;
    }

    public static int distanceBetweenPointsInt(LatLng point1, LatLng point2){
        float[] results = new float[SIZE_OF_DISTANCE_CONTAINER];
        Location.distanceBetween(point1.latitude,
                point1.longitude,
                point2.latitude,
                point2.longitude,
                results);

        return Float.valueOf(results[INDEX_OF_DISTANCE_CONTAINER]).intValue();
    }

    //////////GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener
    @Override
    public void onConnected(Bundle bundle) {
        isConnected = true;
        if (myCurrentPosition == null){
            Location lastKnownPosition = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
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
