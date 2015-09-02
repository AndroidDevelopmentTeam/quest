package com.androiddevteam.quest.adapter.binder;

import android.content.Context;
import android.database.Cursor;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 20.08.15
 */

public class BaseViewBinder extends BaseAbstractBinder {

    public BaseViewBinder(Context context) {
        super(context);
    }

    @Override
    protected void customSetDataToTextView(TextView view, Cursor cursor, int columnIndex) {

    }

    @Override
    protected void customSetDataToImageView(ImageView view, Cursor cursor, int columnIndex) {

    }
}
