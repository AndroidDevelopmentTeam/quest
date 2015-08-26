package com.android.zeus.ui.ratinggroup;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.android.zeus.AndroidUtils.R;

public class RatingItemWrapper extends CheckBox implements CompoundButton.OnCheckedChangeListener {

    private ChangeListener changeListener;
    private int checkedRes = R.drawable.btn_orange_hover;
    private int uncheckedRes = R.drawable.btn_green_normal;

    public RatingItemWrapper(Context context) {
        super(context);
        setOnCheckedChangeListener(this);
    }

    public RatingItemWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnCheckedChangeListener(this);
    }

    public RatingItemWrapper(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnCheckedChangeListener(this);
    }

    public void setChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    void systemCheck(boolean checked) {
        if (checked) {
            this.setBackgroundResource(checkedRes);
        } else {
            this.setBackgroundResource(uncheckedRes);
        }
        refreshDrawableState();
    }

    @Override
    public void setChecked(boolean t) {
        if (t) {
            this.setBackgroundResource(R.drawable.btn_orange_hover);
        } else {
            this.setBackgroundResource(R.drawable.btn_green_normal);
        }
        super.setChecked(t);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        changeListener.onCheckedChanged(buttonView.getId(), isChecked);
    }
}
