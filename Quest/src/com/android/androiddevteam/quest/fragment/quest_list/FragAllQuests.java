package com.android.androiddevteam.quest.fragment.quest_list;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.*;
import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.activity.ActNewQuest;
import com.android.androiddevteam.quest.adapter.ListAdapterAllQuests;
import com.android.androiddevteam.quest.bundle.BundleExtras;
import com.android.androiddevteam.quest.fragment.FragBaseAbstract;
import com.android.androiddevteam.quest.structure.QuestItem;
import com.android.zeus.ui.floating_button.FloatingActionButton;
import com.android.zeus.ui.floating_button.ShowHideOnScroll;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Project: Quest
 * Author: priadko
 * Date: 02.07.15
 */

public class FragAllQuests extends FragBaseAbstract
        implements AdapterView.OnItemClickListener, View.OnClickListener{

    private static final int ROOT_LAYOUT_ID = R.layout.frag_quests;
    private static final int LIST_VIEW_ID = R.id.listView_quests_list;
    private static final int NEW_QUEST_ID = R.id.floatButton_new_quest;
    private static final String TAG = "FragAllQuests";
    private static final String TITLE = "All quests";

    @Deprecated
    private static final int DEF_QUEST_DRAWABLE_ID = R.drawable.ic_launcher;

    @Deprecated
    private static final int DEF_QUEST_ITEMS_COUNT = 100;

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
        return TITLE;
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
        ((ListView) rootView.findViewById(LIST_VIEW_ID)).setOnItemClickListener(this);
        rootView.findViewById(NEW_QUEST_ID).setOnClickListener(this);
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
        FloatingActionButton floatingActionButton = ((FloatingActionButton) rootView.findViewById(NEW_QUEST_ID));
        floatingActionButton.setColor(Color.WHITE);
        floatingActionButton.setSize(FloatingActionButton.SIZE_NORMAL);
        floatingActionButton.initBackground();

        ((ListView) rootView.findViewById(LIST_VIEW_ID)).setAdapter(getAdapter());
        ShowHideOnScroll showHideOnScroll = new ShowHideOnScroll(floatingActionButton);
        rootView.findViewById(LIST_VIEW_ID).setOnTouchListener(showHideOnScroll);
    }

    private BaseAdapter getAdapter(){
        List<QuestItem> questItems = new ArrayList<>();
        for (int i = 0; i < DEF_QUEST_ITEMS_COUNT; ++i){
            QuestItem questItem = new QuestItem("Quest #" + (i + DEF_QUEST_ITEMS_COUNT), DEF_QUEST_DRAWABLE_ID);
            questItem.setTime(Long.valueOf(System.nanoTime()).toString());
            questItem.setDate(Integer.valueOf(Calendar.getInstance().getTime().getDate()).toString());
            questItem.setCreator("#" + (i + DEF_QUEST_ITEMS_COUNT));
            questItem.setPrize(Integer.valueOf((i + DEF_QUEST_ITEMS_COUNT)).toString());

            questItems.add(questItem);
        }
        return new ListAdapterAllQuests(getActivity(), questItems);
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        QuestItem questItem = ((QuestItem) parent.getAdapter().getItem(position));

        Bundle bundle = new Bundle();
        bundle.putString(BundleExtras.FRAG_QUEST_QUEST_NAME_STRING, questItem.getName());
        bundle.putString(BundleExtras.FRAG_QUEST_QUEST_TIME_STRING, questItem.getTime());
        bundle.putString(BundleExtras.FRAG_QUEST_QUEST_DATE_STRING, questItem.getDate());
        bundle.putString(BundleExtras.FRAG_QUEST_QUEST_CREATOR_STRING, questItem.getCreator());
        bundle.putString(BundleExtras.FRAG_QUEST_QUEST_PRIZE_STRING, questItem.getPrize());

        FragQuest fragQuest = new FragQuest();
        fragQuest.setArguments(bundle);

        replaceFragmentBackStack(fragQuest);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case NEW_QUEST_ID:
                Intent intent = new Intent(getActivity(), ActNewQuest.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
