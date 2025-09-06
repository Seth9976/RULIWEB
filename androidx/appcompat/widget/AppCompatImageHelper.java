package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.R.styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ImageViewCompat;

public class AppCompatImageHelper {
    private TintInfo mImageTint;
    private TintInfo mInternalImageTint;
    private int mLevel;
    private TintInfo mTmpInfo;
    private final ImageView mView;

    public AppCompatImageHelper(ImageView imageView0) {
        this.mLevel = 0;
        this.mView = imageView0;
    }

    private boolean applyFrameworkTintUsingColorFilter(Drawable drawable0) {
        if(this.mTmpInfo == null) {
            this.mTmpInfo = new TintInfo();
        }
        TintInfo tintInfo0 = this.mTmpInfo;
        tintInfo0.clear();
        ColorStateList colorStateList0 = ImageViewCompat.getImageTintList(this.mView);
        if(colorStateList0 != null) {
            tintInfo0.mHasTintList = true;
            tintInfo0.mTintList = colorStateList0;
        }
        PorterDuff.Mode porterDuff$Mode0 = ImageViewCompat.getImageTintMode(this.mView);
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

    void applyImageLevel() {
        if(this.mView.getDrawable() != null) {
            this.mView.getDrawable().setLevel(this.mLevel);
        }
    }

    void applySupportImageTint() {
        Drawable drawable0 = this.mView.getDrawable();
        if(drawable0 != null) {
            DrawableUtils.fixDrawable(drawable0);
        }
        if(drawable0 != null && (!this.shouldApplyFrameworkTintUsingColorFilter() || !this.applyFrameworkTintUsingColorFilter(drawable0))) {
            TintInfo tintInfo0 = this.mImageTint;
            if(tintInfo0 != null) {
                AppCompatDrawableManager.tintDrawable(drawable0, tintInfo0, this.mView.getDrawableState());
                return;
            }
            TintInfo tintInfo1 = this.mInternalImageTint;
            if(tintInfo1 != null) {
                AppCompatDrawableManager.tintDrawable(drawable0, tintInfo1, this.mView.getDrawableState());
            }
        }
    }

    ColorStateList getSupportImageTintList() {
        return this.mImageTint == null ? null : this.mImageTint.mTintList;
    }

    PorterDuff.Mode getSupportImageTintMode() {
        return this.mImageTint == null ? null : this.mImageTint.mTintMode;
    }

    boolean hasOverlappingRendering() {
        return !(this.mView.getBackground() instanceof RippleDrawable);
    }

    public void loadFromAttributes(AttributeSet attributeSet0, int v) {
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet0, styleable.AppCompatImageView, v, 0);
        Context context0 = this.mView.getContext();
        ViewCompat.saveAttributeDataForStyleable(this.mView, context0, styleable.AppCompatImageView, attributeSet0, tintTypedArray0.getWrappedTypeArray(), v, 0);
        try {
            Drawable drawable0 = this.mView.getDrawable();
            if(drawable0 == null) {
                int v2 = tintTypedArray0.getResourceId(styleable.AppCompatImageView_srcCompat, -1);
                if(v2 != -1) {
                    drawable0 = AppCompatResources.getDrawable(this.mView.getContext(), v2);
                    if(drawable0 != null) {
                        this.mView.setImageDrawable(drawable0);
                    }
                }
            }
            if(drawable0 != null) {
                DrawableUtils.fixDrawable(drawable0);
            }
            if(tintTypedArray0.hasValue(styleable.AppCompatImageView_tint)) {
                ColorStateList colorStateList0 = tintTypedArray0.getColorStateList(styleable.AppCompatImageView_tint);
                ImageViewCompat.setImageTintList(this.mView, colorStateList0);
            }
            if(tintTypedArray0.hasValue(styleable.AppCompatImageView_tintMode)) {
                PorterDuff.Mode porterDuff$Mode0 = DrawableUtils.parseTintMode(tintTypedArray0.getInt(styleable.AppCompatImageView_tintMode, -1), null);
                ImageViewCompat.setImageTintMode(this.mView, porterDuff$Mode0);
            }
        }
        finally {
            tintTypedArray0.recycle();
        }
    }

    void obtainLevelFromDrawable(Drawable drawable0) {
        this.mLevel = drawable0.getLevel();
    }

    public void setImageResource(int v) {
        if(v == 0) {
            this.mView.setImageDrawable(null);
        }
        else {
            Drawable drawable0 = AppCompatResources.getDrawable(this.mView.getContext(), v);
            if(drawable0 != null) {
                DrawableUtils.fixDrawable(drawable0);
            }
            this.mView.setImageDrawable(drawable0);
        }
        this.applySupportImageTint();
    }

    void setInternalImageTint(ColorStateList colorStateList0) {
        if(colorStateList0 == null) {
            this.mInternalImageTint = null;
        }
        else {
            if(this.mInternalImageTint == null) {
                this.mInternalImageTint = new TintInfo();
            }
            this.mInternalImageTint.mTintList = colorStateList0;
            this.mInternalImageTint.mHasTintList = true;
        }
        this.applySupportImageTint();
    }

    void setSupportImageTintList(ColorStateList colorStateList0) {
        if(this.mImageTint == null) {
            this.mImageTint = new TintInfo();
        }
        this.mImageTint.mTintList = colorStateList0;
        this.mImageTint.mHasTintList = true;
        this.applySupportImageTint();
    }

    void setSupportImageTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.mImageTint == null) {
            this.mImageTint = new TintInfo();
        }
        this.mImageTint.mTintMode = porterDuff$Mode0;
        this.mImageTint.mHasTintMode = true;
        this.applySupportImageTint();
    }

    private boolean shouldApplyFrameworkTintUsingColorFilter() {
        return Build.VERSION.SDK_INT <= 21 ? Build.VERSION.SDK_INT == 21 : this.mInternalImageTint != null;
    }
}

