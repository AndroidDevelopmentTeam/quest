package com.android.androiddevteam.quest.fragment.quest_list;

import android.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;
import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.bundle.BundleExtras;
import com.android.androiddevteam.quest.fragment.FragBaseAbstract;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 06.07.15
 */

public class FragQuest extends FragBaseAbstract {

    private static final int ROOT_LAYOUT_ID = R.layout.frag_quest;
    private static final int QUEST_TIME_ID = R.id.textView_quest_time;
    private static final int QUEST_DATE_ID = R.id.textView_quest_date;
    private static final int QUEST_CREATOR_ID = R.id.textView_quest_creator;
    private static final int QUEST_PRIZE_ID = R.id.textView_quest_prize;

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
        if (getArguments() != null
                && getArguments().getString(BundleExtras.FRAG_QUEST_QUEST_NAME_STRING) != null){
            return getArguments().getString(BundleExtras.FRAG_QUEST_QUEST_NAME_STRING);
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
            actionBar.setCustomView(R.layout.action_bar_base);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            ((TextView)actionBar.getCustomView().findViewById(R.id.textView_title))
                    .setText(getFragmentTitle());
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
            String time = getArguments().getString(BundleExtras.FRAG_QUEST_QUEST_TIME_STRING);
            if (time != null) ((TextView) rootView.findViewById(QUEST_TIME_ID)).setText(time);

            String date = getArguments().getString(BundleExtras.FRAG_QUEST_QUEST_DATE_STRING);
            if (date != null) ((TextView) rootView.findViewById(QUEST_DATE_ID)).setText(date);

            String creator = getArguments().getString(BundleExtras.FRAG_QUEST_QUEST_CREATOR_STRING);
            if (creator != null) ((TextView) rootView.findViewById(QUEST_CREATOR_ID)).setText(creator);

            String prize = getArguments().getString(BundleExtras.FRAG_QUEST_QUEST_PRIZE_STRING);
            if (prize != null) ((TextView) rootView.findViewById(QUEST_PRIZE_ID)).setText(prize);
        }
    }
}
