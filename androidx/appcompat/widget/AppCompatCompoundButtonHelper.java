package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.appcompat.R.styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.CompoundButtonCompat;

class AppCompatCompoundButtonHelper {
    private ColorStateList mButtonTintList;
    private PorterDuff.Mode mButtonTintMode;
    private boolean mHasButtonTint;
    private boolean mHasButtonTintMode;
    private boolean mSkipNextApply;
    private final CompoundButton mView;

    AppCompatCompoundButtonHelper(CompoundButton compoundButton0) {
        this.mButtonTintList = null;
        this.mButtonTintMode = null;
        this.mHasButtonTint = false;
        this.mHasButtonTintMode = false;
        this.mView = compoundButton0;
    }

    void applyButtonTint() {
        Drawable drawable0 = CompoundButtonCompat.getButtonDrawable(this.mView);
        if(drawable0 != null && (this.mHasButtonTint || this.mHasButtonTintMode)) {
            Drawable drawable1 = DrawableCompat.wrap(drawable0).mutate();
            if(this.mHasButtonTint) {
                DrawableCompat.setTintList(drawable1, this.mButtonTintList);
            }
            if(this.mHasButtonTintMode) {
                DrawableCompat.setTintMode(drawable1, this.mButtonTintMode);
            }
            if(drawable1.isStateful()) {
                drawable1.setState(this.mView.getDrawableState());
            }
            this.mView.setButtonDrawable(drawable1);
        }
    }

    ColorStateList getSupportButtonTintList() {
        return this.mButtonTintList;
    }

    PorterDuff.Mode getSupportButtonTintMode() {
        return this.mButtonTintMode;
    }

    void loadFromAttributes(AttributeSet attributeSet0, int v) {
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet0, styleable.CompoundButton, v, 0);
        Context context0 = this.mView.getContext();
        ViewCompat.saveAttributeDataForStyleable(this.mView, context0, styleable.CompoundButton, attributeSet0, tintTypedArray0.getWrappedTypeArray(), v, 0);
        try {
            if(tintTypedArray0.hasValue(styleable.CompoundButton_buttonCompat)) {
                int v2 = tintTypedArray0.getResourceId(styleable.CompoundButton_buttonCompat, 0);
                if(v2 != 0) {
                    try {
                        Drawable drawable0 = AppCompatResources.getDrawable(this.mView.getContext(), v2);
                        this.mView.setButtonDrawable(drawable0);
                        goto label_15;
                    }
                    catch(Resources.NotFoundException unused_ex) {
                    }
                }
                goto label_10;
            }
            else {
            label_10:
                if(tintTypedArray0.hasValue(styleable.CompoundButton_android_button)) {
                    int v3 = tintTypedArray0.getResourceId(styleable.CompoundButton_android_button, 0);
                    if(v3 != 0) {
                        Drawable drawable1 = AppCompatResources.getDrawable(this.mView.getContext(), v3);
                        this.mView.setButtonDrawable(drawable1);
                    }
                }
            }
        label_15:
            if(tintTypedArray0.hasValue(styleable.CompoundButton_buttonTint)) {
                ColorStateList colorStateList0 = tintTypedArray0.getColorStateList(styleable.CompoundButton_buttonTint);
                CompoundButtonCompat.setButtonTintList(this.mView, colorStateList0);
            }
            if(tintTypedArray0.hasValue(styleable.CompoundButton_buttonTintMode)) {
                PorterDuff.Mode porterDuff$Mode0 = DrawableUtils.parseTintMode(tintTypedArray0.getInt(styleable.CompoundButton_buttonTintMode, -1), null);
                CompoundButtonCompat.setButtonTintMode(this.mView, porterDuff$Mode0);
            }
        }
        finally {
            tintTypedArray0.recycle();
        }
    }

    void onSetButtonDrawable() {
        if(this.mSkipNextApply) {
            this.mSkipNextApply = false;
            return;
        }
        this.mSkipNextApply = true;
        this.applyButtonTint();
    }

    void setSupportButtonTintList(ColorStateList colorStateList0) {
        this.mButtonTintList = colorStateList0;
        this.mHasButtonTint = true;
        this.applyButtonTint();
    }

    void setSupportButtonTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mButtonTintMode = porterDuff$Mode0;
        this.mHasButtonTintMode = true;
        this.applyButtonTint();
    }
}

