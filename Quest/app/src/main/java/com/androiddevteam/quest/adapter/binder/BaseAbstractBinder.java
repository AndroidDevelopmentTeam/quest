package com.androiddevteam.quest.adapter.binder;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.android.priadko.bitmaputilities.BitmapUtilities;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 20.08.15
 */

public abstract class BaseAbstractBinder implements SimpleCursorAdapter.ViewBinder {

    private Context context;

    public BaseAbstractBinder(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
    /**
     * Binds the Cursor column defined by the specified index to the specified view.
     * <p/>
     * When binding is handled by this ViewBinder, this method must return true.
     * If this method returns false, SimpleCursorAdapter will attempts to handle
     * the binding on its own.
     *
     * @param view        the view to bind the data to
     * @param cursor      the cursor to get the data from
     * @param columnIndex the column at which the data can be found in the cursor
     * @return true if the data was bound to the view, false otherwise
     */
    @Override
    public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
        if (view instanceof TextView) {
            customSetDataToTextView((TextView)view, cursor, columnIndex);
        } else if (view instanceof ImageView) {
            customSetDataToImageView((ImageView) view, cursor, columnIndex);
        } else {
            throw new IllegalStateException(view.getClass().getName() + " is not a " +
                    " view that can be bounds by this SimpleCursorAdapter");
        }
        return true;
    }

    protected abstract void customSetDataToTextView(TextView view, Cursor cursor, int columnIndex);

    protected void setDataToTextView(View view, Cursor cursor, int columnIndex) {
        String text = cursor.getString(columnIndex);
        if (text == null) {
            text = "";
        }
        ((TextView) view).setText(text);
    }

    protected abstract void customSetDataToImageView(ImageView view, Cursor cursor, int columnIndex);

    protected void setBitmapToImageView(View view, Cursor cursor, int columnIndex) {
        byte[] bytes = cursor.getBlob(columnIndex);
        if (bytes != null) {
            ((ImageView) view).setImageBitmap(BitmapUtilities.byteArrayToBitmap(bytes));
        }
    }
}
