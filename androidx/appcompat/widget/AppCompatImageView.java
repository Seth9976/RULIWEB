package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.core.view.TintableBackgroundView;
import androidx.core.widget.TintableImageSourceView;

public class AppCompatImageView extends ImageView implements TintableBackgroundView, TintableImageSourceView {
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private boolean mHasLevel;
    private final AppCompatImageHelper mImageHelper;

    public AppCompatImageView(Context context0) {
        this(context0, null);
    }

    public AppCompatImageView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public AppCompatImageView(Context context0, AttributeSet attributeSet0, int v) {
        super(TintContextWrapper.wrap(context0), attributeSet0, v);
        this.mHasLevel = false;
        ThemeUtils.checkAppCompatTheme(this, this.getContext());
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = appCompatBackgroundHelper0;
        appCompatBackgroundHelper0.loadFromAttributes(attributeSet0, v);
        AppCompatImageHelper appCompatImageHelper0 = new AppCompatImageHelper(this);
        this.mImageHelper = appCompatImageHelper0;
        appCompatImageHelper0.loadFromAttributes(attributeSet0, v);
    }

    @Override  // android.widget.ImageView
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.applySupportBackgroundTint();
        }
        AppCompatImageHelper appCompatImageHelper0 = this.mImageHelper;
        if(appCompatImageHelper0 != null) {
            appCompatImageHelper0.applySupportImageTint();
        }
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public ColorStateList getSupportBackgroundTintList() {
        return this.mBackgroundTintHelper == null ? null : this.mBackgroundTintHelper.getSupportBackgroundTintList();
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.mBackgroundTintHelper == null ? null : this.mBackgroundTintHelper.getSupportBackgroundTintMode();
    }

    @Override  // androidx.core.widget.TintableImageSourceView
    public ColorStateList getSupportImageTintList() {
        return this.mImageHelper == null ? null : this.mImageHelper.getSupportImageTintList();
    }

    @Override  // androidx.core.widget.TintableImageSourceView
    public PorterDuff.Mode getSupportImageTintMode() {
        return this.mImageHelper == null ? null : this.mImageHelper.getSupportImageTintMode();
    }

    // 去混淆评级： 低(20)
    @Override  // android.widget.ImageView
    public boolean hasOverlappingRendering() {
        return this.mImageHelper.hasOverlappingRendering() && super.hasOverlappingRendering();
    }

    @Override  // android.view.View
    public void setBackgroundDrawable(Drawable drawable0) {
        super.setBackgroundDrawable(drawable0);
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.onSetBackgroundDrawable(drawable0);
        }
    }

    @Override  // android.view.View
    public void setBackgroundResource(int v) {
        super.setBackgroundResource(v);
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.onSetBackgroundResource(v);
        }
    }

    @Override  // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap0) {
        super.setImageBitmap(bitmap0);
        AppCompatImageHelper appCompatImageHelper0 = this.mImageHelper;
        if(appCompatImageHelper0 != null) {
            appCompatImageHelper0.applySupportImageTint();
        }
    }

    @Override  // android.widget.ImageView
    public void setImageDrawable(Drawable drawable0) {
        AppCompatImageHelper appCompatImageHelper0 = this.mImageHelper;
        if(appCompatImageHelper0 != null && drawable0 != null && !this.mHasLevel) {
            appCompatImageHelper0.obtainLevelFromDrawable(drawable0);
        }
        super.setImageDrawable(drawable0);
        AppCompatImageHelper appCompatImageHelper1 = this.mImageHelper;
        if(appCompatImageHelper1 != null) {
            appCompatImageHelper1.applySupportImageTint();
            if(!this.mHasLevel) {
                this.mImageHelper.applyImageLevel();
            }
        }
    }

    @Override  // android.widget.ImageView
    public void setImageLevel(int v) {
        super.setImageLevel(v);
        this.mHasLevel = true;
    }

    @Override  // android.widget.ImageView
    public void setImageResource(int v) {
        AppCompatImageHelper appCompatImageHelper0 = this.mImageHelper;
        if(appCompatImageHelper0 != null) {
            appCompatImageHelper0.setImageResource(v);
        }
    }

    @Override  // android.widget.ImageView
    public void setImageURI(Uri uri0) {
        super.setImageURI(uri0);
        AppCompatImageHelper appCompatImageHelper0 = this.mImageHelper;
        if(appCompatImageHelper0 != null) {
            appCompatImageHelper0.applySupportImageTint();
        }
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public void setSupportBackgroundTintList(ColorStateList colorStateList0) {
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.setSupportBackgroundTintList(colorStateList0);
        }
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public void setSupportBackgroundTintMode(PorterDuff.Mode porterDuff$Mode0) {
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.setSupportBackgroundTintMode(porterDuff$Mode0);
        }
    }

    @Override  // androidx.core.widget.TintableImageSourceView
    public void setSupportImageTintList(ColorStateList colorStateList0) {
        AppCompatImageHelper appCompatImageHelper0 = this.mImageHelper;
        if(appCompatImageHelper0 != null) {
            appCompatImageHelper0.setSupportImageTintList(colorStateList0);
        }
    }

    @Override  // androidx.core.widget.TintableImageSourceView
    public void setSupportImageTintMode(PorterDuff.Mode porterDuff$Mode0) {
        AppCompatImageHelper appCompatImageHelper0 = this.mImageHelper;
        if(appCompatImageHelper0 != null) {
            appCompatImageHelper0.setSupportImageTintMode(porterDuff$Mode0);
        }
    }
}

