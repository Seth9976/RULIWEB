package androidx.core.graphics;

import android.graphics.Typeface;
import android.util.Log;
import androidx.collection.LongSparseArray;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class WeightTypefaceApi26 {
    private static final String NATIVE_CREATE_FROM_TYPEFACE_WITH_EXACT_STYLE_METHOD = "nativeCreateFromTypefaceWithExactStyle";
    private static final String NATIVE_INSTANCE_FIELD = "native_instance";
    private static final String TAG = "WeightTypeface";
    private static final Constructor sConstructor;
    private static final Method sNativeCreateFromTypefaceWithExactStyle;
    private static final Field sNativeInstance;
    private static final Object sWeightCacheLock;
    private static final LongSparseArray sWeightTypefaceCache;

    static {
        Constructor constructor0;
        Method method0;
        Field field0;
        try {
            field0 = Typeface.class.getDeclaredField("native_instance");
            method0 = Typeface.class.getDeclaredMethod("nativeCreateFromTypefaceWithExactStyle", Long.TYPE, Integer.TYPE, Boolean.TYPE);
            method0.setAccessible(true);
            constructor0 = Typeface.class.getDeclaredConstructor(Long.TYPE);
            constructor0.setAccessible(true);
        }
        catch(NoSuchFieldException | NoSuchMethodException noSuchFieldException0) {
            Log.e("WeightTypeface", noSuchFieldException0.getClass().getName(), noSuchFieldException0);
            field0 = null;
            method0 = null;
            constructor0 = null;
        }
        WeightTypefaceApi26.sNativeInstance = field0;
        WeightTypefaceApi26.sNativeCreateFromTypefaceWithExactStyle = method0;
        WeightTypefaceApi26.sConstructor = constructor0;
        WeightTypefaceApi26.sWeightTypefaceCache = new LongSparseArray(3);
        WeightTypefaceApi26.sWeightCacheLock = new Object();
    }

    private static Typeface create(long v) {
        try {
            return (Typeface)WeightTypefaceApi26.sConstructor.newInstance(v);
        }
        catch(IllegalAccessException | InstantiationException | InvocationTargetException unused_ex) {
            return null;
        }
    }

    // 去混淆评级： 低(30)
    static Typeface createWeightStyle(Typeface typeface0, int v, boolean z) {
        return null;
    }

    private static long getNativeInstance(Typeface typeface0) {
        try {
            return WeightTypefaceApi26.sNativeInstance.getLong(typeface0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
    }

    private static boolean isPrivateApiAvailable() [...] // 潜在的解密器

    private static long nativeCreateFromTypefaceWithExactStyle(long v, int v1, boolean z) {
        try {
            return (long)(((Long)WeightTypefaceApi26.sNativeCreateFromTypefaceWithExactStyle.invoke(null, v, v1, Boolean.valueOf(z))));
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new RuntimeException(invocationTargetException0);
        }
    }
}

