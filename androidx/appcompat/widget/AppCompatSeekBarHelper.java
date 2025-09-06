package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.appcompat.R.styleable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;

class AppCompatSeekBarHelper extends AppCompatProgressBarHelper {
    private boolean mHasTickMarkTint;
    private boolean mHasTickMarkTintMode;
    private Drawable mTickMark;
    private ColorStateList mTickMarkTintList;
    private PorterDuff.Mode mTickMarkTintMode;
    private final SeekBar mView;

    AppCompatSeekBarHelper(SeekBar seekBar0) {
        super(seekBar0);
        this.mTickMarkTintList = null;
        this.mTickMarkTintMode = null;
        this.mHasTickMarkTint = false;
        this.mHasTickMarkTintMode = false;
        this.mView = seekBar0;
    }

    private void applyTickMarkTint() {
        Drawable drawable0 = this.mTickMark;
        if(drawable0 != null && (this.mHasTickMarkTint || this.mHasTickMarkTintMode)) {
            Drawable drawable1 = DrawableCompat.wrap(drawable0.mutate());
            this.mTickMark = drawable1;
            if(this.mHasTickMarkTint) {
                DrawableCompat.setTintList(drawable1, this.mTickMarkTintList);
            }
            if(this.mHasTickMarkTintMode) {
                DrawableCompat.setTintMode(this.mTickMark, this.mTickMarkTintMode);
            }
            if(this.mTickMark.isStateful()) {
                this.mTickMark.setState(this.mView.getDrawableState());
            }
        }
    }

    void drawTickMarks(Canvas canvas0) {
        if(this.mTickMark != null) {
            int v = this.mView.getMax();
            int v1 = 1;
            if(v > 1) {
                int v2 = this.mTickMark.getIntrinsicWidth();
                int v3 = this.mTickMark.getIntrinsicHeight();
                int v4 = v2 < 0 ? 1 : v2 / 2;
                if(v3 >= 0) {
                    v1 = v3 / 2;
                }
                this.mTickMark.setBounds(-v4, -v1, v4, v1);
                int v5 = this.mView.getWidth();
                int v6 = this.mView.getPaddingLeft();
                int v7 = this.mView.getPaddingRight();
                int v8 = canvas0.save();
                canvas0.translate(((float)this.mView.getPaddingLeft()), ((float)(this.mView.getHeight() / 2)));
                for(int v9 = 0; v9 <= v; ++v9) {
                    this.mTickMark.draw(canvas0);
                    canvas0.translate(((float)(v5 - v6 - v7)) / ((float)v), 0.0f);
                }
                canvas0.restoreToCount(v8);
            }
        }
    }

    void drawableStateChanged() {
        Drawable drawable0 = this.mTickMark;
        if(drawable0 != null && drawable0.isStateful() && drawable0.setState(this.mView.getDrawableState())) {
            this.mView.invalidateDrawable(drawable0);
        }
    }

    Drawable getTickMark() {
        return this.mTickMark;
    }

    ColorStateList getTickMarkTintList() {
        return this.mTickMarkTintList;
    }

    PorterDuff.Mode getTickMarkTintMode() {
        return this.mTickMarkTintMode;
    }

    void jumpDrawablesToCurrentState() {
        Drawable drawable0 = this.mTickMark;
        if(drawable0 != null) {
            drawable0.jumpToCurrentState();
        }
    }

    @Override  // androidx.appcompat.widget.AppCompatProgressBarHelper
    void loadFromAttributes(AttributeSet attributeSet0, int v) {
        super.loadFromAttributes(attributeSet0, v);
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet0, styleable.AppCompatSeekBar, v, 0);
        Context context0 = this.mView.getContext();
        ViewCompat.saveAttributeDataForStyleable(this.mView, context0, styleable.AppCompatSeekBar, attributeSet0, tintTypedArray0.getWrappedTypeArray(), v, 0);
        Drawable drawable0 = tintTypedArray0.getDrawableIfKnown(styleable.AppCompatSeekBar_android_thumb);
        if(drawable0 != null) {
            this.mView.setThumb(drawable0);
        }
        this.setTickMark(tintTypedArray0.getDrawable(styleable.AppCompatSeekBar_tickMark));
        if(tintTypedArray0.hasValue(styleable.AppCompatSeekBar_tickMarkTintMode)) {
            this.mTickMarkTintMode = DrawableUtils.parseTintMode(tintTypedArray0.getInt(styleable.AppCompatSeekBar_tickMarkTintMode, -1), this.mTickMarkTintMode);
            this.mHasTickMarkTintMode = true;
        }
        if(tintTypedArray0.hasValue(styleable.AppCompatSeekBar_tickMarkTint)) {
            this.mTickMarkTintList = tintTypedArray0.getColorStateList(styleable.AppCompatSeekBar_tickMarkTint);
            this.mHasTickMarkTint = true;
        }
        tintTypedArray0.recycle();
        this.applyTickMarkTint();
    }

    void setTickMark(Drawable drawable0) {
        Drawable drawable1 = this.mTickMark;
        if(drawable1 != null) {
            drawable1.setCallback(null);
        }
        this.mTickMark = drawable0;
        if(drawable0 != null) {
            drawable0.setCallback(this.mView);
            DrawableCompat.setLayoutDirection(drawable0, this.mView.getLayoutDirection());
            if(drawable0.isStateful()) {
                drawable0.setState(this.mView.getDrawableState());
            }
            this.applyTickMarkTint();
        }
        this.mView.invalidate();
    }

    void setTickMarkTintList(ColorStateList colorStateList0) {
        this.mTickMarkTintList = colorStateList0;
        this.mHasTickMarkTint = true;
        this.applyTickMarkTint();
    }

    void setTickMarkTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mTickMarkTintMode = porterDuff$Mode0;
        this.mHasTickMarkTintMode = true;
        this.applyTickMarkTint();
    }
}

