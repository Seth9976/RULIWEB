package androidx.appcompat.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.style;
import androidx.appcompat.R.styleable;
import androidx.core.graphics.drawable.DrawableCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DrawerArrowDrawable extends Drawable {
    @Retention(RetentionPolicy.SOURCE)
    public @interface ArrowDirection {
    }

    public static final int ARROW_DIRECTION_END = 3;
    public static final int ARROW_DIRECTION_LEFT = 0;
    public static final int ARROW_DIRECTION_RIGHT = 1;
    public static final int ARROW_DIRECTION_START = 2;
    private static final float ARROW_HEAD_ANGLE;
    private float mArrowHeadLength;
    private float mArrowShaftLength;
    private float mBarGap;
    private float mBarLength;
    private int mDirection;
    private float mMaxCutForBarSize;
    private final Paint mPaint;
    private final Path mPath;
    private float mProgress;
    private final int mSize;
    private boolean mSpin;
    private boolean mVerticalMirror;

    static {
        DrawerArrowDrawable.ARROW_HEAD_ANGLE = 0.785398f;
    }

    public DrawerArrowDrawable(Context context0) {
        Paint paint0 = new Paint();
        this.mPaint = paint0;
        this.mPath = new Path();
        this.mVerticalMirror = false;
        this.mDirection = 2;
        paint0.setStyle(Paint.Style.STROKE);
        paint0.setStrokeJoin(Paint.Join.MITER);
        paint0.setStrokeCap(Paint.Cap.BUTT);
        paint0.setAntiAlias(true);
        TypedArray typedArray0 = context0.getTheme().obtainStyledAttributes(null, styleable.DrawerArrowToggle, attr.drawerArrowStyle, style.Base_Widget_AppCompat_DrawerArrowToggle);
        this.setColor(typedArray0.getColor(styleable.DrawerArrowToggle_color, 0));
        this.setBarThickness(typedArray0.getDimension(styleable.DrawerArrowToggle_thickness, 0.0f));
        this.setSpinEnabled(typedArray0.getBoolean(styleable.DrawerArrowToggle_spinBars, true));
        this.setGapSize(((float)Math.round(typedArray0.getDimension(styleable.DrawerArrowToggle_gapBetweenBars, 0.0f))));
        this.mSize = typedArray0.getDimensionPixelSize(styleable.DrawerArrowToggle_drawableSize, 0);
        this.mBarLength = (float)Math.round(typedArray0.getDimension(styleable.DrawerArrowToggle_barLength, 0.0f));
        this.mArrowHeadLength = (float)Math.round(typedArray0.getDimension(styleable.DrawerArrowToggle_arrowHeadLength, 0.0f));
        this.mArrowShaftLength = typedArray0.getDimension(styleable.DrawerArrowToggle_arrowShaftLength, 0.0f);
        typedArray0.recycle();
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        Rect rect0 = this.getBounds();
        boolean z = false;
        switch(this.mDirection) {
            case 0: {
                break;
            }
            case 1: {
                z = true;
                break;
            }
            case 3: {
                if(DrawableCompat.getLayoutDirection(this) == 0) {
                    z = true;
                }
                break;
            }
            default: {
                if(DrawableCompat.getLayoutDirection(this) == 1) {
                    z = true;
                }
            }
        }
        float f = this.mBarLength + (((float)Math.sqrt(this.mArrowHeadLength * this.mArrowHeadLength * 2.0f)) - this.mBarLength) * this.mProgress;
        float f1 = this.mBarLength + (this.mArrowShaftLength - this.mBarLength) * this.mProgress;
        float f2 = (float)Math.round(0.0f + (this.mMaxCutForBarSize - 0.0f) * this.mProgress);
        float f3 = 0.0f + (DrawerArrowDrawable.ARROW_HEAD_ANGLE - 0.0f) * this.mProgress;
        float f4 = z ? 0.0f : -180.0f;
        float f5 = f4 + ((z ? 180.0f : 0.0f) - f4) * this.mProgress;
        float f6 = (float)Math.round(Math.cos(f3) * ((double)f));
        float f7 = (float)Math.round(((double)f) * Math.sin(f3));
        this.mPath.rewind();
        float f8 = DrawerArrowDrawable.lerp(this.mBarGap + this.mPaint.getStrokeWidth(), -this.mMaxCutForBarSize, this.mProgress);
        this.mPath.moveTo(-f1 / 2.0f + f2, 0.0f);
        this.mPath.rLineTo(f1 - f2 * 2.0f, 0.0f);
        this.mPath.moveTo(-f1 / 2.0f, f8);
        this.mPath.rLineTo(f6, f7);
        this.mPath.moveTo(-f1 / 2.0f, -f8);
        this.mPath.rLineTo(f6, -f7);
        this.mPath.close();
        canvas0.save();
        float f9 = this.mPaint.getStrokeWidth();
        float f10 = ((float)(((int)(((float)rect0.height()) - 3.0f * f9 - this.mBarGap * 2.0f)) / 4 * 2)) + (f9 * 1.5f + this.mBarGap);
        canvas0.translate(((float)rect0.centerX()), f10);
        if(this.mSpin) {
            canvas0.rotate(f5 * ((float)((this.mVerticalMirror ^ z) == 0 ? 1 : -1)));
        }
        else if(z) {
            canvas0.rotate(180.0f);
        }
        canvas0.drawPath(this.mPath, this.mPaint);
        canvas0.restore();
    }

    public float getArrowHeadLength() {
        return this.mArrowHeadLength;
    }

    public float getArrowShaftLength() {
        return this.mArrowShaftLength;
    }

    public float getBarLength() {
        return this.mBarLength;
    }

    public float getBarThickness() {
        return this.mPaint.getStrokeWidth();
    }

    public int getColor() {
        return this.mPaint.getColor();
    }

    public int getDirection() {
        return this.mDirection;
    }

    public float getGapSize() {
        return this.mBarGap;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mSize;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mSize;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public final Paint getPaint() {
        return this.mPaint;
    }

    public float getProgress() {
        return this.mProgress;
    }

    public boolean isSpinEnabled() {
        return this.mSpin;
    }

    private static float lerp(float f, float f1, float f2) [...] // Inlined contents

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        if(v != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(v);
            this.invalidateSelf();
        }
    }

    public void setArrowHeadLength(float f) {
        if(this.mArrowHeadLength != f) {
            this.mArrowHeadLength = f;
            this.invalidateSelf();
        }
    }

    public void setArrowShaftLength(float f) {
        if(this.mArrowShaftLength != f) {
            this.mArrowShaftLength = f;
            this.invalidateSelf();
        }
    }

    public void setBarLength(float f) {
        if(this.mBarLength != f) {
            this.mBarLength = f;
            this.invalidateSelf();
        }
    }

    public void setBarThickness(float f) {
        if(this.mPaint.getStrokeWidth() != f) {
            this.mPaint.setStrokeWidth(f);
            this.mMaxCutForBarSize = (float)(((double)(f / 2.0f)) * Math.cos(DrawerArrowDrawable.ARROW_HEAD_ANGLE));
            this.invalidateSelf();
        }
    }

    public void setColor(int v) {
        if(v != this.mPaint.getColor()) {
            this.mPaint.setColor(v);
            this.invalidateSelf();
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        this.mPaint.setColorFilter(colorFilter0);
        this.invalidateSelf();
    }

    public void setDirection(int v) {
        if(v != this.mDirection) {
            this.mDirection = v;
            this.invalidateSelf();
        }
    }

    public void setGapSize(float f) {
        if(f != this.mBarGap) {
            this.mBarGap = f;
            this.invalidateSelf();
        }
    }

    public void setProgress(float f) {
        if(this.mProgress != f) {
            this.mProgress = f;
            this.invalidateSelf();
        }
    }

    public void setSpinEnabled(boolean z) {
        if(this.mSpin != z) {
            this.mSpin = z;
            this.invalidateSelf();
        }
    }

    public void setVerticalMirror(boolean z) {
        if(this.mVerticalMirror != z) {
            this.mVerticalMirror = z;
            this.invalidateSelf();
        }
    }
}

