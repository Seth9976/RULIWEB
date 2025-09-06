package androidx.core.graphics;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import androidx.collection.LongSparseArray;
import androidx.core.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import java.lang.reflect.Field;

final class WeightTypefaceApi14 {
    private static final String NATIVE_INSTANCE_FIELD = "native_instance";
    private static final String TAG = "WeightTypeface";
    private static final Field sNativeInstance;
    private static final Object sWeightCacheLock;
    private static final LongSparseArray sWeightTypefaceCache;

    static {
        Field field0;
        try {
            field0 = Typeface.class.getDeclaredField("native_instance");
            field0.setAccessible(true);
        }
        catch(Exception exception0) {
            Log.e("WeightTypeface", exception0.getClass().getName(), exception0);
            field0 = null;
        }
        WeightTypefaceApi14.sNativeInstance = field0;
        WeightTypefaceApi14.sWeightTypefaceCache = new LongSparseArray(3);
        WeightTypefaceApi14.sWeightCacheLock = new Object();
    }

    // 去混淆评级： 低(30)
    static Typeface createWeightStyle(TypefaceCompatBaseImpl typefaceCompatBaseImpl0, Context context0, Typeface typeface0, int v, boolean z) {
        return null;
    }

    private static Typeface getBestFontFromFamily(TypefaceCompatBaseImpl typefaceCompatBaseImpl0, Context context0, Typeface typeface0, int v, boolean z) {
        FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry0 = typefaceCompatBaseImpl0.getFontFamily(typeface0);
        return fontResourcesParserCompat$FontFamilyFilesResourceEntry0 == null ? null : typefaceCompatBaseImpl0.createFromFontFamilyFilesResourceEntry(context0, fontResourcesParserCompat$FontFamilyFilesResourceEntry0, context0.getResources(), v, z);
    }

    private static long getNativeInstance(Typeface typeface0) {
        try {
            return ((Number)WeightTypefaceApi14.sNativeInstance.get(typeface0)).longValue();
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
    }

    private static boolean isPrivateApiAvailable() [...] // 潜在的解密器

    private static Typeface platformTypefaceCreate(Typeface typeface0, int v, boolean z) {
        int v1 = 1;
        if(v < 600 && !z) {
            return Typeface.create(typeface0, 0);
        }
        if(v < 600) {
            return Typeface.create(typeface0, 2);
        }
        if(z) {
            v1 = 3;
        }
        return Typeface.create(typeface0, v1);
    }
}

