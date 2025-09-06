package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat;
import androidx.appcompat.resources.Compatibility.Api21Impl;
import androidx.appcompat.resources.R.drawable;
import androidx.collection.LongSparseArray;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.collection.SparseArrayCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class ResourceManagerInternal {
    static class AsldcInflateDelegate implements InflateDelegate {
        @Override  // androidx.appcompat.widget.ResourceManagerInternal$InflateDelegate
        public Drawable createFromXmlInner(Context context0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) {
            try {
                return AnimatedStateListDrawableCompat.createFromXmlInner(context0, context0.getResources(), xmlPullParser0, attributeSet0, resources$Theme0);
            }
            catch(Exception exception0) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", exception0);
                return null;
            }
        }
    }

    static class AvdcInflateDelegate implements InflateDelegate {
        @Override  // androidx.appcompat.widget.ResourceManagerInternal$InflateDelegate
        public Drawable createFromXmlInner(Context context0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) {
            try {
                return AnimatedVectorDrawableCompat.createFromXmlInner(context0, context0.getResources(), xmlPullParser0, attributeSet0, resources$Theme0);
            }
            catch(Exception exception0) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", exception0);
                return null;
            }
        }
    }

    static class ColorFilterLruCache extends LruCache {
        public ColorFilterLruCache(int v) {
            super(v);
        }

        private static int generateCacheKey(int v, PorterDuff.Mode porterDuff$Mode0) {
            return (v + 0x1F) * 0x1F + porterDuff$Mode0.hashCode();
        }

        PorterDuffColorFilter get(int v, PorterDuff.Mode porterDuff$Mode0) {
            return (PorterDuffColorFilter)this.get(ColorFilterLruCache.generateCacheKey(v, porterDuff$Mode0));
        }

        PorterDuffColorFilter put(int v, PorterDuff.Mode porterDuff$Mode0, PorterDuffColorFilter porterDuffColorFilter0) {
            return (PorterDuffColorFilter)this.put(ColorFilterLruCache.generateCacheKey(v, porterDuff$Mode0), porterDuffColorFilter0);
        }
    }

    static class DrawableDelegate implements InflateDelegate {
        @Override  // androidx.appcompat.widget.ResourceManagerInternal$InflateDelegate
        public Drawable createFromXmlInner(Context context0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) {
            String s = attributeSet0.getClassAttribute();
            if(s != null) {
                try {
                    Drawable drawable0 = (Drawable)DrawableDelegate.class.getClassLoader().loadClass(s).asSubclass(Drawable.class).getDeclaredConstructor(null).newInstance(null);
                    Api21Impl.inflate(drawable0, context0.getResources(), xmlPullParser0, attributeSet0, resources$Theme0);
                    return drawable0;
                }
                catch(Exception exception0) {
                    Log.e("DrawableDelegate", "Exception while inflating <drawable>", exception0);
                }
            }
            return null;
        }
    }

    interface InflateDelegate {
        Drawable createFromXmlInner(Context arg1, XmlPullParser arg2, AttributeSet arg3, Resources.Theme arg4);
    }

    public interface ResourceManagerHooks {
        Drawable createDrawableFor(ResourceManagerInternal arg1, Context arg2, int arg3);

        ColorStateList getTintListForDrawableRes(Context arg1, int arg2);

        PorterDuff.Mode getTintModeForDrawableRes(int arg1);

        boolean tintDrawable(Context arg1, int arg2, Drawable arg3);

        boolean tintDrawableUsingColorFilter(Context arg1, int arg2, Drawable arg3);
    }

    static class VdcInflateDelegate implements InflateDelegate {
        @Override  // androidx.appcompat.widget.ResourceManagerInternal$InflateDelegate
        public Drawable createFromXmlInner(Context context0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) {
            try {
                return VectorDrawableCompat.createFromXmlInner(context0.getResources(), xmlPullParser0, attributeSet0, resources$Theme0);
            }
            catch(Exception exception0) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", exception0);
                return null;
            }
        }
    }

    private static final ColorFilterLruCache COLOR_FILTER_CACHE = null;
    private static final boolean DEBUG = false;
    private static final PorterDuff.Mode DEFAULT_MODE = null;
    private static ResourceManagerInternal INSTANCE = null;
    private static final String PLATFORM_VD_CLAZZ = "android.graphics.drawable.VectorDrawable";
    private static final String SKIP_DRAWABLE_TAG = "appcompat_skip_skip";
    private static final String TAG = "ResourceManagerInternal";
    private SimpleArrayMap mDelegates;
    private final WeakHashMap mDrawableCaches;
    private boolean mHasCheckedVectorDrawableSetup;
    private ResourceManagerHooks mHooks;
    private SparseArrayCompat mKnownDrawableIdTags;
    private WeakHashMap mTintLists;
    private TypedValue mTypedValue;

    static {
        ResourceManagerInternal.DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
        ResourceManagerInternal.COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
    }

    public ResourceManagerInternal() {
        this.mDrawableCaches = new WeakHashMap(0);
    }

    private void addDelegate(String s, InflateDelegate resourceManagerInternal$InflateDelegate0) {
        if(this.mDelegates == null) {
            this.mDelegates = new SimpleArrayMap();
        }
        this.mDelegates.put(s, resourceManagerInternal$InflateDelegate0);
    }

    private boolean addDrawableToCache(Context context0, long v, Drawable drawable0) {
        synchronized(this) {
            Drawable.ConstantState drawable$ConstantState0 = drawable0.getConstantState();
            if(drawable$ConstantState0 != null) {
                LongSparseArray longSparseArray0 = (LongSparseArray)this.mDrawableCaches.get(context0);
                if(longSparseArray0 == null) {
                    longSparseArray0 = new LongSparseArray();
                    this.mDrawableCaches.put(context0, longSparseArray0);
                }
                longSparseArray0.put(v, new WeakReference(drawable$ConstantState0));
                return true;
            }
            return false;
        }
    }

    private void addTintListToCache(Context context0, int v, ColorStateList colorStateList0) {
        if(this.mTintLists == null) {
            this.mTintLists = new WeakHashMap();
        }
        SparseArrayCompat sparseArrayCompat0 = (SparseArrayCompat)this.mTintLists.get(context0);
        if(sparseArrayCompat0 == null) {
            sparseArrayCompat0 = new SparseArrayCompat();
            this.mTintLists.put(context0, sparseArrayCompat0);
        }
        sparseArrayCompat0.append(v, colorStateList0);
    }

    private void checkVectorDrawableSetup(Context context0) {
        if(!this.mHasCheckedVectorDrawableSetup) {
            this.mHasCheckedVectorDrawableSetup = true;
            Drawable drawable0 = this.getDrawable(context0, drawable.abc_vector_test);
            if(drawable0 == null || !ResourceManagerInternal.isVectorDrawable(drawable0)) {
                this.mHasCheckedVectorDrawableSetup = false;
                throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
            }
        }
    }

    private static long createCacheKey(TypedValue typedValue0) {
        return ((long)typedValue0.assetCookie) << 0x20 | ((long)typedValue0.data);
    }

    private Drawable createDrawableIfNeeded(Context context0, int v) {
        if(this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        TypedValue typedValue0 = this.mTypedValue;
        context0.getResources().getValue(v, typedValue0, true);
        long v1 = ResourceManagerInternal.createCacheKey(typedValue0);
        Drawable drawable0 = this.getCachedDrawable(context0, v1);
        if(drawable0 != null) {
            return drawable0;
        }
        Drawable drawable1 = this.mHooks == null ? null : this.mHooks.createDrawableFor(this, context0, v);
        if(drawable1 != null) {
            drawable1.setChangingConfigurations(typedValue0.changingConfigurations);
            this.addDrawableToCache(context0, v1, drawable1);
        }
        return drawable1;
    }

    private static PorterDuffColorFilter createTintFilter(ColorStateList colorStateList0, PorterDuff.Mode porterDuff$Mode0, int[] arr_v) {
        return colorStateList0 == null || porterDuff$Mode0 == null ? null : ResourceManagerInternal.getPorterDuffColorFilter(colorStateList0.getColorForState(arr_v, 0), porterDuff$Mode0);
    }

    public static ResourceManagerInternal get() {
        synchronized(ResourceManagerInternal.class) {
            if(ResourceManagerInternal.INSTANCE == null) {
                ResourceManagerInternal resourceManagerInternal0 = new ResourceManagerInternal();
                ResourceManagerInternal.INSTANCE = resourceManagerInternal0;
                ResourceManagerInternal.installDefaultInflateDelegates(resourceManagerInternal0);
            }
            return ResourceManagerInternal.INSTANCE;
        }
    }

    private Drawable getCachedDrawable(Context context0, long v) {
        synchronized(this) {
            LongSparseArray longSparseArray0 = (LongSparseArray)this.mDrawableCaches.get(context0);
            if(longSparseArray0 == null) {
                return null;
            }
            WeakReference weakReference0 = (WeakReference)longSparseArray0.get(v);
            if(weakReference0 != null) {
                Drawable.ConstantState drawable$ConstantState0 = (Drawable.ConstantState)weakReference0.get();
                if(drawable$ConstantState0 != null) {
                    return drawable$ConstantState0.newDrawable(context0.getResources());
                }
                longSparseArray0.remove(v);
            }
            return null;
        }
    }

    public Drawable getDrawable(Context context0, int v) {
        synchronized(this) {
            return this.getDrawable(context0, v, false);
        }
    }

    Drawable getDrawable(Context context0, int v, boolean z) {
        synchronized(this) {
            this.checkVectorDrawableSetup(context0);
            Drawable drawable0 = this.loadDrawableFromDelegates(context0, v);
            if(drawable0 == null) {
                drawable0 = this.createDrawableIfNeeded(context0, v);
            }
            if(drawable0 == null) {
                drawable0 = ContextCompat.getDrawable(context0, v);
            }
            if(drawable0 != null) {
                drawable0 = this.tintDrawable(context0, v, z, drawable0);
            }
            if(drawable0 != null) {
                DrawableUtils.fixDrawable(drawable0);
            }
            return drawable0;
        }
    }

    public static PorterDuffColorFilter getPorterDuffColorFilter(int v, PorterDuff.Mode porterDuff$Mode0) {
        synchronized(ResourceManagerInternal.class) {
            ColorFilterLruCache resourceManagerInternal$ColorFilterLruCache0 = ResourceManagerInternal.COLOR_FILTER_CACHE;
            PorterDuffColorFilter porterDuffColorFilter0 = resourceManagerInternal$ColorFilterLruCache0.get(v, porterDuff$Mode0);
            if(porterDuffColorFilter0 == null) {
                porterDuffColorFilter0 = new PorterDuffColorFilter(v, porterDuff$Mode0);
                resourceManagerInternal$ColorFilterLruCache0.put(v, porterDuff$Mode0, porterDuffColorFilter0);
            }
            return porterDuffColorFilter0;
        }
    }

    ColorStateList getTintList(Context context0, int v) {
        synchronized(this) {
            ColorStateList colorStateList0 = this.getTintListFromCache(context0, v);
            if(colorStateList0 == null) {
                colorStateList0 = this.mHooks == null ? null : this.mHooks.getTintListForDrawableRes(context0, v);
                if(colorStateList0 != null) {
                    this.addTintListToCache(context0, v, colorStateList0);
                }
            }
            return colorStateList0;
        }
    }

    private ColorStateList getTintListFromCache(Context context0, int v) {
        WeakHashMap weakHashMap0 = this.mTintLists;
        if(weakHashMap0 != null) {
            SparseArrayCompat sparseArrayCompat0 = (SparseArrayCompat)weakHashMap0.get(context0);
            return sparseArrayCompat0 == null ? null : ((ColorStateList)sparseArrayCompat0.get(v));
        }
        return null;
    }

    PorterDuff.Mode getTintMode(int v) {
        return this.mHooks == null ? null : this.mHooks.getTintModeForDrawableRes(v);
    }

    private static void installDefaultInflateDelegates(ResourceManagerInternal resourceManagerInternal0) {
        if(Build.VERSION.SDK_INT < 24) {
            resourceManagerInternal0.addDelegate("vector", new VdcInflateDelegate());
            resourceManagerInternal0.addDelegate("animated-vector", new AvdcInflateDelegate());
            resourceManagerInternal0.addDelegate("animated-selector", new AsldcInflateDelegate());
            resourceManagerInternal0.addDelegate("drawable", new DrawableDelegate());
        }
    }

    // 去混淆评级： 低(20)
    private static boolean isVectorDrawable(Drawable drawable0) {
        return drawable0 instanceof VectorDrawableCompat || "android.graphics.drawable.VectorDrawable".equals(drawable0.getClass().getName());
    }

    private Drawable loadDrawableFromDelegates(Context context0, int v) {
        if(this.mDelegates != null && !this.mDelegates.isEmpty()) {
            SparseArrayCompat sparseArrayCompat0 = this.mKnownDrawableIdTags;
            if(sparseArrayCompat0 == null) {
                this.mKnownDrawableIdTags = new SparseArrayCompat();
            }
            else {
                String s = (String)sparseArrayCompat0.get(v);
                if("appcompat_skip_skip".equals(s) || s != null && this.mDelegates.get(s) == null) {
                    return null;
                }
            }
            if(this.mTypedValue == null) {
                this.mTypedValue = new TypedValue();
            }
            TypedValue typedValue0 = this.mTypedValue;
            Resources resources0 = context0.getResources();
            resources0.getValue(v, typedValue0, true);
            long v1 = ResourceManagerInternal.createCacheKey(typedValue0);
            Drawable drawable0 = this.getCachedDrawable(context0, v1);
            if(drawable0 != null) {
                return drawable0;
            }
            if(typedValue0.string != null && typedValue0.string.toString().endsWith(".xml")) {
                try {
                    XmlResourceParser xmlResourceParser0 = resources0.getXml(v);
                    AttributeSet attributeSet0 = Xml.asAttributeSet(xmlResourceParser0);
                    do {
                        int v2 = xmlResourceParser0.next();
                    }
                    while(v2 != 1 && v2 != 2);
                    if(v2 != 2) {
                        throw new XmlPullParserException("No start tag found");
                    }
                    String s1 = xmlResourceParser0.getName();
                    this.mKnownDrawableIdTags.append(v, s1);
                    InflateDelegate resourceManagerInternal$InflateDelegate0 = (InflateDelegate)this.mDelegates.get(s1);
                    if(resourceManagerInternal$InflateDelegate0 != null) {
                        drawable0 = resourceManagerInternal$InflateDelegate0.createFromXmlInner(context0, xmlResourceParser0, attributeSet0, context0.getTheme());
                    }
                    if(drawable0 != null) {
                        drawable0.setChangingConfigurations(typedValue0.changingConfigurations);
                        this.addDrawableToCache(context0, v1, drawable0);
                    }
                }
                catch(Exception exception0) {
                    Log.e("ResourceManagerInternal", "Exception while inflating drawable", exception0);
                }
            }
            if(drawable0 == null) {
                this.mKnownDrawableIdTags.append(v, "appcompat_skip_skip");
            }
            return drawable0;
        }
        return null;
    }

    public void onConfigurationChanged(Context context0) {
        synchronized(this) {
            LongSparseArray longSparseArray0 = (LongSparseArray)this.mDrawableCaches.get(context0);
            if(longSparseArray0 != null) {
                longSparseArray0.clear();
            }
        }
    }

    Drawable onDrawableLoadedFromResources(Context context0, VectorEnabledTintResources vectorEnabledTintResources0, int v) {
        synchronized(this) {
            Drawable drawable0 = this.loadDrawableFromDelegates(context0, v);
            if(drawable0 == null) {
                drawable0 = vectorEnabledTintResources0.getDrawableCanonical(v);
            }
            return drawable0 != null ? this.tintDrawable(context0, v, false, drawable0) : null;
        }
    }

    public void setHooks(ResourceManagerHooks resourceManagerInternal$ResourceManagerHooks0) {
        synchronized(this) {
            this.mHooks = resourceManagerInternal$ResourceManagerHooks0;
        }
    }

    private Drawable tintDrawable(Context context0, int v, boolean z, Drawable drawable0) {
        ColorStateList colorStateList0 = this.getTintList(context0, v);
        if(colorStateList0 != null) {
            Drawable drawable1 = DrawableCompat.wrap(drawable0.mutate());
            DrawableCompat.setTintList(drawable1, colorStateList0);
            PorterDuff.Mode porterDuff$Mode0 = this.getTintMode(v);
            if(porterDuff$Mode0 != null) {
                DrawableCompat.setTintMode(drawable1, porterDuff$Mode0);
            }
            return drawable1;
        }
        return this.mHooks != null && this.mHooks.tintDrawable(context0, v, drawable0) || this.tintDrawableUsingColorFilter(context0, v, drawable0) || !z ? drawable0 : null;
    }

    static void tintDrawable(Drawable drawable0, TintInfo tintInfo0, int[] arr_v) {
        int[] arr_v1 = drawable0.getState();
        if(drawable0.mutate() == drawable0) {
            if(drawable0 instanceof LayerDrawable && drawable0.isStateful()) {
                drawable0.setState(new int[0]);
                drawable0.setState(arr_v1);
            }
            if(tintInfo0.mHasTintList || tintInfo0.mHasTintMode) {
                drawable0.setColorFilter(ResourceManagerInternal.createTintFilter((tintInfo0.mHasTintList ? tintInfo0.mTintList : null), (tintInfo0.mHasTintMode ? tintInfo0.mTintMode : ResourceManagerInternal.DEFAULT_MODE), arr_v));
            }
            else {
                drawable0.clearColorFilter();
            }
            if(Build.VERSION.SDK_INT <= 23) {
                drawable0.invalidateSelf();
            }
            return;
        }
        Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
    }

    boolean tintDrawableUsingColorFilter(Context context0, int v, Drawable drawable0) {
        return this.mHooks != null && this.mHooks.tintDrawableUsingColorFilter(context0, v, drawable0);
    }
}

