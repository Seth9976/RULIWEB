package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import androidx.appcompat.R.styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.CheckedTextViewCompat;

class AppCompatCheckedTextViewHelper {
    private ColorStateList mCheckMarkTintList;
    private PorterDuff.Mode mCheckMarkTintMode;
    private boolean mHasCheckMarkTint;
    private boolean mHasCheckMarkTintMode;
    private boolean mSkipNextApply;
    private final CheckedTextView mView;

    AppCompatCheckedTextViewHelper(CheckedTextView checkedTextView0) {
        this.mCheckMarkTintList = null;
        this.mCheckMarkTintMode = null;
        this.mHasCheckMarkTint = false;
        this.mHasCheckMarkTintMode = false;
        this.mView = checkedTextView0;
    }

    void applyCheckMarkTint() {
        Drawable drawable0 = CheckedTextViewCompat.getCheckMarkDrawable(this.mView);
        if(drawable0 != null && (this.mHasCheckMarkTint || this.mHasCheckMarkTintMode)) {
            Drawable drawable1 = DrawableCompat.wrap(drawable0).mutate();
            if(this.mHasCheckMarkTint) {
                DrawableCompat.setTintList(drawable1, this.mCheckMarkTintList);
            }
            if(this.mHasCheckMarkTintMode) {
                DrawableCompat.setTintMode(drawable1, this.mCheckMarkTintMode);
            }
            if(drawable1.isStateful()) {
                drawable1.setState(this.mView.getDrawableState());
            }
            this.mView.setCheckMarkDrawable(drawable1);
        }
    }

    ColorStateList getSupportCheckMarkTintList() {
        return this.mCheckMarkTintList;
    }

    PorterDuff.Mode getSupportCheckMarkTintMode() {
        return this.mCheckMarkTintMode;
    }

    void loadFromAttributes(AttributeSet attributeSet0, int v) {
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet0, styleable.CheckedTextView, v, 0);
        Context context0 = this.mView.getContext();
        ViewCompat.saveAttributeDataForStyleable(this.mView, context0, styleable.CheckedTextView, attributeSet0, tintTypedArray0.getWrappedTypeArray(), v, 0);
        try {
            if(tintTypedArray0.hasValue(styleable.CheckedTextView_checkMarkCompat)) {
                int v2 = tintTypedArray0.getResourceId(styleable.CheckedTextView_checkMarkCompat, 0);
                if(v2 != 0) {
                    try {
                        Drawable drawable0 = AppCompatResources.getDrawable(this.mView.getContext(), v2);
                        this.mView.setCheckMarkDrawable(drawable0);
                        goto label_15;
                    }
                    catch(Resources.NotFoundException unused_ex) {
                    }
                }
                goto label_10;
            }
            else {
            label_10:
                if(tintTypedArray0.hasValue(styleable.CheckedTextView_android_checkMark)) {
                    int v3 = tintTypedArray0.getResourceId(styleable.CheckedTextView_android_checkMark, 0);
                    if(v3 != 0) {
                        Drawable drawable1 = AppCompatResources.getDrawable(this.mView.getContext(), v3);
                        this.mView.setCheckMarkDrawable(drawable1);
                    }
                }
            }
        label_15:
            if(tintTypedArray0.hasValue(styleable.CheckedTextView_checkMarkTint)) {
                ColorStateList colorStateList0 = tintTypedArray0.getColorStateList(styleable.CheckedTextView_checkMarkTint);
                CheckedTextViewCompat.setCheckMarkTintList(this.mView, colorStateList0);
            }
            if(tintTypedArray0.hasValue(styleable.CheckedTextView_checkMarkTintMode)) {
                PorterDuff.Mode porterDuff$Mode0 = DrawableUtils.parseTintMode(tintTypedArray0.getInt(styleable.CheckedTextView_checkMarkTintMode, -1), null);
                CheckedTextViewCompat.setCheckMarkTintMode(this.mView, porterDuff$Mode0);
            }
        }
        finally {
            tintTypedArray0.recycle();
        }
    }

    void onSetCheckMarkDrawable() {
        if(this.mSkipNextApply) {
            this.mSkipNextApply = false;
            return;
        }
        this.mSkipNextApply = true;
        this.applyCheckMarkTint();
    }

    void setSupportCheckMarkTintList(ColorStateList colorStateList0) {
        this.mCheckMarkTintList = colorStateList0;
        this.mHasCheckMarkTint = true;
        this.applyCheckMarkTint();
    }

    void setSupportCheckMarkTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mCheckMarkTintMode = porterDuff$Mode0;
        this.mHasCheckMarkTintMode = true;
        this.applyCheckMarkTint();
    }
}

