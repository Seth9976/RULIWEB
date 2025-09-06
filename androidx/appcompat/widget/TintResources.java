package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

class TintResources extends ResourcesWrapper {
    private final WeakReference mContextRef;

    public TintResources(Context context0, Resources resources0) {
        super(resources0);
        this.mContextRef = new WeakReference(context0);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public Drawable getDrawable(int v) throws Resources.NotFoundException {
        Drawable drawable0 = this.getDrawableCanonical(v);
        Context context0 = (Context)this.mContextRef.get();
        if(drawable0 != null && context0 != null) {
            ResourceManagerInternal.get().tintDrawableUsingColorFilter(context0, v, drawable0);
        }
        return drawable0;
    }
}

