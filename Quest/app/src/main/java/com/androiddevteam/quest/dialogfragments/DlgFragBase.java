package com.androiddevteam.quest.dialogfragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.View;

public abstract class DlgFragBase extends DialogFragment {

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
        return builder.create();
    }

    protected abstract View getTitleView();

    protected abstract String getTitle();

    protected abstract View getRootView();

    public abstract String getDialogTag();
}
