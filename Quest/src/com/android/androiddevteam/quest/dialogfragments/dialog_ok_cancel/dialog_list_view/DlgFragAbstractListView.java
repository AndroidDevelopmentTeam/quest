package com.android.androiddevteam.quest.dialogfragments.dialog_ok_cancel.dialog_list_view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.dialogfragments.DlgFragAbstractOkCancel;

public abstract class DlgFragAbstractListView extends DlgFragAbstractOkCancel
        implements AdapterView.OnItemClickListener{

    protected static final int LIST_VIEW_STATES_ID = R.id.listView_states;
    protected static final int ROOT_LAYOUT_ID = R.layout.dialog_states;

    protected static final String OK_TEXT = "Ок";
    protected static final String CANCEL_TEXT = "Отмена";

    protected  int stateImageId;
    protected  String stateName;

    @Override
    protected View getTitleView() {
        return null;
    }

    @Override
    protected View getRootView() {
        View rootView = getActivity().getLayoutInflater().inflate(ROOT_LAYOUT_ID, null);
        populateListView(rootView);
        return rootView;
    }

    protected void populateListView(View rootView){
        ListView listView = (ListView)rootView.findViewById(LIST_VIEW_STATES_ID);
        listView.setOnItemClickListener(this);
        listView.setAdapter(getAdapter());
    }

    protected abstract BaseAdapter getAdapter();

    @Override
    protected String getOKText() {
        return OK_TEXT;
    }

    @Override
    protected String getCancelText() {
        return CANCEL_TEXT;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        view.setSelected(true);
//        stateName
//                = ((StateItemBaseAbstract)parent.getAdapter().getItem(position)).getStateNameById(position);
//        stateImageId
//                = ((StateItemBaseAbstract)parent.getAdapter().getItem(position)).getStateImageById(position);
    }
}
