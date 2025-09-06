package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.core.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import androidx.core.content.res.FontResourcesParserCompat.FontFileResourceEntry;
import androidx.core.provider.FontsContractCompat.FontInfo;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class TypefaceCompatBaseImpl {
    interface StyleExtractor {
        int getWeight(Object arg1);

        boolean isItalic(Object arg1);
    }

    private static final int INVALID_KEY = 0;
    private static final String TAG = "TypefaceCompatBaseImpl";
    private ConcurrentHashMap mFontFamilies;

    TypefaceCompatBaseImpl() {
        this.mFontFamilies = new ConcurrentHashMap();
    }

    private void addFontFamily(Typeface typeface0, FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry0) {
        long v = TypefaceCompatBaseImpl.getUniqueKey(typeface0);
        if(v != 0L) {
            this.mFontFamilies.put(v, fontResourcesParserCompat$FontFamilyFilesResourceEntry0);
        }
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context0, FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry0, Resources resources0, int v) {
        FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry0 = this.findBestEntry(fontResourcesParserCompat$FontFamilyFilesResourceEntry0, v);
        if(fontResourcesParserCompat$FontFileResourceEntry0 == null) {
            return null;
        }
        Typeface typeface0 = TypefaceCompat.createFromResourcesFontFile(context0, resources0, fontResourcesParserCompat$FontFileResourceEntry0.getResourceId(), fontResourcesParserCompat$FontFileResourceEntry0.getFileName(), 0, v);
        this.addFontFamily(typeface0, fontResourcesParserCompat$FontFamilyFilesResourceEntry0);
        return typeface0;
    }

    Typeface createFromFontFamilyFilesResourceEntry(Context context0, FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry0, Resources resources0, int v, boolean z) {
        FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry0 = this.findBestEntry(fontResourcesParserCompat$FontFamilyFilesResourceEntry0, v, z);
        if(fontResourcesParserCompat$FontFileResourceEntry0 == null) {
            return null;
        }
        Typeface typeface0 = TypefaceCompat.createFromResourcesFontFile(context0, resources0, fontResourcesParserCompat$FontFileResourceEntry0.getResourceId(), fontResourcesParserCompat$FontFileResourceEntry0.getFileName(), 0, 0);
        this.addFontFamily(typeface0, fontResourcesParserCompat$FontFamilyFilesResourceEntry0);
        return typeface0;
    }

    public Typeface createFromFontInfo(Context context0, CancellationSignal cancellationSignal0, FontInfo[] arr_fontsContractCompat$FontInfo, int v) {
        InputStream inputStream0;
        if(arr_fontsContractCompat$FontInfo.length < 1) {
            return null;
        }
        FontInfo fontsContractCompat$FontInfo0 = this.findBestInfo(arr_fontsContractCompat$FontInfo, v);
        try {
            inputStream0 = null;
            inputStream0 = context0.getContentResolver().openInputStream(fontsContractCompat$FontInfo0.getUri());
            return this.createFromInputStream(context0, inputStream0);
        }
        catch(IOException unused_ex) {
            return null;
        }
        finally {
            TypefaceCompatUtil.closeQuietly(inputStream0);
        }
    }

    public Typeface createFromFontInfoWithFallback(Context context0, CancellationSignal cancellationSignal0, List list0, int v) {
        throw new IllegalStateException("createFromFontInfoWithFallback must only be called on API 29+");
    }

    protected Typeface createFromInputStream(Context context0, InputStream inputStream0) {
        File file0 = TypefaceCompatUtil.getTempFile(context0);
        if(file0 == null) {
            return null;
        }
        try {
            return TypefaceCompatUtil.copyToFile(file0, inputStream0) ? Typeface.createFromFile(file0.getPath()) : null;
        }
        catch(RuntimeException unused_ex) {
            return null;
        }
        finally {
            file0.delete();
        }
    }

    public Typeface createFromResourcesFontFile(Context context0, Resources resources0, int v, String s, int v1) {
        File file0 = TypefaceCompatUtil.getTempFile(context0);
        if(file0 == null) {
            return null;
        }
        try {
            return TypefaceCompatUtil.copyToFile(file0, resources0, v) ? Typeface.createFromFile(file0.getPath()) : null;
        }
        catch(RuntimeException unused_ex) {
            return null;
        }
        finally {
            file0.delete();
        }
    }

    Typeface createWeightStyle(Context context0, Typeface typeface0, int v, boolean z) {
        Typeface typeface1 = null;
        try {
            typeface1 = WeightTypefaceApi14.createWeightStyle(this, context0, typeface0, v, z);
        }
        catch(RuntimeException unused_ex) {
        }
        return typeface1 == null ? typeface0 : typeface1;
    }

    private FontFileResourceEntry findBestEntry(FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry0, int v) {
        return (FontFileResourceEntry)TypefaceCompatBaseImpl.findBestFont(fontResourcesParserCompat$FontFamilyFilesResourceEntry0.getEntries(), v, new StyleExtractor() {
            public int getWeight(FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry0) {
                return fontResourcesParserCompat$FontFileResourceEntry0.getWeight();
            }

            @Override  // androidx.core.graphics.TypefaceCompatBaseImpl$StyleExtractor
            public int getWeight(Object object0) {
                return this.getWeight(((FontFileResourceEntry)object0));
            }

            public boolean isItalic(FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry0) {
                return fontResourcesParserCompat$FontFileResourceEntry0.isItalic();
            }

            @Override  // androidx.core.graphics.TypefaceCompatBaseImpl$StyleExtractor
            public boolean isItalic(Object object0) {
                return this.isItalic(((FontFileResourceEntry)object0));
            }
        });
    }

    private FontFileResourceEntry findBestEntry(FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry0, int v, boolean z) {
        return (FontFileResourceEntry)TypefaceCompatBaseImpl.findBestFont(fontResourcesParserCompat$FontFamilyFilesResourceEntry0.getEntries(), v, z, new StyleExtractor() {
            public int getWeight(FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry0) {
                return fontResourcesParserCompat$FontFileResourceEntry0.getWeight();
            }

            @Override  // androidx.core.graphics.TypefaceCompatBaseImpl$StyleExtractor
            public int getWeight(Object object0) {
                return this.getWeight(((FontFileResourceEntry)object0));
            }

            public boolean isItalic(FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry0) {
                return fontResourcesParserCompat$FontFileResourceEntry0.isItalic();
            }

            @Override  // androidx.core.graphics.TypefaceCompatBaseImpl$StyleExtractor
            public boolean isItalic(Object object0) {
                return this.isItalic(((FontFileResourceEntry)object0));
            }
        });
    }

    private static Object findBestFont(Object[] arr_object, int v, StyleExtractor typefaceCompatBaseImpl$StyleExtractor0) {
        int v1 = (v & 1) == 0 ? 400 : 700;
        return (v & 2) == 0 ? TypefaceCompatBaseImpl.findBestFont(arr_object, v1, false, typefaceCompatBaseImpl$StyleExtractor0) : TypefaceCompatBaseImpl.findBestFont(arr_object, v1, true, typefaceCompatBaseImpl$StyleExtractor0);
    }

    private static Object findBestFont(Object[] arr_object, int v, boolean z, StyleExtractor typefaceCompatBaseImpl$StyleExtractor0) {
        Object object0 = null;
        int v1 = 0x7FFFFFFF;
        for(int v2 = 0; v2 < arr_object.length; ++v2) {
            Object object1 = arr_object[v2];
            int v3 = Math.abs(typefaceCompatBaseImpl$StyleExtractor0.getWeight(object1) - v) * 2 + (typefaceCompatBaseImpl$StyleExtractor0.isItalic(object1) == z ? 0 : 1);
            if(object0 == null || v1 > v3) {
                object0 = object1;
                v1 = v3;
            }
        }
        return object0;
    }

    protected FontInfo findBestInfo(FontInfo[] arr_fontsContractCompat$FontInfo, int v) {
        return (FontInfo)TypefaceCompatBaseImpl.findBestFont(arr_fontsContractCompat$FontInfo, v, new StyleExtractor() {
            public int getWeight(FontInfo fontsContractCompat$FontInfo0) {
                return fontsContractCompat$FontInfo0.getWeight();
            }

            @Override  // androidx.core.graphics.TypefaceCompatBaseImpl$StyleExtractor
            public int getWeight(Object object0) {
                return this.getWeight(((FontInfo)object0));
            }

            public boolean isItalic(FontInfo fontsContractCompat$FontInfo0) {
                return fontsContractCompat$FontInfo0.isItalic();
            }

            @Override  // androidx.core.graphics.TypefaceCompatBaseImpl$StyleExtractor
            public boolean isItalic(Object object0) {
                return this.isItalic(((FontInfo)object0));
            }
        });
    }

    FontFamilyFilesResourceEntry getFontFamily(Typeface typeface0) {
        long v = TypefaceCompatBaseImpl.getUniqueKey(typeface0);
        return v == 0L ? null : ((FontFamilyFilesResourceEntry)this.mFontFamilies.get(v));
    }

    private static long getUniqueKey(Typeface typeface0) {
        if(typeface0 == null) {
            return 0L;
        }
        try {
            Field field0 = Typeface.class.getDeclaredField("native_instance");
            field0.setAccessible(true);
            return ((Number)field0.get(typeface0)).longValue();
        }
        catch(NoSuchFieldException noSuchFieldException0) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", noSuchFieldException0);
            return 0L;
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", illegalAccessException0);
            return 0L;
        }
    }
}

