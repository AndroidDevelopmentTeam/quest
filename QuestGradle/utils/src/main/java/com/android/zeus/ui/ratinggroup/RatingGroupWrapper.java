package com.android.zeus.ui.ratinggroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class RatingGroupWrapper extends LinearLayout implements ChangeListener {

    private int rating;

    public RatingGroupWrapper(Context context) {
        super(context);
    }

    public RatingGroupWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RatingGroupWrapper(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setRating(int rating) {
        this.rating = rating;
        setRating(rating - 1, true);
    }

    private void setRating(int rating, boolean nothing) {
        this.rating = (rating + 1);
        for (int i = 0; i <= rating; ++i) {
            if (getChildAt(i).getId() == View.NO_ID) {
                getChildAt(i).setId(getChildAt(i).hashCode());
            }
            ((RatingItemWrapper) getChildAt(i)).systemCheck(true);
        }
        for (int i = (rating + 1); i < getChildCount(); ++i) {
            if (getChildAt(i).getId() == View.NO_ID) {
                getChildAt(i).setId(getChildAt(i).hashCode());
            }
            ((RatingItemWrapper) getChildAt(i)).systemCheck(false);
        }
    }

    public int getRating() {
        return rating;
    }

    @Override
    public void onCheckedChanged(int checkedId, boolean isChecked) {
        setRating(indexOfChild(findViewById(checkedId)), true);
    }
}
