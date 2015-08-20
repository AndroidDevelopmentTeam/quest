package com.android.androiddevteam.quest.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.database.DataAdapter;
import com.android.androiddevteam.quest.fragment.FragBaseAbstract;

/**
 * Project: Quest
 * Author: Oleksandr Priadko
 * Date: 04.08.15
 */

public abstract class BaseAbstractFragmentActivity extends FragmentActivity{

    private static final int DEFAULT_APP_PADDING_ID = R.dimen.marginPadding;

    protected static float DEFAULT_APP_PADDING_FLOAT;
    protected static int DEFAULT_APP_PADDING_INT;

    protected static DataAdapter dataAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        DEFAULT_APP_PADDING_FLOAT = getResources().getDimension(DEFAULT_APP_PADDING_ID);
        DEFAULT_APP_PADDING_INT = Float.valueOf(getResources().getDimension(DEFAULT_APP_PADDING_ID)).intValue();

        if (dataAdapter == null){
            dataAdapter = new DataAdapter(this);
            dataAdapter.createDatabase();
        }

        if (!dataAdapter.isDatabaseOpen()){
            dataAdapter.open();
        }

        additionalCustomization();
    }

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

        dataAdapter.close();
    }

    protected abstract void actionsBeforeParentStop();

    protected abstract void actionsAfterParentStop();

    public static float getDefaultAppPaddingFloat() {
        return DEFAULT_APP_PADDING_FLOAT;
    }

    public static int getDefaultAppPaddingInt() {
        return DEFAULT_APP_PADDING_INT;
    }

    protected abstract int getFragmentContainerId();

    protected abstract int getContentViewId();

    protected abstract void additionalCustomization();

    public DataAdapter getDataAdapter() {
        return dataAdapter;
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
}
