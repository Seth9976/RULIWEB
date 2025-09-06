package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.util.Consumer;
import androidx.tracing.Trace;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import kotlin.UByte..ExternalSyntheticBackport0;

class FontRequestWorker {
    static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(int v) {
            this.mTypeface = null;
            this.mResult = v;
        }

        TypefaceResult(Typeface typeface0) {
            this.mTypeface = typeface0;
            this.mResult = 0;
        }

        boolean isSuccess() {
            return this.mResult == 0;
        }
    }

    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE;
    static final Object LOCK;
    static final SimpleArrayMap PENDING_REPLIES;
    static final LruCache sTypefaceCache;

    static {
        FontRequestWorker.sTypefaceCache = new LruCache(16);
        FontRequestWorker.DEFAULT_EXECUTOR_SERVICE = RequestExecutor.createDefaultExecutor("fonts-androidx", 10, 10000);
        FontRequestWorker.LOCK = new Object();
        FontRequestWorker.PENDING_REPLIES = new SimpleArrayMap();
    }

    private static String createCacheId(List list0, int v) {
        StringBuilder stringBuilder0 = new StringBuilder();
        for(int v1 = 0; v1 < list0.size(); ++v1) {
            stringBuilder0.append(((FontRequest)list0.get(v1)).getId());
            stringBuilder0.append("-");
            stringBuilder0.append(v);
            if(v1 < list0.size() - 1) {
                stringBuilder0.append(";");
            }
        }
        return stringBuilder0.toString();
    }

    private static int getFontFamilyResultStatus(FontFamilyResult fontsContractCompat$FontFamilyResult0) {
        int v = 1;
        switch(fontsContractCompat$FontFamilyResult0.getStatusCode()) {
            case 0: {
                FontInfo[] arr_fontsContractCompat$FontInfo = fontsContractCompat$FontFamilyResult0.getFonts();
                if(arr_fontsContractCompat$FontInfo != null && arr_fontsContractCompat$FontInfo.length != 0) {
                    v = 0;
                    for(int v1 = 0; v1 < arr_fontsContractCompat$FontInfo.length; ++v1) {
                        int v2 = arr_fontsContractCompat$FontInfo[v1].getResultCode();
                        if(v2 != 0) {
                            return v2 >= 0 ? v2 : -3;
                        }
                    }
                }
                return v;
            }
            case 1: {
                return -2;
            }
            default: {
                return -3;
            }
        }
    }

    static TypefaceResult getFontSync(String s, Context context0, List list0, int v) {
        FontFamilyResult fontsContractCompat$FontFamilyResult0;
        Trace.beginSection("getFontSync");
        try {
            LruCache lruCache0 = FontRequestWorker.sTypefaceCache;
            Typeface typeface0 = (Typeface)lruCache0.get(s);
            if(typeface0 != null) {
                return new TypefaceResult(typeface0);
            }
            try {
                fontsContractCompat$FontFamilyResult0 = FontProvider.getFontFamilyResult(context0, list0, null);
            }
            catch(PackageManager.NameNotFoundException unused_ex) {
                return new TypefaceResult(-1);
            }
            int v2 = FontRequestWorker.getFontFamilyResultStatus(fontsContractCompat$FontFamilyResult0);
            if(v2 != 0) {
                return new TypefaceResult(v2);
            }
            Typeface typeface1 = !fontsContractCompat$FontFamilyResult0.hasFallback() || Build.VERSION.SDK_INT < 29 ? TypefaceCompat.createFromFontInfo(context0, null, fontsContractCompat$FontFamilyResult0.getFonts(), v) : TypefaceCompat.createFromFontInfoWithFallback(context0, null, fontsContractCompat$FontFamilyResult0.getFontsWithFallbacks(), v);
            if(typeface1 != null) {
                lruCache0.put(s, typeface1);
                return new TypefaceResult(typeface1);
            }
            return new TypefaceResult(-3);
        }
        finally {
            Trace.endSection();
        }
    }

    static Typeface requestFontAsync(Context context0, List list0, int v, Executor executor0, CallbackWrapper callbackWrapper0) {
        String s = FontRequestWorker.createCacheId(list0, v);
        Typeface typeface0 = (Typeface)FontRequestWorker.sTypefaceCache.get(s);
        if(typeface0 != null) {
            callbackWrapper0.onTypefaceResult(new TypefaceResult(typeface0));
            return typeface0;
        }
        androidx.core.provider.FontRequestWorker.2 fontRequestWorker$20 = new Consumer() {
            public void accept(TypefaceResult fontRequestWorker$TypefaceResult0) {
                if(fontRequestWorker$TypefaceResult0 == null) {
                    fontRequestWorker$TypefaceResult0 = new TypefaceResult(-3);
                }
                callbackWrapper0.onTypefaceResult(fontRequestWorker$TypefaceResult0);
            }

            @Override  // androidx.core.util.Consumer
            public void accept(Object object0) {
                this.accept(((TypefaceResult)object0));
            }
        };
        synchronized(FontRequestWorker.LOCK) {
            SimpleArrayMap simpleArrayMap0 = FontRequestWorker.PENDING_REPLIES;
            ArrayList arrayList0 = (ArrayList)simpleArrayMap0.get(s);
            if(arrayList0 != null) {
                arrayList0.add(fontRequestWorker$20);
                return null;
            }
            ArrayList arrayList1 = new ArrayList();
            arrayList1.add(fontRequestWorker$20);
            simpleArrayMap0.put(s, arrayList1);
        }
        androidx.core.provider.FontRequestWorker.3 fontRequestWorker$30 = new Callable() {
            public TypefaceResult call() {
                try {
                    return FontRequestWorker.getFontSync(s, context0, list0, v);
                }
                catch(Throwable unused_ex) {
                    return new TypefaceResult(-3);
                }
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }
        };
        if(executor0 == null) {
            executor0 = FontRequestWorker.DEFAULT_EXECUTOR_SERVICE;
        }
        RequestExecutor.execute(executor0, fontRequestWorker$30, new Consumer() {
            public void accept(TypefaceResult fontRequestWorker$TypefaceResult0) {
                ArrayList arrayList0;
                synchronized(FontRequestWorker.LOCK) {
                    arrayList0 = (ArrayList)FontRequestWorker.PENDING_REPLIES.get(s);
                    if(arrayList0 == null) {
                        return;
                    }
                    FontRequestWorker.PENDING_REPLIES.remove(s);
                }
                for(int v1 = 0; v1 < arrayList0.size(); ++v1) {
                    ((Consumer)arrayList0.get(v1)).accept(fontRequestWorker$TypefaceResult0);
                }
            }

            @Override  // androidx.core.util.Consumer
            public void accept(Object object0) {
                this.accept(((TypefaceResult)object0));
            }
        });
        return null;
    }

    static Typeface requestFontSync(Context context0, FontRequest fontRequest0, CallbackWrapper callbackWrapper0, int v, int v1) {
        String s = FontRequestWorker.createCacheId(UByte..ExternalSyntheticBackport0.m(fontRequest0), v);
        Typeface typeface0 = (Typeface)FontRequestWorker.sTypefaceCache.get(s);
        if(typeface0 != null) {
            callbackWrapper0.onTypefaceResult(new TypefaceResult(typeface0));
            return typeface0;
        }
        if(v1 == -1) {
            TypefaceResult fontRequestWorker$TypefaceResult0 = FontRequestWorker.getFontSync(s, context0, UByte..ExternalSyntheticBackport0.m(fontRequest0), v);
            callbackWrapper0.onTypefaceResult(fontRequestWorker$TypefaceResult0);
            return fontRequestWorker$TypefaceResult0.mTypeface;
        }
        androidx.core.provider.FontRequestWorker.1 fontRequestWorker$10 = new Callable() {
            public TypefaceResult call() {
                List list0 = UByte..ExternalSyntheticBackport0.m(fontRequest0);
                return FontRequestWorker.getFontSync(s, context0, list0, v);
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }
        };
        try {
            TypefaceResult fontRequestWorker$TypefaceResult1 = (TypefaceResult)RequestExecutor.submit(FontRequestWorker.DEFAULT_EXECUTOR_SERVICE, fontRequestWorker$10, v1);
            callbackWrapper0.onTypefaceResult(fontRequestWorker$TypefaceResult1);
            return fontRequestWorker$TypefaceResult1.mTypeface;
        }
        catch(InterruptedException unused_ex) {
            callbackWrapper0.onTypefaceResult(new TypefaceResult(-3));
            return null;
        }
    }

    static void resetTypefaceCache() {
        FontRequestWorker.sTypefaceCache.evictAll();
    }
}

