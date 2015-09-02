package com.androiddevteam.quest.dialogfragments.dialog_ok;

import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import com.androiddevteam.quest.R;
import com.androiddevteam.quest.dialogfragments.DlgFragAbstractOk;

public class DlgFragTitleMessage extends DlgFragAbstractOk {

    private String DIALOG_TITLE = "Внимание!";
    private static final String DIALOG_TAG = "DlgFragTitleMessage";
    private static final int ROOT_LAYOUT_ID = R.layout.dialog_message;

    private static final String OK_TEXT = "OK";

    private String message;

    public void setMessage(String message){
        this.message = message;
    }

    @Override
    protected View getTitleView() {
        return null;
    }

    @Override
    protected String getTitle() {
        return DIALOG_TITLE;
    }

    @Override
    protected View getRootView() {
        TextView rootView
                = ((TextView) getActivity().getLayoutInflater().inflate(ROOT_LAYOUT_ID, null, false));
        rootView.setText(message);

        return rootView;
    }

    @Override
    public String getDialogTag() {
        return DIALOG_TAG;
    }

    @Override
    protected String getOKText() {
        return OK_TEXT;
    }

    @Override
    protected void performOK(DialogInterface dialog, int id) {
        dismiss();

    }
}
