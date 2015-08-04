package com.android.androiddevteam.quest.dialogfragments.dialog_ok_cancel;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import com.android.androiddevteam.quest.R;
import com.android.androiddevteam.quest.dialogfragments.DlgFragAbstractOkCancel;

import java.util.Calendar;

public class DlgFragGetDate extends DlgFragAbstractOkCancel
        implements DatePicker.OnDateChangedListener{

    private static final int ROOT_LAYOUT_ID = R.layout.dialog_get_date;
    private static final int DATE_PICKER_ID = R.id.datePicker_date;

    private static final String DIALOG_TITLE = "Выберите дату";
    private static final String DIALOG_TAG = "DlgFragGetDate";

    private static final String OK_TEXT = "OK";
    private static final String CANCEL_TEXT = "Отмена";

    private int dayOfMonth;
    private int monthOfYear;
    private int year;

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
        Calendar calendar = Calendar.getInstance();
        ((DatePicker) rootView.findViewById(DATE_PICKER_ID)).init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                this);
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
            if (year > 0){
//                intent.putExtra(FragInternal.EXTRA_INT_DAY_OF_MONTH, dayOfMonth);
//                intent.putExtra(FragInternal.EXTRA_INT_MONTH_OF_YEAR, monthOfYear);
//                intent.putExtra(FragInternal.EXTRA_INT_YEAR, year);
            } else {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                monthOfYear = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

//                intent.putExtra(FragInternal.EXTRA_INT_DAY_OF_MONTH, dayOfMonth);
//                intent.putExtra(FragInternal.EXTRA_INT_MONTH_OF_YEAR, monthOfYear);
//                intent.putExtra(FragInternal.EXTRA_INT_YEAR, year);
            }

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
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.monthOfYear = monthOfYear;
        this.dayOfMonth = dayOfMonth;
    }
}
