package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat.FamilyResourceEntry;
import androidx.core.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import androidx.core.content.res.FontResourcesParserCompat.ProviderResourceEntry;
import androidx.core.content.res.ResourcesCompat.FontCallback;
import androidx.core.provider.FontsContractCompat.FontInfo;
import androidx.core.provider.FontsContractCompat.FontRequestCallback;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Preconditions;
import androidx.tracing.Trace;
import java.util.List;
import kotlin.UByte..ExternalSyntheticBackport0;

public class TypefaceCompat {
    public static class ResourcesCallbackAdapter extends FontRequestCallback {
        private FontCallback mFontCallback;

        public ResourcesCallbackAdapter(FontCallback resourcesCompat$FontCallback0) {
            this.mFontCallback = resourcesCompat$FontCallback0;
        }

        @Override  // androidx.core.provider.FontsContractCompat$FontRequestCallback
        public void onTypefaceRequestFailed(int v) {
            FontCallback resourcesCompat$FontCallback0 = this.mFontCallback;
            if(resourcesCompat$FontCallback0 != null) {
                resourcesCompat$FontCallback0.onFontRetrievalFailed(v);
            }
        }

        @Override  // androidx.core.provider.FontsContractCompat$FontRequestCallback
        public void onTypefaceRetrieved(Typeface typeface0) {
            FontCallback resourcesCompat$FontCallback0 = this.mFontCallback;
            if(resourcesCompat$FontCallback0 != null) {
                resourcesCompat$FontCallback0.onFontRetrieved(typeface0);
            }
        }
    }

    public static final boolean DOWNLOADABLE_FALLBACK_DEBUG = false;
    public static final boolean DOWNLOADABLE_FONT_TRACING = true;
    private static final LruCache sTypefaceCache;
    private static final TypefaceCompatBaseImpl sTypefaceCompatImpl;

    static {
        Trace.beginSection("TypefaceCompat static init");
        if(Build.VERSION.SDK_INT >= 29) {
            TypefaceCompat.sTypefaceCompatImpl = new TypefaceCompatApi29Impl();
        }
        else if(Build.VERSION.SDK_INT >= 28) {
            TypefaceCompat.sTypefaceCompatImpl = new TypefaceCompatApi28Impl();
        }
        else if(Build.VERSION.SDK_INT >= 26) {
            TypefaceCompat.sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
        }
        else if(Build.VERSION.SDK_INT < 24 || !TypefaceCompatApi24Impl.isUsable()) {
            TypefaceCompat.sTypefaceCompatImpl = new TypefaceCompatApi21Impl();
        }
        else {
            TypefaceCompat.sTypefaceCompatImpl = new TypefaceCompatApi24Impl();
        }
        TypefaceCompat.sTypefaceCache = new LruCache(16);
        Trace.endSection();
    }

    public static void clearCache() {
        TypefaceCompat.sTypefaceCache.evictAll();
    }

    public static Typeface create(Context context0, Typeface typeface0, int v) {
        if(context0 == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        return Typeface.create(typeface0, v);
    }

    public static Typeface create(Context context0, Typeface typeface0, int v, boolean z) {
        if(context0 == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        Preconditions.checkArgumentInRange(v, 1, 1000, "weight");
        if(typeface0 == null) {
            typeface0 = Typeface.DEFAULT;
        }
        return TypefaceCompat.sTypefaceCompatImpl.createWeightStyle(context0, typeface0, v, z);
    }

    public static Typeface createFromFontInfo(Context context0, CancellationSignal cancellationSignal0, FontInfo[] arr_fontsContractCompat$FontInfo, int v) {
        Trace.beginSection("TypefaceCompat.createFromFontInfo");
        try {
            return TypefaceCompat.sTypefaceCompatImpl.createFromFontInfo(context0, cancellationSignal0, arr_fontsContractCompat$FontInfo, v);
        }
        finally {
            Trace.endSection();
        }
    }

    public static Typeface createFromFontInfoWithFallback(Context context0, CancellationSignal cancellationSignal0, List list0, int v) {
        Trace.beginSection("TypefaceCompat.createFromFontInfoWithFallback");
        try {
            return TypefaceCompat.sTypefaceCompatImpl.createFromFontInfoWithFallback(context0, cancellationSignal0, list0, v);
        }
        finally {
            Trace.endSection();
        }
    }

    @Deprecated
    public static Typeface createFromResourcesFamilyXml(Context context0, FamilyResourceEntry fontResourcesParserCompat$FamilyResourceEntry0, Resources resources0, int v, int v1, FontCallback resourcesCompat$FontCallback0, Handler handler0, boolean z) {
        return TypefaceCompat.createFromResourcesFamilyXml(context0, fontResourcesParserCompat$FamilyResourceEntry0, resources0, v, null, 0, v1, resourcesCompat$FontCallback0, handler0, z);
    }

    public static Typeface createFromResourcesFamilyXml(Context context0, FamilyResourceEntry fontResourcesParserCompat$FamilyResourceEntry0, Resources resources0, int v, String s, int v1, int v2, FontCallback resourcesCompat$FontCallback0, Handler handler0, boolean z) {
        Typeface typeface1;
        boolean z1;
        if(fontResourcesParserCompat$FamilyResourceEntry0 instanceof ProviderResourceEntry) {
            Typeface typeface0 = TypefaceCompat.getSystemFontFamily(((ProviderResourceEntry)fontResourcesParserCompat$FamilyResourceEntry0).getSystemFontFamilyName());
            if(typeface0 != null) {
                if(resourcesCompat$FontCallback0 != null) {
                    resourcesCompat$FontCallback0.callbackSuccessAsync(typeface0, handler0);
                }
                return typeface0;
            }
            if(!z) {
                z1 = resourcesCompat$FontCallback0 == null;
            }
            else if(((ProviderResourceEntry)fontResourcesParserCompat$FamilyResourceEntry0).getFetchStrategy() == 0) {
                z1 = true;
            }
            else {
                z1 = false;
            }
            int v3 = z ? ((ProviderResourceEntry)fontResourcesParserCompat$FamilyResourceEntry0).getTimeout() : -1;
            Handler handler1 = FontCallback.getHandler(handler0);
            ResourcesCallbackAdapter typefaceCompat$ResourcesCallbackAdapter0 = new ResourcesCallbackAdapter(resourcesCompat$FontCallback0);
            typeface1 = FontsContractCompat.requestFont(context0, (((ProviderResourceEntry)fontResourcesParserCompat$FamilyResourceEntry0).getFallbackRequest() == null ? UByte..ExternalSyntheticBackport0.m(((ProviderResourceEntry)fontResourcesParserCompat$FamilyResourceEntry0).getRequest()) : UByte..ExternalSyntheticBackport0.m(((ProviderResourceEntry)fontResourcesParserCompat$FamilyResourceEntry0).getRequest(), ((ProviderResourceEntry)fontResourcesParserCompat$FamilyResourceEntry0).getFallbackRequest())), v2, z1, v3, handler1, typefaceCompat$ResourcesCallbackAdapter0);
        }
        else {
            typeface1 = TypefaceCompat.sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context0, ((FontFamilyFilesResourceEntry)fontResourcesParserCompat$FamilyResourceEntry0), resources0, v2);
            if(resourcesCompat$FontCallback0 != null) {
                if(typeface1 == null) {
                    resourcesCompat$FontCallback0.callbackFailAsync(-3, handler0);
                }
                else {
                    resourcesCompat$FontCallback0.callbackSuccessAsync(typeface1, handler0);
                }
            }
        }
        if(typeface1 != null) {
            String s1 = TypefaceCompat.createResourceUid(resources0, v, s, v1, v2);
            TypefaceCompat.sTypefaceCache.put(s1, typeface1);
        }
        return typeface1;
    }

    @Deprecated
    public static Typeface createFromResourcesFontFile(Context context0, Resources resources0, int v, String s, int v1) {
        return TypefaceCompat.createFromResourcesFontFile(context0, resources0, v, s, 0, v1);
    }

    public static Typeface createFromResourcesFontFile(Context context0, Resources resources0, int v, String s, int v1, int v2) {
        Typeface typeface0 = TypefaceCompat.sTypefaceCompatImpl.createFromResourcesFontFile(context0, resources0, v, s, v2);
        if(typeface0 != null) {
            String s1 = TypefaceCompat.createResourceUid(resources0, v, s, v1, v2);
            TypefaceCompat.sTypefaceCache.put(s1, typeface0);
        }
        return typeface0;
    }

    private static String createResourceUid(Resources resources0, int v, String s, int v1, int v2) {
        return resources0.getResourcePackageName(v) + '-' + s + '-' + v1 + '-' + v + '-' + v2;
    }

    @Deprecated
    public static Typeface findFromCache(Resources resources0, int v, int v1) {
        return TypefaceCompat.findFromCache(resources0, v, null, 0, v1);
    }

    public static Typeface findFromCache(Resources resources0, int v, String s, int v1, int v2) {
        String s1 = TypefaceCompat.createResourceUid(resources0, v, s, v1, v2);
        return (Typeface)TypefaceCompat.sTypefaceCache.get(s1);
    }

    private static Typeface getBestFontFromFamily(Context context0, Typeface typeface0, int v) {
        FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry0 = TypefaceCompat.sTypefaceCompatImpl.getFontFamily(typeface0);
        return fontResourcesParserCompat$FontFamilyFilesResourceEntry0 == null ? null : TypefaceCompat.sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context0, fontResourcesParserCompat$FontFamilyFilesResourceEntry0, context0.getResources(), v);
    }

    private static Typeface getSystemFontFamily(String s) {
        if(s != null && !s.isEmpty()) {
            Typeface typeface0 = Typeface.create(s, 0);
            return typeface0 == null || typeface0.equals(Typeface.create(Typeface.DEFAULT, 0)) ? null : typeface0;
        }
        return null;
    }
}

