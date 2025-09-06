package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;

class WrappedDrawableApi14 extends Drawable implements Drawable.Callback, TintAwareDrawable, WrappedDrawable {
    static final PorterDuff.Mode DEFAULT_TINT_MODE;
    private boolean mColorFilterSet;
    private int mCurrentColor;
    private PorterDuff.Mode mCurrentMode;
    Drawable mDrawable;
    private boolean mMutated;
    WrappedDrawableState mState;

    static {
        WrappedDrawableApi14.DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    }

    WrappedDrawableApi14(Drawable drawable0) {
        this.mState = this.mutateConstantState();
        this.setWrappedDrawable(drawable0);
    }

    WrappedDrawableApi14(WrappedDrawableState wrappedDrawableState0, Resources resources0) {
        this.mState = wrappedDrawableState0;
        this.updateLocalState(resources0);
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        this.mDrawable.draw(canvas0);
    }

    @Override  // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        int v = super.getChangingConfigurations();
        WrappedDrawableState wrappedDrawableState0 = this.mState;
        return wrappedDrawableState0 == null ? v | this.mDrawable.getChangingConfigurations() : v | wrappedDrawableState0.getChangingConfigurations() | this.mDrawable.getChangingConfigurations();
    }

    @Override  // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if(this.mState != null && this.mState.canConstantState()) {
            WrappedDrawableState wrappedDrawableState0 = this.mState;
            wrappedDrawableState0.mChangingConfigurations = this.getChangingConfigurations();
            return this.mState;
        }
        return null;
    }

    @Override  // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.mDrawable.getCurrent();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mDrawable.getIntrinsicHeight();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mDrawable.getIntrinsicWidth();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getLayoutDirection() {
        return DrawableCompat.getLayoutDirection(this.mDrawable);
    }

    @Override  // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.mDrawable.getMinimumHeight();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.mDrawable.getMinimumWidth();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.mDrawable.getOpacity();
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect0) {
        return this.mDrawable.getPadding(rect0);
    }

    @Override  // android.graphics.drawable.Drawable
    public int[] getState() {
        return this.mDrawable.getState();
    }

    @Override  // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        return this.mDrawable.getTransparentRegion();
    }

    @Override  // androidx.core.graphics.drawable.WrappedDrawable
    public final Drawable getWrappedDrawable() {
        return this.mDrawable;
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void invalidateDrawable(Drawable drawable0) {
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return DrawableCompat.isAutoMirrored(this.mDrawable);
    }

    protected boolean isCompatTintEnabled() {
        return true;
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean isStateful() {
        if(this.isCompatTintEnabled()) {
            WrappedDrawableState wrappedDrawableState0 = this.mState;
            if(wrappedDrawableState0 != null) {
                return wrappedDrawableState0.mTint != null && wrappedDrawableState0.mTint.isStateful() || this.mDrawable.isStateful();
            }
        }
        throw new NullPointerException();
    }

    @Override  // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.mDrawable.jumpToCurrentState();
    }

    @Override  // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if(!this.mMutated && super.mutate() == this) {
            this.mState = this.mutateConstantState();
            Drawable drawable0 = this.mDrawable;
            if(drawable0 != null) {
                drawable0.mutate();
            }
            WrappedDrawableState wrappedDrawableState0 = this.mState;
            if(wrappedDrawableState0 != null) {
                wrappedDrawableState0.mDrawableState = this.mDrawable == null ? null : this.mDrawable.getConstantState();
            }
            this.mMutated = true;
        }
        return this;
    }

    private WrappedDrawableState mutateConstantState() {
        return new WrappedDrawableState(this.mState);
    }

    @Override  // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect0) {
        Drawable drawable0 = this.mDrawable;
        if(drawable0 != null) {
            drawable0.setBounds(rect0);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int v) {
        return DrawableCompat.setLayoutDirection(this.mDrawable, v);
    }

    @Override  // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int v) {
        return this.mDrawable.setLevel(v);
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void scheduleDrawable(Drawable drawable0, Runnable runnable0, long v) {
        this.scheduleSelf(runnable0, v);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        this.mDrawable.setAlpha(v);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        DrawableCompat.setAutoMirrored(this.mDrawable, z);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int v) {
        this.mDrawable.setChangingConfigurations(v);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        this.mDrawable.setColorFilter(colorFilter0);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.mDrawable.setDither(z);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.mDrawable.setFilterBitmap(z);
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean setState(int[] arr_v) {
        boolean z = this.mDrawable.setState(arr_v);
        return this.updateTint(arr_v) || z;
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTint(int v) {
        this.setTintList(ColorStateList.valueOf(v));
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList0) {
        this.mState.mTint = colorStateList0;
        this.updateTint(this.getState());
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mState.mTintMode = porterDuff$Mode0;
        this.updateTint(this.getState());
    }

    // 去混淆评级： 低(20)
    @Override  // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z1) {
        return super.setVisible(z, z1) || this.mDrawable.setVisible(z, z1);
    }

    @Override  // androidx.core.graphics.drawable.WrappedDrawable
    public final void setWrappedDrawable(Drawable drawable0) {
        Drawable drawable1 = this.mDrawable;
        if(drawable1 != null) {
            drawable1.setCallback(null);
        }
        this.mDrawable = drawable0;
        if(drawable0 != null) {
            drawable0.setCallback(this);
            this.setVisible(drawable0.isVisible(), true);
            this.setState(drawable0.getState());
            this.setLevel(drawable0.getLevel());
            this.setBounds(drawable0.getBounds());
            WrappedDrawableState wrappedDrawableState0 = this.mState;
            if(wrappedDrawableState0 != null) {
                wrappedDrawableState0.mDrawableState = drawable0.getConstantState();
            }
        }
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void unscheduleDrawable(Drawable drawable0, Runnable runnable0) {
        this.unscheduleSelf(runnable0);
    }

    private void updateLocalState(Resources resources0) {
        if(this.mState != null && this.mState.mDrawableState != null) {
            this.setWrappedDrawable(this.mState.mDrawableState.newDrawable(resources0));
        }
    }

    private boolean updateTint(int[] arr_v) {
        if(!this.isCompatTintEnabled()) {
            return false;
        }
        ColorStateList colorStateList0 = this.mState.mTint;
        PorterDuff.Mode porterDuff$Mode0 = this.mState.mTintMode;
        if(colorStateList0 == null || porterDuff$Mode0 == null) {
            this.mColorFilterSet = false;
            this.clearColorFilter();
        }
        else {
            int v = colorStateList0.getColorForState(arr_v, colorStateList0.getDefaultColor());
            if(!this.mColorFilterSet || v != this.mCurrentColor || porterDuff$Mode0 != this.mCurrentMode) {
                this.setColorFilter(v, porterDuff$Mode0);
                this.mCurrentColor = v;
                this.mCurrentMode = porterDuff$Mode0;
                this.mColorFilterSet = true;
                return true;
            }
        }
        return false;
    }
}

