package com.android.androiddevteam.quest.structure;

import android.graphics.Bitmap;

/**
 * Project: Quest
 * Author: priadko
 * Date: 02.07.15
 */

public class QuestItem {

    private String name;
    private Bitmap avatarBitmap = null;
    private int avatarDrawableId;

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
}
