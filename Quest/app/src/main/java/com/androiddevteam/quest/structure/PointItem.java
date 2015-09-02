package com.androiddevteam.quest.structure;

import android.graphics.Bitmap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 03.08.15
 */

public class PointItem {

    private String pointName;
    private LatLng pointPosition;
    private int pointColor;
    private Bitmap pointAvatarBitmap;


    public PointItem(MarkerOptions markerOptions, int pointColor) {
        pointName = markerOptions.getTitle();
        pointPosition = markerOptions.getPosition();
        this.pointColor = pointColor;
    }

    public PointItem(String pointName, LatLng pointPosition, int pointColor) {
        this.pointName = pointName;
        this.pointPosition = pointPosition;
        this.pointColor = pointColor;
    }

    public PointItem(String pointName, LatLng pointPosition, Bitmap pointAvatarBitmap) {
        this.pointName = pointName;
        this.pointPosition = pointPosition;
        this.pointAvatarBitmap = pointAvatarBitmap;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public LatLng getPointPosition() {
        return pointPosition;
    }

    public void setPointPosition(LatLng pointPosition) {
        this.pointPosition = pointPosition;
    }

    public int getPointColor() {
        return pointColor;
    }

    public void setPointColor(int pointColor) {
        this.pointColor = pointColor;
    }

    public Bitmap getPointAvatarBitmap() {
        return pointAvatarBitmap;
    }

    public void setPointAvatarBitmap(Bitmap pointAvatarBitmap) {
        this.pointAvatarBitmap = pointAvatarBitmap;
    }
}
