package com.androiddevteam.quest.fragment.quest_list;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

import com.android.priadko.bitmaputilities.BitmapUtilities;
import com.androiddevteam.quest.R;
import com.androiddevteam.quest.activity.MainActivity;
import com.androiddevteam.quest.constants.BUNDLE;
import com.androiddevteam.quest.fragment.FragBaseAbstract;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 06.07.15
 */

public class FragQuest extends FragBaseAbstract {

    private static final int ROOT_LAYOUT_ID = R.layout.frag_quest;
    private static final int ACTION_BAR_LAYOUT_ID = R.layout.action_bar_frag_quest;
    private static final int QUEST_TIME_ID = R.id.textView_quest_time;
    private static final int QUEST_DATE_ID = R.id.textView_quest_date;
    private static final int QUEST_CREATOR_ID = R.id.textView_quest_creator;
    private static final int QUEST_PRIZE_ID = R.id.textView_quest_prize;
    private static final int ACTION_BAR_TITLE_ID = R.id.textView_title;

    private static final String TAG = "FragQuest";
    private static final String TITLE = "Quest";

    /**
     * Return fragment tag. Tag will be used in transition in SupportFragmentManager.
     *
     * @return fragment tag.
     * @see FragmentManager
     */
    @Override
    public String getFragmentTag() {
        return TAG;
    }

    /**
     * Return fragment title. Title will be set to ActionBar.
     *
     * @return fragment title.
     * @see ActionBar
     */
    @Override
    public String getFragmentTitle() {
        if (getArguments() != null && getArguments().getString(BUNDLE.STRING.QUEST_NAME) != null){
            return getArguments().getString(BUNDLE.STRING.QUEST_NAME);
        } else {
            return TITLE;
        }
    }

    /**
     * Return id of root layout.
     *
     * @return id of layout as int
     */
    @Override
    protected int getRootLayoutId() {
        return ROOT_LAYOUT_ID;
    }

    /**
     * We can set listeners to views in rootView.
     *
     * @param rootView Root view of fragment.
     */
    @Override
    protected void setClickListeners(View rootView) {

    }

    /**
     * We can customize ActionBar here.
     *
     * @param actionBar ActionBar that can be customized.
     * @see ActionBar
     */
    @Override
    protected void customizeActionBar(ActionBar actionBar) {
        if (actionBar != null) {
            actionBar.setCustomView(ACTION_BAR_LAYOUT_ID);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            TextView textView = ((TextView)actionBar.getCustomView().findViewById(ACTION_BAR_TITLE_ID));
            textView.setText(getFragmentTitle());
            if (getArguments() != null){
                textView.setCompoundDrawablePadding(MainActivity.getDefaultAppPaddingInt());

                byte[] blob = getArguments().getByteArray(BUNDLE.BYTE_ARRAY.QUEST_AVATAR);
                if (blob != null && blob.length > 1){
                    textView.setCompoundDrawablesWithIntrinsicBounds(
                            new BitmapDrawable(getResources(), BitmapUtilities.byteArrayToBitmap(blob)),
                            null,
                            null,
                            null);
                }
            }
        }
    }

    /**
     * We can customize views that rootView contain here.
     *
     * @param rootView Root view of fragment.
     */
    @Override
    protected void customizeViews(View rootView) {
        if (getArguments() != null){
            String time = getArguments().getString(BUNDLE.STRING.QUEST_TIME);
            if (time != null) ((TextView) rootView.findViewById(QUEST_TIME_ID)).setText(time);

            String date = getArguments().getString(BUNDLE.STRING.QUEST_DATE);
            if (date != null) ((TextView) rootView.findViewById(QUEST_DATE_ID)).setText(date);

            String creator = getArguments().getString(BUNDLE.STRING.QUEST_CREATOR);
            if (creator != null) ((TextView) rootView.findViewById(QUEST_CREATOR_ID)).setText(creator);

            String prize = getArguments().getString(BUNDLE.STRING.QUEST_PRIZE);
            if (prize != null) ((TextView) rootView.findViewById(QUEST_PRIZE_ID)).setText(prize);
        }
    }
}
