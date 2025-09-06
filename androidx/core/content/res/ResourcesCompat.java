package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

public final class ResourcesCompat {
    static class Api21Impl {
        static Drawable getDrawable(Resources resources0, int v, Resources.Theme resources$Theme0) {
            return resources0.getDrawable(v, resources$Theme0);
        }

        static Drawable getDrawableForDensity(Resources resources0, int v, int v1, Resources.Theme resources$Theme0) {
            return resources0.getDrawableForDensity(v, v1, resources$Theme0);
        }
    }

    static class Api23Impl {
        static int getColor(Resources resources0, int v, Resources.Theme resources$Theme0) {
            return resources0.getColor(v, resources$Theme0);
        }

        static ColorStateList getColorStateList(Resources resources0, int v, Resources.Theme resources$Theme0) {
            return resources0.getColorStateList(v, resources$Theme0);
        }
    }

    static class Api29Impl {
        static float getFloat(Resources resources0, int v) {
            return resources0.getFloat(v);
        }
    }

    static class ColorStateListCacheEntry {
        final Configuration mConfiguration;
        final int mThemeHash;
        final ColorStateList mValue;

        ColorStateListCacheEntry(ColorStateList colorStateList0, Configuration configuration0, Resources.Theme resources$Theme0) {
            this.mValue = colorStateList0;
            this.mConfiguration = configuration0;
            this.mThemeHash = resources$Theme0 == null ? 0 : resources$Theme0.hashCode();
        }
    }

    static final class ColorStateListCacheKey {
        final Resources mResources;
        final Resources.Theme mTheme;

        ColorStateListCacheKey(Resources resources0, Resources.Theme resources$Theme0) {
            this.mResources = resources0;
            this.mTheme = resources$Theme0;
        }

        // 去混淆评级： 低(30)
        @Override
        public boolean equals(Object object0) {
            return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && this.mResources.equals(((ColorStateListCacheKey)object0).mResources) && ObjectsCompat.equals(this.mTheme, ((ColorStateListCacheKey)object0).mTheme);
        }

        @Override
        public int hashCode() {
            return ObjectsCompat.hash(new Object[]{this.mResources, this.mTheme});
        }
    }

    public static abstract class FontCallback {
        public final void callbackFailAsync(int v, Handler handler0) {
            FontCallback.getHandler(handler0).post(() -> this.onFontRetrievalFailed(v));
        }

        public final void callbackSuccessAsync(Typeface typeface0, Handler handler0) {
            FontCallback.getHandler(handler0).post(() -> this.onFontRetrieved(typeface0));
        }

        public static Handler getHandler(Handler handler0) {
            return handler0 == null ? new Handler(Looper.getMainLooper()) : handler0;
        }

        // 检测为 Lambda 实现
        void lambda$callbackFailAsync$1$androidx-core-content-res-ResourcesCompat$FontCallback(int v) [...]

        // 检测为 Lambda 实现
        void lambda$callbackSuccessAsync$0$androidx-core-content-res-ResourcesCompat$FontCallback(Typeface typeface0) [...]

        public abstract void onFontRetrievalFailed(int arg1);

        public abstract void onFontRetrieved(Typeface arg1);
    }

    public static final class ThemeCompat {
        static class androidx.core.content.res.ResourcesCompat.ThemeCompat.Api23Impl {
            private static Method sRebaseMethod;
            private static boolean sRebaseMethodFetched;
            private static final Object sRebaseMethodLock;

            static {
                androidx.core.content.res.ResourcesCompat.ThemeCompat.Api23Impl.sRebaseMethodLock = new Object();
            }

            static void rebase(Resources.Theme resources$Theme0) {
                synchronized(androidx.core.content.res.ResourcesCompat.ThemeCompat.Api23Impl.sRebaseMethodLock) {
                    if(!androidx.core.content.res.ResourcesCompat.ThemeCompat.Api23Impl.sRebaseMethodFetched) {
                        try {
                            Method method0 = Resources.Theme.class.getDeclaredMethod("rebase", null);
                            androidx.core.content.res.ResourcesCompat.ThemeCompat.Api23Impl.sRebaseMethod = method0;
                            method0.setAccessible(true);
                        }
                        catch(NoSuchMethodException noSuchMethodException0) {
                            Log.i("ResourcesCompat", "Failed to retrieve rebase() method", noSuchMethodException0);
                        }
                        androidx.core.content.res.ResourcesCompat.ThemeCompat.Api23Impl.sRebaseMethodFetched = true;
                    }
                    Method method1 = androidx.core.content.res.ResourcesCompat.ThemeCompat.Api23Impl.sRebaseMethod;
                    if(method1 != null) {
                        try {
                            method1.invoke(resources$Theme0, null);
                        }
                        catch(IllegalAccessException | InvocationTargetException illegalAccessException0) {
                            Log.i("ResourcesCompat", "Failed to invoke rebase() method via reflection", illegalAccessException0);
                            androidx.core.content.res.ResourcesCompat.ThemeCompat.Api23Impl.sRebaseMethod = null;
                        }
                    }
                }
            }
        }

        static class androidx.core.content.res.ResourcesCompat.ThemeCompat.Api29Impl {
            static void rebase(Resources.Theme resources$Theme0) {
                resources$Theme0.rebase();
            }
        }

        public static void rebase(Resources.Theme resources$Theme0) {
            if(Build.VERSION.SDK_INT >= 29) {
                androidx.core.content.res.ResourcesCompat.ThemeCompat.Api29Impl.rebase(resources$Theme0);
                return;
            }
            if(Build.VERSION.SDK_INT >= 23) {
                androidx.core.content.res.ResourcesCompat.ThemeCompat.Api23Impl.rebase(resources$Theme0);
            }
        }
    }

    public static final int ID_NULL = 0;
    private static final String TAG = "ResourcesCompat";
    private static final Object sColorStateCacheLock;
    private static final WeakHashMap sColorStateCaches;
    private static final ThreadLocal sTempTypedValue;

    static {
        ResourcesCompat.sTempTypedValue = new ThreadLocal();
        ResourcesCompat.sColorStateCaches = new WeakHashMap(0);
        ResourcesCompat.sColorStateCacheLock = new Object();
    }

    private static void addColorStateListToCache(ColorStateListCacheKey resourcesCompat$ColorStateListCacheKey0, int v, ColorStateList colorStateList0, Resources.Theme resources$Theme0) {
        synchronized(ResourcesCompat.sColorStateCacheLock) {
            WeakHashMap weakHashMap0 = ResourcesCompat.sColorStateCaches;
            SparseArray sparseArray0 = (SparseArray)weakHashMap0.get(resourcesCompat$ColorStateListCacheKey0);
            if(sparseArray0 == null) {
                sparseArray0 = new SparseArray();
                weakHashMap0.put(resourcesCompat$ColorStateListCacheKey0, sparseArray0);
            }
            sparseArray0.append(v, new ColorStateListCacheEntry(colorStateList0, resourcesCompat$ColorStateListCacheKey0.mResources.getConfiguration(), resources$Theme0));
        }
    }

    public static void clearCachesForTheme(Resources.Theme resources$Theme0) {
        synchronized(ResourcesCompat.sColorStateCacheLock) {
            Iterator iterator0 = ResourcesCompat.sColorStateCaches.keySet().iterator();
            while(iterator0.hasNext()) {
                Object object1 = iterator0.next();
                if(((ColorStateListCacheKey)object1) != null && resources$Theme0.equals(((ColorStateListCacheKey)object1).mTheme)) {
                    iterator0.remove();
                }
            }
        }
    }

    private static ColorStateList getCachedColorStateList(ColorStateListCacheKey resourcesCompat$ColorStateListCacheKey0, int v) {
        synchronized(ResourcesCompat.sColorStateCacheLock) {
            SparseArray sparseArray0 = (SparseArray)ResourcesCompat.sColorStateCaches.get(resourcesCompat$ColorStateListCacheKey0);
            if(sparseArray0 != null && sparseArray0.size() > 0) {
                ColorStateListCacheEntry resourcesCompat$ColorStateListCacheEntry0 = (ColorStateListCacheEntry)sparseArray0.get(v);
                if(resourcesCompat$ColorStateListCacheEntry0 != null) {
                    Configuration configuration0 = resourcesCompat$ColorStateListCacheKey0.mResources.getConfiguration();
                    if(resourcesCompat$ColorStateListCacheEntry0.mConfiguration.equals(configuration0) && (resourcesCompat$ColorStateListCacheKey0.mTheme == null && resourcesCompat$ColorStateListCacheEntry0.mThemeHash == 0 || resourcesCompat$ColorStateListCacheKey0.mTheme != null && resourcesCompat$ColorStateListCacheEntry0.mThemeHash == resourcesCompat$ColorStateListCacheKey0.mTheme.hashCode())) {
                        return resourcesCompat$ColorStateListCacheEntry0.mValue;
                    }
                    sparseArray0.remove(v);
                }
            }
            return null;
        }
    }

    // 去混淆评级： 低(20)
    public static Typeface getCachedFont(Context context0, int v) throws Resources.NotFoundException {
        return context0.isRestricted() ? null : ResourcesCompat.loadFont(context0, v, new TypedValue(), 0, null, null, false, true);
    }

    public static int getColor(Resources resources0, int v, Resources.Theme resources$Theme0) throws Resources.NotFoundException {
        return Build.VERSION.SDK_INT < 23 ? resources0.getColor(v) : Api23Impl.getColor(resources0, v, resources$Theme0);
    }

    public static ColorStateList getColorStateList(Resources resources0, int v, Resources.Theme resources$Theme0) throws Resources.NotFoundException {
        ColorStateListCacheKey resourcesCompat$ColorStateListCacheKey0 = new ColorStateListCacheKey(resources0, resources$Theme0);
        ColorStateList colorStateList0 = ResourcesCompat.getCachedColorStateList(resourcesCompat$ColorStateListCacheKey0, v);
        if(colorStateList0 != null) {
            return colorStateList0;
        }
        ColorStateList colorStateList1 = ResourcesCompat.inflateColorStateList(resources0, v, resources$Theme0);
        if(colorStateList1 != null) {
            ResourcesCompat.addColorStateListToCache(resourcesCompat$ColorStateListCacheKey0, v, colorStateList1, resources$Theme0);
            return colorStateList1;
        }
        return Build.VERSION.SDK_INT < 23 ? resources0.getColorStateList(v) : Api23Impl.getColorStateList(resources0, v, resources$Theme0);
    }

    public static Drawable getDrawable(Resources resources0, int v, Resources.Theme resources$Theme0) throws Resources.NotFoundException {
        return Api21Impl.getDrawable(resources0, v, resources$Theme0);
    }

    public static Drawable getDrawableForDensity(Resources resources0, int v, int v1, Resources.Theme resources$Theme0) throws Resources.NotFoundException {
        return Api21Impl.getDrawableForDensity(resources0, v, v1, resources$Theme0);
    }

    public static float getFloat(Resources resources0, int v) {
        if(Build.VERSION.SDK_INT >= 29) {
            return Api29Impl.getFloat(resources0, v);
        }
        TypedValue typedValue0 = ResourcesCompat.getTypedValue();
        resources0.getValue(v, typedValue0, true);
        if(typedValue0.type != 4) {
            throw new Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(v) + " type #0x" + Integer.toHexString(typedValue0.type) + " is not valid");
        }
        return typedValue0.getFloat();
    }

    // 去混淆评级： 低(20)
    public static Typeface getFont(Context context0, int v) throws Resources.NotFoundException {
        return context0.isRestricted() ? null : ResourcesCompat.loadFont(context0, v, new TypedValue(), 0, null, null, false, false);
    }

    // 去混淆评级： 低(20)
    public static Typeface getFont(Context context0, int v, TypedValue typedValue0, int v1, FontCallback resourcesCompat$FontCallback0) throws Resources.NotFoundException {
        return context0.isRestricted() ? null : ResourcesCompat.loadFont(context0, v, typedValue0, v1, resourcesCompat$FontCallback0, null, true, false);
    }

    public static void getFont(Context context0, int v, FontCallback resourcesCompat$FontCallback0, Handler handler0) throws Resources.NotFoundException {
        Preconditions.checkNotNull(resourcesCompat$FontCallback0);
        if(context0.isRestricted()) {
            resourcesCompat$FontCallback0.callbackFailAsync(-4, handler0);
            return;
        }
        ResourcesCompat.loadFont(context0, v, new TypedValue(), 0, resourcesCompat$FontCallback0, handler0, false, false);
    }

    private static TypedValue getTypedValue() {
        ThreadLocal threadLocal0 = ResourcesCompat.sTempTypedValue;
        TypedValue typedValue0 = (TypedValue)threadLocal0.get();
        if(typedValue0 == null) {
            typedValue0 = new TypedValue();
            threadLocal0.set(typedValue0);
        }
        return typedValue0;
    }

    private static ColorStateList inflateColorStateList(Resources resources0, int v, Resources.Theme resources$Theme0) {
        if(ResourcesCompat.isColorInt(resources0, v)) {
            return null;
        }
        XmlResourceParser xmlResourceParser0 = resources0.getXml(v);
        try {
            return ColorStateListInflaterCompat.createFromXml(resources0, xmlResourceParser0, resources$Theme0);
        }
        catch(Exception exception0) {
            Log.w("ResourcesCompat", "Failed to inflate ColorStateList, leaving it to the framework", exception0);
            return null;
        }
    }

    private static boolean isColorInt(Resources resources0, int v) {
        TypedValue typedValue0 = ResourcesCompat.getTypedValue();
        resources0.getValue(v, typedValue0, true);
        return typedValue0.type >= 28 && typedValue0.type <= 0x1F;
    }

    private static Typeface loadFont(Context context0, int v, TypedValue typedValue0, int v1, FontCallback resourcesCompat$FontCallback0, Handler handler0, boolean z, boolean z1) {
        Resources resources0 = context0.getResources();
        resources0.getValue(v, typedValue0, true);
        Typeface typeface0 = ResourcesCompat.loadFont(context0, resources0, typedValue0, v, v1, resourcesCompat$FontCallback0, handler0, z, z1);
        if(typeface0 == null && resourcesCompat$FontCallback0 == null && !z1) {
            throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(v) + " could not be retrieved.");
        }
        return typeface0;
    }

    private static Typeface loadFont(Context context0, Resources resources0, TypedValue typedValue0, int v, int v1, FontCallback resourcesCompat$FontCallback0, Handler handler0, boolean z, boolean z1) {
        if(typedValue0.string == null) {
            throw new Resources.NotFoundException("Resource \"" + resources0.getResourceName(v) + "\" (" + Integer.toHexString(v) + ") is not a Font: " + typedValue0);
        }
        String s = typedValue0.string.toString();
        if(!s.startsWith("res/")) {
            if(resourcesCompat$FontCallback0 != null) {
                resourcesCompat$FontCallback0.callbackFailAsync(-3, handler0);
            }
            return null;
        }
        Typeface typeface0 = TypefaceCompat.findFromCache(resources0, v, s, typedValue0.assetCookie, v1);
        if(typeface0 != null) {
            if(resourcesCompat$FontCallback0 != null) {
                resourcesCompat$FontCallback0.callbackSuccessAsync(typeface0, handler0);
            }
            return typeface0;
        }
        if(z1) {
            return null;
        }
        try {
            if(s.toLowerCase().endsWith(".xml")) {
                FamilyResourceEntry fontResourcesParserCompat$FamilyResourceEntry0 = FontResourcesParserCompat.parse(resources0.getXml(v), resources0);
                if(fontResourcesParserCompat$FamilyResourceEntry0 == null) {
                    Log.e("ResourcesCompat", "Failed to find font-family tag");
                    if(resourcesCompat$FontCallback0 != null) {
                        resourcesCompat$FontCallback0.callbackFailAsync(-3, handler0);
                    }
                    return null;
                }
                return TypefaceCompat.createFromResourcesFamilyXml(context0, fontResourcesParserCompat$FamilyResourceEntry0, resources0, v, s, typedValue0.assetCookie, v1, resourcesCompat$FontCallback0, handler0, z);
            }
            Typeface typeface1 = TypefaceCompat.createFromResourcesFontFile(context0, resources0, v, s, typedValue0.assetCookie, v1);
            if(resourcesCompat$FontCallback0 != null) {
                if(typeface1 != null) {
                    resourcesCompat$FontCallback0.callbackSuccessAsync(typeface1, handler0);
                    return typeface1;
                }
                resourcesCompat$FontCallback0.callbackFailAsync(-3, handler0);
            }
            return typeface1;
        }
        catch(XmlPullParserException xmlPullParserException0) {
            Log.e("ResourcesCompat", "Failed to parse xml resource " + s, xmlPullParserException0);
        }
        catch(IOException iOException0) {
            Log.e("ResourcesCompat", "Failed to read xml resource " + s, iOException0);
        }
        if(resourcesCompat$FontCallback0 != null) {
            resourcesCompat$FontCallback0.callbackFailAsync(-3, handler0);
        }
        return null;
    }
}

