package com.androiddevteam.quest.dialogfragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

public abstract class DlgFragAbstractOk extends DlgFragBase {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (getTitleView() != null){
            builder.setCustomTitle(getTitleView());
        } else if (!TextUtils.isEmpty(getTitle())){
            builder.setTitle(getTitle());
        }
        if (getRootView() != null) {
            builder.setView(getRootView());
        }
        builder.setPositiveButton(getOKText(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                performOK(dialog, id);
            }
        });
        return builder.create();
    }

    protected abstract String getOKText();

    protected abstract void performOK(DialogInterface dialog, int id);
}
