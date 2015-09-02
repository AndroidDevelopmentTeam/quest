package com.android.androiddevteam.quest.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.activity.MainActivity;
import com.android.androiddevteam.quest.database.DataAdapter;
import com.android.zeus.bitmaputilities.BitmapUtilities;
import com.android.zeus.ui.adapter.CursorRecyclerViewAdapter;

/**
 * Quest
 * Created by: Oleksandr Priadko
 * 26.08.15
 */
public class CursorAdapterAllQuests extends CursorRecyclerViewAdapter{

    private static final int ITEM_ROOT_LAYOUT_ID = R.layout.rec_view_quest_card;
    private static final int ITEM_TITLE_AVATAR_ID = R.id.textView_quest_name_avatar;
    private static final int DEFAULT_QUEST_DRAWABLE = R.drawable.ic_action_place;

    private Context context;

    public CursorAdapterAllQuests(Context context, Cursor cursor) {
        super(cursor);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        ((ViewHolder) viewHolder).questTitleAvatar
                .setText(cursor.getString(cursor.getColumnIndex(DataAdapter.QUEST_NAME_STRING)));
        ((ViewHolder) viewHolder).questTitleAvatar.setCompoundDrawablePadding(MainActivity.getDefaultAppPaddingInt());

        byte[] blob = cursor.getBlob(cursor.getColumnIndex(DataAdapter.QUEST_AVATAR_BLOB));
        if (blob.length > 1){
            ((ViewHolder) viewHolder).questTitleAvatar.setCompoundDrawablesWithIntrinsicBounds(
                    new BitmapDrawable(context.getResources(), BitmapUtilities.byteArrayToBitmap(blob)),
                    null,
                    null,
                    null);
        } else {
            ((ViewHolder) viewHolder).questTitleAvatar.setCompoundDrawablesWithIntrinsicBounds(
                    context.getResources().getDrawable(DEFAULT_QUEST_DRAWABLE),
                    null,
                    null,
                    null);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(ITEM_ROOT_LAYOUT_ID, viewGroup, false);
        return new ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView questTitleAvatar;

        public ViewHolder(View view) {
            super(view);
            questTitleAvatar = ((TextView) view.findViewById(ITEM_TITLE_AVATAR_ID));
        }
    }
}
