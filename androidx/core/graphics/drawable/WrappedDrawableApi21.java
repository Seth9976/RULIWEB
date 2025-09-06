package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.lang.reflect.Method;

class WrappedDrawableApi21 extends WrappedDrawableApi14 {
    private static final String TAG = "WrappedDrawableApi21";
    private static Method sIsProjectedDrawableMethod;

    WrappedDrawableApi21(Drawable drawable0) {
        super(drawable0);
        this.findAndCacheIsProjectedDrawableMethod();
    }

    WrappedDrawableApi21(WrappedDrawableState wrappedDrawableState0, Resources resources0) {
        super(wrappedDrawableState0, resources0);
        this.findAndCacheIsProjectedDrawableMethod();
    }

    private void findAndCacheIsProjectedDrawableMethod() {
        if(WrappedDrawableApi21.sIsProjectedDrawableMethod == null) {
            try {
                WrappedDrawableApi21.sIsProjectedDrawableMethod = Drawable.class.getDeclaredMethod("isProjected", null);
            }
            catch(Exception exception0) {
                Log.w("WrappedDrawableApi21", "Failed to retrieve Drawable#isProjected() method", exception0);
            }
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public Rect getDirtyBounds() {
        return this.mDrawable.getDirtyBounds();
    }

    @Override  // android.graphics.drawable.Drawable
    public void getOutline(Outline outline0) {
        this.mDrawable.getOutline(outline0);
    }

    // 去混淆评级： 中等(50)
    @Override  // androidx.core.graphics.drawable.WrappedDrawableApi14
    protected boolean isCompatTintEnabled() [...] // 潜在的解密器

    @Override  // android.graphics.drawable.Drawable
    public boolean isProjected() {
        if(this.mDrawable != null) {
            Method method0 = WrappedDrawableApi21.sIsProjectedDrawableMethod;
            if(method0 != null) {
                try {
                    return ((Boolean)method0.invoke(this.mDrawable, null)).booleanValue();
                }
                catch(Exception exception0) {
                    Log.w("WrappedDrawableApi21", "Error calling Drawable#isProjected() method", exception0);
                }
            }
        }
        return false;
    }

    @Override  // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f1) {
        this.mDrawable.setHotspot(f, f1);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setHotspotBounds(int v, int v1, int v2, int v3) {
        this.mDrawable.setHotspotBounds(v, v1, v2, v3);
    }

    @Override  // androidx.core.graphics.drawable.WrappedDrawableApi14
    public boolean setState(int[] arr_v) {
        if(super.setState(arr_v)) {
            this.invalidateSelf();
            return true;
        }
        return false;
    }

    @Override  // androidx.core.graphics.drawable.WrappedDrawableApi14
    public void setTint(int v) {
        this.mDrawable.setTint(v);
    }

    @Override  // androidx.core.graphics.drawable.WrappedDrawableApi14
    public void setTintList(ColorStateList colorStateList0) {
        this.mDrawable.setTintList(colorStateList0);
    }

    @Override  // androidx.core.graphics.drawable.WrappedDrawableApi14
    public void setTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mDrawable.setTintMode(porterDuff$Mode0);
    }
}

