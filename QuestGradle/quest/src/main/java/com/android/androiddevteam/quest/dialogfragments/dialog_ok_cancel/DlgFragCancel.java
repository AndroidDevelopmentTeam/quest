package com.android.androiddevteam.quest.dialogfragments.dialog_ok_cancel;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import com.android.androiddevteam.quest.dialogfragments.DlgFragAbstractOkCancel;

public class DlgFragCancel extends DlgFragAbstractOkCancel {

    private static final String DIALOG_TITLE = "Cancel?";
    private static final String DIALOG_TAG = "DlgFragCancel";

    private static final String OK_TEXT = "Yes";
    private static final String CANCEL_TEXT = "Cancel";

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
    protected String getCancelText() {
        return CANCEL_TEXT;
    }

    @Override
    protected void performOK(DialogInterface dialog, int id) {
        if (getTargetFragment() != null) {
            getTargetFragment().onActivityResult(getTargetRequestCode(),
                    Activity.RESULT_OK,
                    null);
            dismiss();
        }
    }

    @Override
    protected void performCancel(DialogInterface dialog, int id) {
        if (getTargetFragment() != null) {
            getTargetFragment().onActivityResult(getTargetRequestCode(),
                    Activity.RESULT_CANCELED,
                    null);
            dismiss();
        }
    }
}
