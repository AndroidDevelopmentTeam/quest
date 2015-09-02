package com.androiddevteam.quest.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.androiddevteam.quest.R;
import com.androiddevteam.quest.google_map.GoogleMapManager;
import com.androiddevteam.quest.structure.PointItem;

import java.util.List;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 02.07.15
 */

public class ListAdapterAllPoints extends BaseAdapter{

    //Point id's
    private static final int POINT_ITEM_LAYOUT_ID = R.layout.adapter_item_point;
    private static final int POINT_ITEM_POINT_NAME_ID = R.id.textView_point_name;
    private static final int POINT_ITEM_POINT_POSITION_ID = R.id.textView_point_position;

    //Distance id's
    private static final int DISTANCE_ITEM_LAYOUT_ID = R.layout.adapter_item_distance;
    private static final int DISTANCE_ITEM_DISTANCE_ID = R.id.textView_dist_item_distance;
    private static final int DISTANCE_ITEM_DIVIDER_ID = R.id.view_dist_item_divider;

    private static final int MOD = 2;
    private static final int REMAINDER_FOR_POINT = 0;


    private List<PointItem> items;
    private Context context;

    private int diff = 0;
//    private int maxDiff = 0;

    public ListAdapterAllPoints(Context context, List<PointItem> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return (items.size() * 2) - 1;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position == 0){
            diff = 0;
        }
        if ((position % MOD) == REMAINDER_FOR_POINT){
            convertView = LayoutInflater.from(context).inflate(POINT_ITEM_LAYOUT_ID, null, false);
                populatePointView(convertView, items.get(diff));
        } else {
            convertView = LayoutInflater.from(context).inflate(DISTANCE_ITEM_LAYOUT_ID, null, false);
            diff++;
            if (position == 1){
                populateDistanceView(convertView, position + 1);
            } else {
                populateDistanceView(convertView, position);
            }
        }

        return convertView;
    }

    private void populatePointView(View convertView, PointItem pointItem){
        TextView pointNameAvatar = ((TextView) convertView.findViewById(POINT_ITEM_POINT_NAME_ID));

        pointNameAvatar.setText(pointItem.getPointName());
        pointNameAvatar.setTextColor(pointItem.getPointColor());
//        pointNameAvatar.setCompoundDrawablePadding(
//                Float.valueOf(context.getResources().getDimension(MainActivity.DEFAULT_APP_PADDING)).intValue());
        if (pointItem.getPointAvatarBitmap() != null){
            pointNameAvatar.setCompoundDrawablesWithIntrinsicBounds(
                    new BitmapDrawable(context.getResources(), pointItem.getPointAvatarBitmap()),
                    null,
                    null,
                    null);
        }

        TextView pointPosition = ((TextView) convertView.findViewById(POINT_ITEM_POINT_POSITION_ID));
        pointPosition.setText(pointItem.getPointPosition().toString());
        pointPosition.setTextColor(pointItem.getPointColor());
    }

    private void populateDistanceView(View convertView, int currentPosition){
        GoogleMapManager.distanceBetweenPointsString(items.get(currentPosition - diff - 1).getPointPosition(),
                items.get(currentPosition - diff).getPointPosition());

        ((TextView) convertView.findViewById(DISTANCE_ITEM_DISTANCE_ID))
                .setText(GoogleMapManager
                        .distanceBetweenPointsString(
                                items.get(currentPosition - diff - 1).getPointPosition(),
                                items.get(currentPosition - diff).getPointPosition()));

//        convertView.findViewById(DISTANCE_ITEM_DIVIDER_ID)
//                .setBackgroundColor(items.get(currentPosition - diff).getPointColor());
    }
}
