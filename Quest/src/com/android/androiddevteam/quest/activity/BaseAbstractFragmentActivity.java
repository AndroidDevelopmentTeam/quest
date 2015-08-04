package com.android.androiddevteam.quest.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.fragment.FragBaseAbstract;
import com.android.androiddevteam.quest.fragment.quest_list.FragAllQuests;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 04.08.15
 */

public abstract class BaseAbstractFragmentActivity extends FragmentActivity{
    public static final int DEFAULT_APP_PADDING = R.dimen.marginPadding;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        additionalCustomization();
    }

    protected abstract int getFragmentContainerId();

    protected abstract int getContentViewId();

    protected abstract void additionalCustomization();

    /**
     * Replace current fragment in container by new fragment.
     * @param fragment New fragment.
     */
    protected void replaceFragmentBackStack(FragBaseAbstract fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getFragmentContainerId(), fragment)
                .addToBackStack(fragment.getFragmentTag())
                .commit();
    }

    /**
     * Replace current fragment in container by new fragment.
     * @param fragment New fragment.
     */
    protected void addFragmentBackStack(android.app.Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .add(getFragmentContainerId(), fragment)
                .addToBackStack(fragment.getTag())
                .commit();
    }
}
