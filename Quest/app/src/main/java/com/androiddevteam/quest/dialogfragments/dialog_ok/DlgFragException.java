package com.androiddevteam.quest.dialogfragments.dialog_ok;

import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import com.androiddevteam.quest.R;
import com.androiddevteam.quest.dialogfragments.DlgFragAbstractOk;

public class DlgFragException extends DlgFragAbstractOk {

    private static final int ROOT_VIEW = R.layout.dialog_exception;

    private static final String DIALOG_TAG = "DlgFragException";
    protected static final String OK_TEXT = "OK";
    private static final String ATTENTION = "Attention!";

    private String message;

    public void setException(Exception e){
        this.message = e.getMessage();
        e.printStackTrace();
    }
    public void setMessage(String message){
        this.message = message;
    }

    @Override
    protected View getTitleView() {
        return null;
    }

    @Override
    protected String getTitle() {
        return ATTENTION;
    }

    @Override
    protected View getRootView() {
        TextView rootView = ((TextView) getActivity().getLayoutInflater().inflate(ROOT_VIEW, null, false));
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
