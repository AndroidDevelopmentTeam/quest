package com.androiddevteam.quest.structure;

import android.graphics.Bitmap;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 02.07.15
 */

public class QuestItem {

    private String name;
    private Bitmap avatarBitmap = null;
    private int avatarDrawableId;
    private String time;
    private String date;
    private String creator;
    private String prize;

    public QuestItem(String name, Bitmap avatarBitmap) {
        this.name = name;
        this.avatarBitmap = avatarBitmap;
    }

    public QuestItem(String name, int avatarDrawableId) {
        this.name = name;
        this.avatarDrawableId = avatarDrawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getAvatarBitmap() {
        return avatarBitmap;
    }

    public void setAvatarBitmap(Bitmap avatarBitmap) {
        this.avatarBitmap = avatarBitmap;
    }

    public int getAvatarDrawableId() {
        return avatarDrawableId;
    }

    public void setAvatarDrawableId(int avatarDrawableId) {
        this.avatarDrawableId = avatarDrawableId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}
