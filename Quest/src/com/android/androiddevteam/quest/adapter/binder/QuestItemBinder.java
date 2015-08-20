package com.android.androiddevteam.quest.adapter.binder;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.activity.MainActivity;
import com.android.androiddevteam.quest.database.DataAdapter;
import com.android.zeus.bitmaputilities.BitmapUtilities;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 20.08.15
 */

public class QuestItemBinder extends BaseAbstractBinder{

    private static final int DEFAULT_QUEST_DRAWABLE = R.drawable.ic_action_place;

    public QuestItemBinder(Context context) {
        super(context);
    }

    @Override
    protected void customSetDataToTextView(TextView textView, Cursor cursor, int columnIndex) {
        if (cursor.getColumnName(columnIndex).equals(DataAdapter.QUEST_AVATAR_BLOB)){
            textView.setCompoundDrawablePadding(MainActivity.getDefaultAppPaddingInt());

            byte[] blob = cursor.getBlob(columnIndex);
            if (blob != null){
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        new BitmapDrawable(getContext().getResources(), BitmapUtilities.byteArrayToBitmap(blob)),
                        null,
                        null,
                        null);
            } else {
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        getContext().getResources().getDrawable(DEFAULT_QUEST_DRAWABLE),
                        null,
                        null,
                        null);
            }
        } else {
            setDataToTextView(textView, cursor, columnIndex);
        }
    }

    @Override
    protected void customSetDataToImageView(ImageView imageView, Cursor cursor, int columnIndex) {

    }
}
