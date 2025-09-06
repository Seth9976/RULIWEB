package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.R.styleable;
import androidx.core.view.ViewCompat;

class AppCompatBackgroundHelper {
    private int mBackgroundResId;
    private TintInfo mBackgroundTint;
    private final AppCompatDrawableManager mDrawableManager;
    private TintInfo mInternalBackgroundTint;
    private TintInfo mTmpInfo;
    private final View mView;

    AppCompatBackgroundHelper(View view0) {
        this.mBackgroundResId = -1;
        this.mView = view0;
        this.mDrawableManager = AppCompatDrawableManager.get();
    }

    private boolean applyFrameworkTintUsingColorFilter(Drawable drawable0) {
        if(this.mTmpInfo == null) {
            this.mTmpInfo = new TintInfo();
        }
        TintInfo tintInfo0 = this.mTmpInfo;
        tintInfo0.clear();
        ColorStateList colorStateList0 = ViewCompat.getBackgroundTintList(this.mView);
        if(colorStateList0 != null) {
            tintInfo0.mHasTintList = true;
            tintInfo0.mTintList = colorStateList0;
        }
        PorterDuff.Mode porterDuff$Mode0 = ViewCompat.getBackgroundTintMode(this.mView);
        if(porterDuff$Mode0 != null) {
            tintInfo0.mHasTintMode = true;
            tintInfo0.mTintMode = porterDuff$Mode0;
        }
        if(!tintInfo0.mHasTintList && !tintInfo0.mHasTintMode) {
            return false;
        }
        AppCompatDrawableManager.tintDrawable(drawable0, tintInfo0, this.mView.getDrawableState());
        return true;
    }

    void applySupportBackgroundTint() {
        Drawable drawable0 = this.mView.getBackground();
        if(drawable0 != null && (!this.shouldApplyFrameworkTintUsingColorFilter() || !this.applyFrameworkTintUsingColorFilter(drawable0))) {
            TintInfo tintInfo0 = this.mBackgroundTint;
            if(tintInfo0 != null) {
                AppCompatDrawableManager.tintDrawable(drawable0, tintInfo0, this.mView.getDrawableState());
                return;
            }
            TintInfo tintInfo1 = this.mInternalBackgroundTint;
            if(tintInfo1 != null) {
                AppCompatDrawableManager.tintDrawable(drawable0, tintInfo1, this.mView.getDrawableState());
            }
        }
    }

    ColorStateList getSupportBackgroundTintList() {
        return this.mBackgroundTint == null ? null : this.mBackgroundTint.mTintList;
    }

    PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.mBackgroundTint == null ? null : this.mBackgroundTint.mTintMode;
    }

    void loadFromAttributes(AttributeSet attributeSet0, int v) {
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet0, styleable.ViewBackgroundHelper, v, 0);
        Context context0 = this.mView.getContext();
        ViewCompat.saveAttributeDataForStyleable(this.mView, context0, styleable.ViewBackgroundHelper, attributeSet0, tintTypedArray0.getWrappedTypeArray(), v, 0);
        try {
            if(tintTypedArray0.hasValue(styleable.ViewBackgroundHelper_android_background)) {
                this.mBackgroundResId = tintTypedArray0.getResourceId(styleable.ViewBackgroundHelper_android_background, -1);
                Context context1 = this.mView.getContext();
                ColorStateList colorStateList0 = this.mDrawableManager.getTintList(context1, this.mBackgroundResId);
                if(colorStateList0 != null) {
                    this.setInternalBackgroundTint(colorStateList0);
                }
            }
            if(tintTypedArray0.hasValue(styleable.ViewBackgroundHelper_backgroundTint)) {
                ColorStateList colorStateList1 = tintTypedArray0.getColorStateList(styleable.ViewBackgroundHelper_backgroundTint);
                ViewCompat.setBackgroundTintList(this.mView, colorStateList1);
            }
            if(tintTypedArray0.hasValue(styleable.ViewBackgroundHelper_backgroundTintMode)) {
                PorterDuff.Mode porterDuff$Mode0 = DrawableUtils.parseTintMode(tintTypedArray0.getInt(styleable.ViewBackgroundHelper_backgroundTintMode, -1), null);
                ViewCompat.setBackgroundTintMode(this.mView, porterDuff$Mode0);
            }
        }
        finally {
            tintTypedArray0.recycle();
        }
    }

    void onSetBackgroundDrawable(Drawable drawable0) {
        this.mBackgroundResId = -1;
        this.setInternalBackgroundTint(null);
        this.applySupportBackgroundTint();
    }

    void onSetBackgroundResource(int v) {
        this.mBackgroundResId = v;
        this.setInternalBackgroundTint((this.mDrawableManager == null ? null : this.mDrawableManager.getTintList(this.mView.getContext(), v)));
        this.applySupportBackgroundTint();
    }

    void setInternalBackgroundTint(ColorStateList colorStateList0) {
        if(colorStateList0 == null) {
            this.mInternalBackgroundTint = null;
        }
        else {
            if(this.mInternalBackgroundTint == null) {
                this.mInternalBackgroundTint = new TintInfo();
            }
            this.mInternalBackgroundTint.mTintList = colorStateList0;
            this.mInternalBackgroundTint.mHasTintList = true;
        }
        this.applySupportBackgroundTint();
    }

    void setSupportBackgroundTintList(ColorStateList colorStateList0) {
        if(this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }
        this.mBackgroundTint.mTintList = colorStateList0;
        this.mBackgroundTint.mHasTintList = true;
        this.applySupportBackgroundTint();
    }

    void setSupportBackgroundTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }
        this.mBackgroundTint.mTintMode = porterDuff$Mode0;
        this.mBackgroundTint.mHasTintMode = true;
        this.applySupportBackgroundTint();
    }

    private boolean shouldApplyFrameworkTintUsingColorFilter() {
        return Build.VERSION.SDK_INT <= 21 ? Build.VERSION.SDK_INT == 21 : this.mInternalBackgroundTint != null;
    }
}

