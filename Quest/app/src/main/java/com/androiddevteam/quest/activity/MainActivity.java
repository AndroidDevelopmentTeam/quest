package com.androiddevteam.quest.activity;

import android.app.ActionBar;
import com.androiddevteam.quest.R;
import com.androiddevteam.quest.fragment.quest_list.FragAllQuests;

public class MainActivity extends BaseAbstractFragmentActivity {

    public static final int CONTENT_VIEW_ID = R.layout.act_main;

    @Override
    protected void actionsBeforeParentResume() {

    }

    @Override
    protected void actionsAfterParentResume() {

    }

    @Override
    protected void actionsBeforeParentPause() {

    }

    @Override
    protected void actionsAfterParentPause() {

    }

    @Override
    protected void actionsBeforeParentStop() {

    }

    @Override
    protected void actionsAfterParentStop() {

    }

    @Override
    protected int getFragmentContainerId() {
        return FRAGMENT_CONTAINER_ID;
    }

    @Override
    protected int getContentViewId() {
        return CONTENT_VIEW_ID;
    }

    @Override
    protected void additionalCustomization() {
        replaceFragmentBackStack(new FragAllQuests());
    }

    @Override
    protected void customizeActionBar(ActionBar actionBar) {

    }
}
