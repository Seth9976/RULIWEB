package androidx.appcompat.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;

public class DrawableWrapperCompat extends Drawable implements Drawable.Callback {
    private Drawable mDrawable;

    public DrawableWrapperCompat(Drawable drawable0) {
        this.setDrawable(drawable0);
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        this.mDrawable.draw(canvas0);
    }

    @Override  // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return this.mDrawable.getChangingConfigurations();
    }

    @Override  // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.mDrawable.getCurrent();
    }

    public Drawable getDrawable() {
        return this.mDrawable;
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

    @Override  // android.graphics.drawable.Drawable$Callback
    public void invalidateDrawable(Drawable drawable0) {
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return DrawableCompat.isAutoMirrored(this.mDrawable);
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.mDrawable.isStateful();
    }

    @Override  // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.mDrawable.jumpToCurrentState();
    }

    @Override  // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect0) {
        this.mDrawable.setBounds(rect0);
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

    public void setDrawable(Drawable drawable0) {
        Drawable drawable1 = this.mDrawable;
        if(drawable1 != null) {
            drawable1.setCallback(null);
        }
        this.mDrawable = drawable0;
        if(drawable0 != null) {
            drawable0.setCallback(this);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.mDrawable.setFilterBitmap(z);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f1) {
        DrawableCompat.setHotspot(this.mDrawable, f, f1);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setHotspotBounds(int v, int v1, int v2, int v3) {
        DrawableCompat.setHotspotBounds(this.mDrawable, v, v1, v2, v3);
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean setState(int[] arr_v) {
        return this.mDrawable.setState(arr_v);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setTint(int v) {
        DrawableCompat.setTint(this.mDrawable, v);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList0) {
        DrawableCompat.setTintList(this.mDrawable, colorStateList0);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode porterDuff$Mode0) {
        DrawableCompat.setTintMode(this.mDrawable, porterDuff$Mode0);
    }

    // 去混淆评级： 低(20)
    @Override  // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z1) {
        return super.setVisible(z, z1) || this.mDrawable.setVisible(z, z1);
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void unscheduleDrawable(Drawable drawable0, Runnable runnable0) {
        this.unscheduleSelf(runnable0);
    }
}

