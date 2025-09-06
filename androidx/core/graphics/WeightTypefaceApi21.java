package androidx.core.graphics;

import android.graphics.Typeface;
import android.util.Log;
import androidx.collection.LongSparseArray;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class WeightTypefaceApi21 {
    private static final String NATIVE_CREATE_FROM_TYPEFACE_METHOD = "nativeCreateFromTypeface";
    private static final String NATIVE_CREATE_WEIGHT_ALIAS_METHOD = "nativeCreateWeightAlias";
    private static final String NATIVE_INSTANCE_FIELD = "native_instance";
    private static final String TAG = "WeightTypeface";
    private static final Constructor sConstructor;
    private static final Method sNativeCreateFromTypeface;
    private static final Method sNativeCreateWeightAlias;
    private static final Field sNativeInstance;
    private static final Object sWeightCacheLock;
    private static final LongSparseArray sWeightTypefaceCache;

    static {
        Constructor constructor0;
        Method method1;
        Method method0;
        Field field0;
        try {
            field0 = Typeface.class.getDeclaredField("native_instance");
            method0 = Typeface.class.getDeclaredMethod("nativeCreateFromTypeface", Long.TYPE, Integer.TYPE);
            method0.setAccessible(true);
            method1 = Typeface.class.getDeclaredMethod("nativeCreateWeightAlias", Long.TYPE, Integer.TYPE);
            method1.setAccessible(true);
            constructor0 = Typeface.class.getDeclaredConstructor(Long.TYPE);
            constructor0.setAccessible(true);
        }
        catch(NoSuchFieldException | NoSuchMethodException exception0) {
            Log.e("WeightTypeface", exception0.getClass().getName(), exception0);
            field0 = null;
            method0 = null;
            method1 = null;
            constructor0 = null;
        }
        WeightTypefaceApi21.sNativeInstance = field0;
        WeightTypefaceApi21.sNativeCreateFromTypeface = method0;
        WeightTypefaceApi21.sNativeCreateWeightAlias = method1;
        WeightTypefaceApi21.sConstructor = constructor0;
        WeightTypefaceApi21.sWeightTypefaceCache = new LongSparseArray(3);
        WeightTypefaceApi21.sWeightCacheLock = new Object();
    }

    private static Typeface create(long v) {
        try {
            return (Typeface)WeightTypefaceApi21.sConstructor.newInstance(v);
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
            return WeightTypefaceApi21.sNativeInstance.getLong(typeface0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
    }

    private static boolean isPrivateApiAvailable() [...] // 潜在的解密器

    private static long nativeCreateFromTypefaceWithExactStyle(long v, int v1, boolean z) {
        try {
            Long long0 = (Long)WeightTypefaceApi21.sNativeCreateFromTypeface.invoke(null, v, ((int)(z ? 2 : 0)));
            long0.longValue();
            return (long)(((Long)WeightTypefaceApi21.sNativeCreateWeightAlias.invoke(null, long0, v1)));
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new RuntimeException(invocationTargetException0);
        }
    }

    private static long nativeCreateWeightAlias(long v, int v1) {
        try {
            return (long)(((Long)WeightTypefaceApi21.sNativeCreateWeightAlias.invoke(null, v, v1)));
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new RuntimeException(invocationTargetException0);
        }
    }
}

