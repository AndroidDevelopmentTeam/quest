package com.android.androiddevteam.quest.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.structure.QuestItem;

import java.util.List;

/**
 * Project: Quest
 * Author: priadko
 * Date: 02.07.15
 */

public class ListAdapterAllQuests extends BaseAdapter{

    private static final int ITEM_LAYOUT_ID = R.layout.adapter_item_quest_item;
    private static final int QUEST_NAME_AVATAR_ID = R.id.textView_quest_item_name;
    private static final int DEFAULT_APP_PADDING = R.dimen.marginPadding;

    private List<QuestItem> items;
    private Context context;

    public ListAdapterAllQuests(Context context, List<QuestItem> items) {
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
        return items.size();
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
        return position;
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
        if (convertView != null){
            populateConvertView(convertView, items.get(position));
        } else {
            convertView = LayoutInflater.from(context).inflate(ITEM_LAYOUT_ID, null, false);
            populateConvertView(convertView, items.get(position));
        }
        return convertView;
    }

    private void populateConvertView(View convertView, QuestItem questItem){
        TextView textView = ((TextView) convertView.findViewById(QUEST_NAME_AVATAR_ID));

        textView.setText(questItem.getName());
        textView.setCompoundDrawablePadding(
                Float.valueOf(context.getResources().getDimension(DEFAULT_APP_PADDING)).intValue());
        if (questItem.getAvatarBitmap() != null){
            textView.setCompoundDrawablesWithIntrinsicBounds(
                    new BitmapDrawable(context.getResources(), questItem.getAvatarBitmap()),
                    null,
                    null,
                    null);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(
                    context.getResources().getDrawable(questItem.getAvatarDrawableId()),
                    null,
                    null,
                    null);
        }
    }
}
