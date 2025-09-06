package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class TintContextWrapper extends ContextWrapper {
    private static final Object CACHE_LOCK;
    private final Resources mResources;
    private final Resources.Theme mTheme;
    private static ArrayList sCache;

    static {
        TintContextWrapper.CACHE_LOCK = new Object();
    }

    private TintContextWrapper(Context context0) {
        super(context0);
        this.mResources = new TintResources(this, context0.getResources());
        this.mTheme = null;
    }

    @Override  // android.content.ContextWrapper
    public AssetManager getAssets() {
        return this.mResources.getAssets();
    }

    @Override  // android.content.ContextWrapper
    public Resources getResources() {
        return this.mResources;
    }

    @Override  // android.content.ContextWrapper
    public Resources.Theme getTheme() {
        return this.mTheme == null ? super.getTheme() : this.mTheme;
    }

    @Override  // android.content.ContextWrapper
    public void setTheme(int v) {
        Resources.Theme resources$Theme0 = this.mTheme;
        if(resources$Theme0 == null) {
            super.setTheme(v);
            return;
        }
        resources$Theme0.applyStyle(v, true);
    }

    // 去混淆评级： 中等(60)
    private static boolean shouldWrap(Context context0) {
        return !(context0 instanceof TintContextWrapper) && !(context0.getResources() instanceof TintResources) && !(context0.getResources() instanceof VectorEnabledTintResources) && false;
    }

    public static Context wrap(Context context0) {
        if(TintContextWrapper.shouldWrap(context0)) {
            Object object0 = TintContextWrapper.CACHE_LOCK;
            synchronized(object0) {
                ArrayList arrayList0 = TintContextWrapper.sCache;
                if(arrayList0 == null) {
                    TintContextWrapper.sCache = new ArrayList();
                }
                else {
                    for(int v1 = arrayList0.size() - 1; v1 >= 0; --v1) {
                        WeakReference weakReference0 = (WeakReference)TintContextWrapper.sCache.get(v1);
                        if(weakReference0 == null || weakReference0.get() == null) {
                            TintContextWrapper.sCache.remove(v1);
                        }
                    }
                    for(int v2 = TintContextWrapper.sCache.size() - 1; v2 >= 0; --v2) {
                        WeakReference weakReference1 = (WeakReference)TintContextWrapper.sCache.get(v2);
                        Context context1 = weakReference1 == null ? null : ((TintContextWrapper)weakReference1.get());
                        if(context1 != null && ((TintContextWrapper)context1).getBaseContext() == context0) {
                            return context1;
                        }
                    }
                }
                Context context2 = new TintContextWrapper(context0);
                TintContextWrapper.sCache.add(new WeakReference(context2));
                return context2;
            }
        }
        return context0;
    }
}

