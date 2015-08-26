package com.android.zeus.roundedBitmapAsDrawable;

import android.graphics.*;
import android.graphics.drawable.Drawable;

public class RoundedDrawable extends Drawable {
    private final Bitmap bitmap;
    private final Paint paint;
    private final RectF rectF;
    private final int bitmapWidth;
    private final int bitmapHeight;

    public RoundedDrawable(Bitmap bitmap) {
        this.bitmap = bitmap;
        rectF = new RectF();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        final BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);

        // NOTE: we assume bitmap is properly scaled to current density
        bitmapWidth = this.bitmap.getWidth();
        bitmapHeight = this.bitmap.getHeight();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawOval(rectF, paint);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

        rectF.set(bounds);
    }

    @Override
    public void setAlpha(int alpha) {
        if (paint.getAlpha() != alpha) {
            paint.setAlpha(alpha);
            invalidateSelf();
        }
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return bitmapWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return bitmapHeight;
    }

    public void setAntiAlias(boolean aa) {
        paint.setAntiAlias(aa);
        invalidateSelf();
    }

    @Override
    public void setFilterBitmap(boolean filter) {
        paint.setFilterBitmap(filter);
        invalidateSelf();
    }

    @Override
    public void setDither(boolean dither) {
        paint.setDither(dither);
        invalidateSelf();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void recycle() {
        bitmap.recycle();
        paint.reset();
    }

    // TODO allow set and use target density, mutate, constant state, changing configurations, etc.
}
