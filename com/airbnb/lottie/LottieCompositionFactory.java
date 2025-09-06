package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.airbnb.lottie.model.LottieCompositionCache;
import com.airbnb.lottie.network.NetworkFetcher;
import com.airbnb.lottie.parser.LottieCompositionMoshiParser;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import okio.Okio;
import org.json.JSONObject;

public class LottieCompositionFactory {
    private static final Map taskCache;

    static {
        LottieCompositionFactory.taskCache = new HashMap();
    }

    private static LottieTask cache(String s, Callable callable0) {
        LottieComposition lottieComposition0 = s == null ? null : LottieCompositionCache.getInstance().get(s);
        if(lottieComposition0 != null) {
            return new LottieTask(new Callable() {
                public LottieResult call() {
                    return new LottieResult(lottieComposition0);
                }

                @Override
                public Object call() throws Exception {
                    return this.call();
                }
            });
        }
        if(s != null) {
            Map map0 = LottieCompositionFactory.taskCache;
            if(map0.containsKey(s)) {
                return (LottieTask)map0.get(s);
            }
        }
        LottieTask lottieTask0 = new LottieTask(callable0);
        lottieTask0.addListener(new LottieListener() {
            public void onResult(LottieComposition lottieComposition0) {
                LottieCompositionFactory.taskCache.remove(s);
            }

            @Override  // com.airbnb.lottie.LottieListener
            public void onResult(Object object0) {
                this.onResult(((LottieComposition)object0));
            }
        });
        lottieTask0.addFailureListener(new LottieListener() {
            @Override  // com.airbnb.lottie.LottieListener
            public void onResult(Object object0) {
                this.onResult(((Throwable)object0));
            }

            public void onResult(Throwable throwable0) {
                LottieCompositionFactory.taskCache.remove(s);
            }
        });
        LottieCompositionFactory.taskCache.put(s, lottieTask0);
        return lottieTask0;
    }

    private static LottieImageAsset findImageAssetForFileName(LottieComposition lottieComposition0, String s) {
        for(Object object0: lottieComposition0.getImages().values()) {
            LottieImageAsset lottieImageAsset0 = (LottieImageAsset)object0;
            if(lottieImageAsset0.getFileName().equals(s)) {
                return lottieImageAsset0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    public static LottieTask fromAsset(Context context0, String s) {
        return LottieCompositionFactory.cache(s, new Callable() {
            public LottieResult call() {
                return LottieCompositionFactory.fromAssetSync(context0.getApplicationContext(), s);
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }
        });
    }

    public static LottieResult fromAssetSync(Context context0, String s) {
        try {
            return s.endsWith(".zip") ? LottieCompositionFactory.fromZipStreamSync(new ZipInputStream(context0.getAssets().open(s)), "asset_" + s) : LottieCompositionFactory.fromJsonInputStreamSync(context0.getAssets().open(s), "asset_" + s);
        }
        catch(IOException iOException0) {
            return new LottieResult(iOException0);
        }
    }

    @Deprecated
    public static LottieTask fromJson(JSONObject jSONObject0, String s) {
        return LottieCompositionFactory.cache(s, new Callable() {
            public LottieResult call() {
                return LottieCompositionFactory.fromJsonSync(jSONObject0, s);
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }
        });
    }

    public static LottieTask fromJsonInputStream(InputStream inputStream0, String s) {
        return LottieCompositionFactory.cache(s, new Callable() {
            public LottieResult call() {
                return LottieCompositionFactory.fromJsonInputStreamSync(inputStream0, s);
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }
        });
    }

    public static LottieResult fromJsonInputStreamSync(InputStream inputStream0, String s) {
        return LottieCompositionFactory.fromJsonInputStreamSync(inputStream0, s, true);
    }

    private static LottieResult fromJsonInputStreamSync(InputStream inputStream0, String s, boolean z) {
        LottieResult lottieResult0;
        try {
            lottieResult0 = LottieCompositionFactory.fromJsonReaderSync(JsonReader.of(Okio.buffer(Okio.source(inputStream0))), s);
        }
        catch(Throwable throwable0) {
            if(z) {
                Utils.closeQuietly(inputStream0);
            }
            throw throwable0;
        }
        if(z) {
            Utils.closeQuietly(inputStream0);
        }
        return lottieResult0;
    }

    public static LottieTask fromJsonReader(JsonReader jsonReader0, String s) {
        return LottieCompositionFactory.cache(s, new Callable() {
            public LottieResult call() {
                return LottieCompositionFactory.fromJsonReaderSync(jsonReader0, s);
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }
        });
    }

    public static LottieResult fromJsonReaderSync(JsonReader jsonReader0, String s) {
        return LottieCompositionFactory.fromJsonReaderSyncInternal(jsonReader0, s, true);
    }

    private static LottieResult fromJsonReaderSyncInternal(JsonReader jsonReader0, String s, boolean z) {
        LottieResult lottieResult1;
        LottieResult lottieResult0;
        try {
            try {
                LottieComposition lottieComposition0 = LottieCompositionMoshiParser.parse(jsonReader0);
                if(s != null) {
                    LottieCompositionCache.getInstance().put(s, lottieComposition0);
                }
                lottieResult0 = new LottieResult(lottieComposition0);
                goto label_14;
            }
            catch(Exception exception0) {
            }
            lottieResult1 = new LottieResult(exception0);
            if(z) {
                goto label_8;
            }
            return lottieResult1;
        }
        catch(Throwable throwable0) {
            goto label_11;
        }
    label_8:
        Utils.closeQuietly(jsonReader0);
        return lottieResult1;
    label_11:
        if(z) {
            Utils.closeQuietly(jsonReader0);
        }
        throw throwable0;
    label_14:
        if(z) {
            Utils.closeQuietly(jsonReader0);
        }
        return lottieResult0;
    }

    public static LottieTask fromJsonString(String s, String s1) {
        return LottieCompositionFactory.cache(s1, new Callable() {
            public LottieResult call() {
                return LottieCompositionFactory.fromJsonStringSync(s, s1);
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }
        });
    }

    public static LottieResult fromJsonStringSync(String s, String s1) {
        return LottieCompositionFactory.fromJsonReaderSync(JsonReader.of(Okio.buffer(Okio.source(new ByteArrayInputStream(s.getBytes())))), s1);
    }

    @Deprecated
    public static LottieResult fromJsonSync(JSONObject jSONObject0, String s) {
        return LottieCompositionFactory.fromJsonStringSync(jSONObject0.toString(), s);
    }

    public static LottieTask fromRawRes(Context context0, int v) {
        WeakReference weakReference0 = new WeakReference(context0);
        Context context1 = context0.getApplicationContext();
        return LottieCompositionFactory.cache(LottieCompositionFactory.rawResCacheKey(context0, v), new Callable() {
            public LottieResult call() {
                Context context0 = (Context)weakReference0.get();
                if(context0 == null) {
                    context0 = context1;
                }
                return LottieCompositionFactory.fromRawResSync(context0, v);
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }
        });
    }

    public static LottieResult fromRawResSync(Context context0, int v) {
        try {
            return LottieCompositionFactory.fromJsonInputStreamSync(context0.getResources().openRawResource(v), LottieCompositionFactory.rawResCacheKey(context0, v));
        }
        catch(Resources.NotFoundException resources$NotFoundException0) {
            return new LottieResult(resources$NotFoundException0);
        }
    }

    public static LottieTask fromUrl(Context context0, String s) {
        return LottieCompositionFactory.cache(("url_" + s), new Callable() {
            public LottieResult call() {
                return NetworkFetcher.fetchSync(context0, s);
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }
        });
    }

    public static LottieResult fromUrlSync(Context context0, String s) {
        return NetworkFetcher.fetchSync(context0, s);
    }

    public static LottieTask fromZipStream(ZipInputStream zipInputStream0, String s) {
        return LottieCompositionFactory.cache(s, new Callable() {
            public LottieResult call() {
                return LottieCompositionFactory.fromZipStreamSync(zipInputStream0, s);
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }
        });
    }

    public static LottieResult fromZipStreamSync(ZipInputStream zipInputStream0, String s) {
        try {
            return LottieCompositionFactory.fromZipStreamSyncInternal(zipInputStream0, s);
        }
        finally {
            Utils.closeQuietly(zipInputStream0);
        }
    }

    private static LottieResult fromZipStreamSyncInternal(ZipInputStream zipInputStream0, String s) {
        LottieComposition lottieComposition0;
        HashMap hashMap0 = new HashMap();
        try {
            ZipEntry zipEntry0 = zipInputStream0.getNextEntry();
            lottieComposition0 = null;
            while(zipEntry0 != null) {
                String s1 = zipEntry0.getName();
                if(s1.contains("__MACOSX")) {
                    zipInputStream0.closeEntry();
                }
                else if(zipEntry0.getName().contains(".json")) {
                    lottieComposition0 = (LottieComposition)LottieCompositionFactory.fromJsonReaderSyncInternal(JsonReader.of(Okio.buffer(Okio.source(zipInputStream0))), null, false).getValue();
                }
                else if(s1.contains(".png") || s1.contains(".webp")) {
                    String[] arr_s = s1.split("/");
                    hashMap0.put(arr_s[arr_s.length - 1], BitmapFactory.decodeStream(zipInputStream0));
                }
                else {
                    zipInputStream0.closeEntry();
                }
                zipEntry0 = zipInputStream0.getNextEntry();
            }
        }
        catch(IOException iOException0) {
            return new LottieResult(iOException0);
        }
        if(lottieComposition0 == null) {
            return new LottieResult(new IllegalArgumentException("Unable to parse composition"));
        }
        for(Object object0: hashMap0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            LottieImageAsset lottieImageAsset0 = LottieCompositionFactory.findImageAssetForFileName(lottieComposition0, ((String)map$Entry0.getKey()));
            if(lottieImageAsset0 != null) {
                lottieImageAsset0.setBitmap(Utils.resizeBitmapIfNeeded(((Bitmap)map$Entry0.getValue()), lottieImageAsset0.getWidth(), lottieImageAsset0.getHeight()));
            }
        }
        for(Object object1: lottieComposition0.getImages().entrySet()) {
            Map.Entry map$Entry1 = (Map.Entry)object1;
            if(((LottieImageAsset)map$Entry1.getValue()).getBitmap() == null) {
                return new LottieResult(new IllegalStateException("There is no image for " + ((LottieImageAsset)map$Entry1.getValue()).getFileName()));
            }
            if(false) {
                break;
            }
        }
        if(s != null) {
            LottieCompositionCache.getInstance().put(s, lottieComposition0);
        }
        return new LottieResult(lottieComposition0);
    }

    private static boolean isNightMode(Context context0) {
        return (context0.getResources().getConfiguration().uiMode & 0x30) == 0x20;
    }

    // 去混淆评级： 低(20)
    private static String rawResCacheKey(Context context0, int v) {
        return "rawRes" + (LottieCompositionFactory.isNightMode(context0) ? "_night_" : "_day_") + v;
    }

    public static void setMaxCacheSize(int v) {
        LottieCompositionCache.getInstance().resize(v);
    }
}

