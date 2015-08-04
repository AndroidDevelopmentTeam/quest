package com.android.androiddevteam.quest.dialogfragments.dialog_ok_cancel;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TimePicker;
import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.dialogfragments.DlgFragAbstractOkCancel;

import java.util.Calendar;

public class DlgFragGetTime extends DlgFragAbstractOkCancel
        implements TimePicker.OnTimeChangedListener{

    private static final int ROOT_LAYOUT_ID = R.layout.dialog_get_time;
    private static final int TIME_PICKER_ID = R.id.timePicker_time;

    private static final String DIALOG_TITLE = "Выберите время";
    private static final String DIALOG_TAG = "DlgFragGetTime";

    private static final String OK_TEXT = "OK";
    private static final String CANCEL_TEXT = "Отмена";

    private int hour = -1;
    private int minute;

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
        View rootView = getActivity().getLayoutInflater().inflate(ROOT_LAYOUT_ID, null);
        ((TimePicker) rootView.findViewById(TIME_PICKER_ID)).setIs24HourView(true);
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
    protected String getCancelText() {
        return CANCEL_TEXT;
    }

    @Override
    protected void performOK(DialogInterface dialog, int id) {
        if (getTargetFragment() != null){
            Intent intent = new Intent();
            if (hour < 0){
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
            }

//            intent.putExtra(FragCreate.EXTRA_INT_HOUR, hour);
//            intent.putExtra(FragCreate.EXTRA_INT_MINUTE, minute);

            getTargetFragment().onActivityResult(getTargetRequestCode(),
                    Activity.RESULT_OK,
                    intent);
            dismiss();
        }
    }

    @Override
    protected void performCancel(DialogInterface dialog, int id) {
        if (getTargetFragment() != null){
            getTargetFragment().onActivityResult(getTargetRequestCode(),
                    Activity.RESULT_CANCELED,
                    null);
            dismiss();
        }
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        hour = hourOfDay;
        this.minute = minute;
    }
}
