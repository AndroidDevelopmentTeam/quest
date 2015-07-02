package com.android.androiddevteam.quest.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.fragment.FragBaseAbstract;
import com.android.androiddevteam.quest.fragment.quest_list.FragAllQuests;

public class MainActivity extends FragmentActivity {

    private static final int FRAGMENT_CONTAINER_ID = R.id.frag_container;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        replaceFragmentBackStack(new FragAllQuests());
    }

    /**
     * Replace current fragment in container by new fragment.
     * @param fragment New fragment.
     */
    protected void replaceFragmentBackStack(FragBaseAbstract fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(FRAGMENT_CONTAINER_ID, fragment)
                .addToBackStack(fragment.getFragmentTag())
                .commit();
    }
}
