package com.android.androiddevteam.quest.adapter;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class CustomSimpleCursorAdapter extends SimpleCursorAdapter {

    /**
     * Standard constructor.
     *
     * @param context The context where the ListView associated with this
     *                SimpleListItemFactory is running
     * @param layout  resource identifier of a layout file that defines the views
     *                for this list item. The layout file should include at least
     *                those named views defined in "to"
     * @param c       The database cursor.  Can be null if the cursor is not available yet.
     * @param from    A list of column names representing the data to bind to the UI.  Can be null
     *                if the cursor is not available yet.
     * @param to      The views that should display column in the "from" parameter.
     *                These should all be TextViews. The first N views in this list
     *                are given the values of the first N columns in the from
     *                parameter.  Can be null if the cursor is not available yet.
     * @param flags   Flags used to determine the behavior of the adapter,
     *                as per {@link android.widget.CursorAdapter#CursorAdapter(Context, Cursor, int)}.
     */
    public CustomSimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }
}
