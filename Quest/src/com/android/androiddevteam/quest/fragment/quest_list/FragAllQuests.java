package com.android.androiddevteam.quest.fragment.quest_list;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.*;
import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.activity.ActNewQuest;
import com.android.androiddevteam.quest.activity.BaseAbstractFragmentActivity;
import com.android.androiddevteam.quest.adapter.CustomSimpleCursorAdapter;
import com.android.androiddevteam.quest.adapter.binder.QuestItemBinder;
import com.android.androiddevteam.quest.constants.BUNDLE;
import com.android.androiddevteam.quest.constants.REQUEST_CODES;
import com.android.androiddevteam.quest.database.DataAdapter;
import com.android.androiddevteam.quest.database.DataBaseHelper;
import com.android.androiddevteam.quest.dialogfragments.dialog_ok_cancel.DlgFragClearDatabase;
import com.android.androiddevteam.quest.fragment.FragBaseAbstract;
import com.android.zeus.ui.floating_button.FloatingActionButton;
import com.android.zeus.ui.floating_button.ShowHideOnScroll;

import java.io.IOException;

/**
 * Project: Quest
 * Author: priadko
 * Date: 02.07.15
 */

public class FragAllQuests extends FragBaseAbstract
        implements AdapterView.OnItemClickListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final int ROOT_LAYOUT_ID = R.layout.frag_quests;
    private static final int ACTION_BAR_LAYOUT_ID = R.layout.action_bar_act_main;
    private static final int LIST_VIEW_ID = R.id.listView_quests_list;
    private static final int NEW_QUEST_ID = R.id.floatButton_new_quest;
    private static final int CLEAR_DB_ID = R.id.imageButton_clear_db;
    private static final int ACTION_BAR_TITLE_ID = R.id.textView_title;
    private static final int SWIPE_REFRESH_LIST_ID = R.id.swipe_refresh_new_quest;
    private static final int ITEM_LAYOUT_ID = R.layout.adapter_item_quest;
    private static final int QUEST_NAME_AVATAR_ID = R.id.textView_quest_item_name;

    private static final String TAG = "FragAllQuests";
    private static final String TITLE = "All quests";

    private static final String REFRESH_MESSAGE = "Refreshing";

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView questsListView;

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
        questsListView = ((ListView) rootView.findViewById(LIST_VIEW_ID));
        questsListView.setOnItemClickListener(this);

        rootView.findViewById(NEW_QUEST_ID).setOnClickListener(this);

        swipeRefreshLayout = ((SwipeRefreshLayout) rootView.findViewById(SWIPE_REFRESH_LIST_ID));
        swipeRefreshLayout.setOnRefreshListener(this);
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
            ((TextView)actionBar.getCustomView().findViewById(ACTION_BAR_TITLE_ID))
                    .setText(getFragmentTitle());
            actionBar.getCustomView().findViewById(CLEAR_DB_ID).setOnClickListener(this);
        }
    }

    /**
     * We can customize views that rootView contain here.
     *
     * @param rootView Root view of fragment.
     */
    @Override
    protected void customizeViews(View rootView) {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        FloatingActionButton floatingActionButton = ((FloatingActionButton) rootView.findViewById(NEW_QUEST_ID));
        floatingActionButton.setColor(Color.WHITE);
        floatingActionButton.setSize(FloatingActionButton.SIZE_NORMAL);
        floatingActionButton.initBackground();

        questsListView.setAdapter(getAdapter());
        ShowHideOnScroll showHideOnScroll = new ShowHideOnScroll(floatingActionButton);
        questsListView.setOnTouchListener(showHideOnScroll);
    }

    private BaseAdapter getAdapter(){
        CustomSimpleCursorAdapter cursorAdapter = new CustomSimpleCursorAdapter(getActivity(),
                ITEM_LAYOUT_ID,
                ((BaseAbstractFragmentActivity) getActivity()).getDataAdapter().getQuests(),
                new String[]{DataAdapter.QUEST_NAME_STRING, DataAdapter.QUEST_AVATAR_BLOB},
                new int[]{QUEST_NAME_AVATAR_ID, QUEST_NAME_AVATAR_ID},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

//        List<QuestItem> questItems = new ArrayList<>();
//        for (int i = 0; i < DEF_QUEST_ITEMS_COUNT; ++i){
//            QuestItem questItem = new QuestItem("Quest #" + (i + DEF_QUEST_ITEMS_COUNT), DEF_QUEST_DRAWABLE_ID);
//            questItem.setTime(Long.valueOf(System.nanoTime()).toString());
//            questItem.setDate(Integer.valueOf(Calendar.getInstance().getTime().getDate()).toString());
//            questItem.setCreator("#" + (i + DEF_QUEST_ITEMS_COUNT));
//            questItem.setPrize(Integer.valueOf((i + DEF_QUEST_ITEMS_COUNT)).toString());
//
//            questItems.add(questItem);
//        }
//        return new ListAdapterAllQuests(getActivity(), questItems);

        cursorAdapter.setViewBinder(new QuestItemBinder(getActivity()));

        return cursorAdapter;
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
        Cursor itemCursor = ((Cursor) parent.getAdapter().getItem(position));

        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE.STRING.QUEST_NAME,
                getStringFromCursor(itemCursor, DataAdapter.QUEST_NAME_STRING));
        bundle.putString(BUNDLE.STRING.QUEST_TIME,
                getStringFromCursor(itemCursor, DataAdapter.QUEST_TIME_STRING));
        bundle.putString(BUNDLE.STRING.QUEST_DATE,
                getStringFromCursor(itemCursor, DataAdapter.QUEST_DATE_STRING));
        bundle.putString(BUNDLE.STRING.QUEST_CREATOR,
                getStringFromCursor(itemCursor, DataAdapter.QUEST_CREATOR_STRING));
        bundle.putString(BUNDLE.STRING.QUEST_PRIZE, getStringFromCursor(itemCursor,
                DataAdapter.QUEST_PRIZE_STRING));

        itemCursor.close();

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
            case CLEAR_DB_ID:
                askForActionWithDb();
                break;
            default:
                break;
        }
    }

    private void askForActionWithDb(){
        DlgFragClearDatabase fragClearDatabase = new DlgFragClearDatabase();
        fragClearDatabase.setTargetFragment(FragAllQuests.this, REQUEST_CODES.CLEAR_DATABASE);
        fragClearDatabase.show(getFragmentManager(), fragClearDatabase.getDialogTag());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode){
            case Activity.RESULT_OK:
                switch (requestCode){
                    case REQUEST_CODES.CLEAR_DATABASE:
                        clearCreateDB();
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    private void clearCreateDB(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        try {
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), REFRESH_MESSAGE, Toast.LENGTH_LONG).show();
    }
}
