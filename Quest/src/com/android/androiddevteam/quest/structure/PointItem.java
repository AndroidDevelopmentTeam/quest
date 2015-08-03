package com.android.androiddevteam.quest.structure;

import android.graphics.Bitmap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 03.08.15
 */

public class PointItem {

    private String pointName;
    private LatLng pointPosition;
    private int pointDefaultIconId;
    private Bitmap pointAvatar;

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

    public int getPointDefaultIconId() {
        return pointDefaultIconId;
    }

    public void setPointDefaultIconId(int pointDefaultIconId) {
        this.pointDefaultIconId = pointDefaultIconId;
    }

    public Bitmap getPointAvatar() {
        return pointAvatar;
    }

    public void setPointAvatar(Bitmap pointAvatar) {
        this.pointAvatar = pointAvatar;
    }
}
