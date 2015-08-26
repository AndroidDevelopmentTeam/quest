package com.android.androiddevteam.quest.fragment;

import android.app.ActionBar;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.activity.MainActivity;

/**
 * Project: Quest
 * Author: priadko
 * Date: 02.07.15
 */

public abstract class FragBaseAbstract extends Fragment{

    /**
     * Return fragment tag. Tag will be used in transition in SupportFragmentManager.
     * @see android.support.v4.app.FragmentManager
     * @return fragment tag.
     */
    public abstract String getFragmentTag();

    /**
     * Return fragment title. Title will be set to ActionBar.
     * @see android.app.ActionBar
     * @return fragment title.
     */
    public abstract String getFragmentTitle();

    /**
     * Return id of root layout.
     * @return id of layout as int
     */
    protected abstract int getRootLayoutId();

    /**
     * We can set listeners to views in rootView.
     * @param rootView Root view of fragment.
     */
    protected abstract void setClickListeners(View rootView);

    /**
     * We can customize ActionBar here.
     * @param actionBar ActionBar that can be customized.
     * @see ActionBar
     */
    protected abstract void customizeActionBar(ActionBar actionBar);

    /**
     * We can customize views that rootView contain here.
     * @param rootView Root view of fragment.
     */
    protected abstract void customizeViews(View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(getRootLayoutId(), null, false);
        setClickListeners(rootView);
        if (getActivity().getActionBar() != null){
            customizeActionBar(getActivity().getActionBar());
        }
        customizeViews(rootView);

        return rootView;
    }

    /**
     * Replace current fragment in container by new fragment.
     * @param fragment New fragment.
     */
    protected void replaceFragmentBackStack(FragBaseAbstract fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(MainActivity.FRAGMENT_CONTAINER_ID, fragment)
                .addToBackStack(fragment.getFragmentTag())
                .commit();
    }

    /**
     * Replace current fragment in container by new fragment.
     * @param fragment New fragment.
     */
    protected void replaceFragmentBackStack(FragBaseAbstract fragment, int containerId) {
        getFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(fragment.getFragmentTag())
                .commit();
    }

    protected String getStringFromCursor(Cursor cursor, String columnName){
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    protected int getIntFromCursor(Cursor cursor, String columnName){
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    protected byte[] getBlobFromCursor(Cursor cursor, String columnName){
        return cursor.getBlob(cursor.getColumnIndex(columnName));
    }
}
