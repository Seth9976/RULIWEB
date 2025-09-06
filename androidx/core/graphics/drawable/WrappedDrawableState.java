package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;

final class WrappedDrawableState extends Drawable.ConstantState {
    int mChangingConfigurations;
    Drawable.ConstantState mDrawableState;
    ColorStateList mTint;
    PorterDuff.Mode mTintMode;

    WrappedDrawableState(WrappedDrawableState wrappedDrawableState0) {
        this.mTint = null;
        this.mTintMode = WrappedDrawableApi14.DEFAULT_TINT_MODE;
        if(wrappedDrawableState0 != null) {
            this.mChangingConfigurations = wrappedDrawableState0.mChangingConfigurations;
            this.mDrawableState = wrappedDrawableState0.mDrawableState;
            this.mTint = wrappedDrawableState0.mTint;
            this.mTintMode = wrappedDrawableState0.mTintMode;
        }
    }

    boolean canConstantState() {
        return this.mDrawableState != null;
    }

    @Override  // android.graphics.drawable.Drawable$ConstantState
    public int getChangingConfigurations() {
        return this.mDrawableState == null ? this.mChangingConfigurations : this.mChangingConfigurations | this.mDrawableState.getChangingConfigurations();
    }

    @Override  // android.graphics.drawable.Drawable$ConstantState
    public Drawable newDrawable() {
        return this.newDrawable(null);
    }

    @Override  // android.graphics.drawable.Drawable$ConstantState
    public Drawable newDrawable(Resources resources0) {
        return new WrappedDrawableApi21(this, resources0);
    }
}

