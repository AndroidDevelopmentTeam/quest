package com.androiddevteam.quest.dialogfragments.dialog_ok;

import android.content.DialogInterface;
import android.view.View;
import com.androiddevteam.quest.dialogfragments.DlgFragAbstractOk;

public class DlgFragTitle extends DlgFragAbstractOk {

    private String dialogTitle;
    private static final String DIALOG_TAG = "DlgFragTitle";

    private static final String OK_TEXT = "Ok";

    public void setMessage(String message){
        dialogTitle = message;
    }

    @Override
    protected View getTitleView() {
        return null;
    }

    @Override
    protected String getTitle() {
        return dialogTitle;
    }

    @Override
    protected View getRootView() {
        return null;
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
