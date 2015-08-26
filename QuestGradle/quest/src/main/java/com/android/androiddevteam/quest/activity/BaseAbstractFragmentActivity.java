package com.android.androiddevteam.quest.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.structure.App;
import com.android.androiddevteam.quest.fragment.FragBaseAbstract;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 04.08.15
 */

public abstract class BaseAbstractFragmentActivity extends AppCompatActivity{

    private static final int DEFAULT_APP_PADDING_ID = R.dimen.marginPadding;

    protected static float DEFAULT_APP_PADDING_FLOAT;
    protected static int DEFAULT_APP_PADDING_INT;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        DEFAULT_APP_PADDING_FLOAT = getResources().getDimension(DEFAULT_APP_PADDING_ID);
        DEFAULT_APP_PADDING_INT = Float.valueOf(getResources().getDimension(DEFAULT_APP_PADDING_ID)).intValue();

        App.getInstance().setAppContext(getApplicationContext());
        App.getInstance().getDataAdapter();

        additionalCustomization();

        customizeActionBar(getActionBar());
    }

    protected abstract int getContentViewId();

    protected abstract void additionalCustomization();

    protected abstract void customizeActionBar(ActionBar actionBar);

    @Override
    protected void onResume() {
        actionsBeforeParentResume();

        super.onResume();

        actionsAfterParentResume();
    }

    protected abstract void actionsBeforeParentResume();

    protected abstract void actionsAfterParentResume();

    @Override
    protected void onPause() {
        actionsBeforeParentPause();

        super.onPause();

        actionsAfterParentPause();
    }

    protected abstract void actionsBeforeParentPause();

    protected abstract void actionsAfterParentPause();

    @Override
    protected void onStop() {
        actionsBeforeParentStop();

        super.onStop();

        actionsAfterParentStop();

        App.getInstance().getDataAdapter().close();
    }

    protected abstract void actionsBeforeParentStop();

    protected abstract void actionsAfterParentStop();

    public static float getDefaultAppPaddingFloat() {
        return DEFAULT_APP_PADDING_FLOAT;
    }

    public static int getDefaultAppPaddingInt() {
        return DEFAULT_APP_PADDING_INT;
    }

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

    protected abstract int getFragmentContainerId();
}
