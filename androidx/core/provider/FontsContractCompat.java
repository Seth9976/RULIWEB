package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import androidx.core.content.res.ResourcesCompat.FontCallback;
import androidx.core.graphics.TypefaceCompat.ResourcesCallbackAdapter;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.graphics.TypefaceCompatUtil;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.UByte..ExternalSyntheticBackport0;

public class FontsContractCompat {
    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";

    }

    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final List mFonts;
        private final int mStatusCode;

        FontFamilyResult(int v, List list0) {
            this.mStatusCode = v;
            this.mFonts = list0;
        }

        @Deprecated
        public FontFamilyResult(int v, FontInfo[] arr_fontsContractCompat$FontInfo) {
            this.mStatusCode = v;
            this.mFonts = Collections.singletonList(arr_fontsContractCompat$FontInfo);
        }

        static FontFamilyResult create(int v, List list0) {
            return new FontFamilyResult(v, list0);
        }

        static FontFamilyResult create(int v, FontInfo[] arr_fontsContractCompat$FontInfo) {
            return new FontFamilyResult(v, arr_fontsContractCompat$FontInfo);
        }

        public FontInfo[] getFonts() {
            return (FontInfo[])this.mFonts.get(0);
        }

        public List getFontsWithFallbacks() {
            return this.mFonts;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        boolean hasFallback() {
            return this.mFonts.size() > 1;
        }
    }

    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        @Deprecated
        public FontInfo(Uri uri0, int v, int v1, boolean z, int v2) {
            this.mUri = (Uri)Preconditions.checkNotNull(uri0);
            this.mTtcIndex = v;
            this.mWeight = v1;
            this.mItalic = z;
            this.mResultCode = v2;
        }

        static FontInfo create(Uri uri0, int v, int v1, boolean z, int v2) {
            return new FontInfo(uri0, v, v1, z, v2);
        }

        public int getResultCode() {
            return this.mResultCode;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }
    }

    public static class FontRequestCallback {
        @Retention(RetentionPolicy.SOURCE)
        public @interface FontRequestFailReason {
        }

        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
        @Deprecated
        public static final int RESULT_OK;
        static final int RESULT_SUCCESS;

        public void onTypefaceRequestFailed(int v) {
        }

        public void onTypefaceRetrieved(Typeface typeface0) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TypefaceStyle {
    }

    @Deprecated
    public static final String PARCEL_FONT_RESULTS = "font_results";
    @Deprecated
    static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    @Deprecated
    static final int RESULT_CODE_WRONG_CERTIFICATES = -2;

    public static Typeface buildTypeface(Context context0, CancellationSignal cancellationSignal0, FontInfo[] arr_fontsContractCompat$FontInfo) {
        return TypefaceCompat.createFromFontInfo(context0, cancellationSignal0, arr_fontsContractCompat$FontInfo, 0);
    }

    public static FontFamilyResult fetchFonts(Context context0, CancellationSignal cancellationSignal0, FontRequest fontRequest0) throws PackageManager.NameNotFoundException {
        return FontProvider.getFontFamilyResult(context0, UByte..ExternalSyntheticBackport0.m(fontRequest0), cancellationSignal0);
    }

    @Deprecated
    public static Typeface getFontSync(Context context0, FontRequest fontRequest0, FontCallback resourcesCompat$FontCallback0, Handler handler0, boolean z, int v, int v1) {
        ResourcesCallbackAdapter typefaceCompat$ResourcesCallbackAdapter0 = new ResourcesCallbackAdapter(resourcesCompat$FontCallback0);
        Handler handler1 = FontCallback.getHandler(handler0);
        return FontsContractCompat.requestFont(context0, UByte..ExternalSyntheticBackport0.m(fontRequest0), v1, z, v, handler1, typefaceCompat$ResourcesCallbackAdapter0);
    }

    @Deprecated
    public static ProviderInfo getProvider(PackageManager packageManager0, FontRequest fontRequest0, Resources resources0) throws PackageManager.NameNotFoundException {
        return FontProvider.getProvider(packageManager0, fontRequest0, resources0);
    }

    @Deprecated
    public static Map prepareFontData(Context context0, FontInfo[] arr_fontsContractCompat$FontInfo, CancellationSignal cancellationSignal0) {
        return TypefaceCompatUtil.readFontInfoIntoByteBuffer(context0, arr_fontsContractCompat$FontInfo, cancellationSignal0);
    }

    public static Typeface requestFont(Context context0, FontRequest fontRequest0, int v, boolean z, int v1, Handler handler0, FontRequestCallback fontsContractCompat$FontRequestCallback0) {
        return FontsContractCompat.requestFont(context0, UByte..ExternalSyntheticBackport0.m(fontRequest0), v, z, v1, handler0, fontsContractCompat$FontRequestCallback0);
    }

    public static Typeface requestFont(Context context0, List list0, int v, boolean z, int v1, Handler handler0, FontRequestCallback fontsContractCompat$FontRequestCallback0) {
        CallbackWrapper callbackWrapper0 = new CallbackWrapper(fontsContractCompat$FontRequestCallback0, RequestExecutor.createHandlerExecutor(handler0));
        if(z) {
            if(list0.size() > 1) {
                throw new IllegalArgumentException("Fallbacks with blocking fetches are not supported for performance reasons");
            }
            return FontRequestWorker.requestFontSync(context0, ((FontRequest)list0.get(0)), callbackWrapper0, v, v1);
        }
        return FontRequestWorker.requestFontAsync(context0, list0, v, null, callbackWrapper0);
    }

    public static void requestFont(Context context0, FontRequest fontRequest0, int v, Executor executor0, Executor executor1, FontRequestCallback fontsContractCompat$FontRequestCallback0) {
        CallbackWrapper callbackWrapper0 = new CallbackWrapper(fontsContractCompat$FontRequestCallback0, executor1);
        FontRequestWorker.requestFontAsync(context0.getApplicationContext(), UByte..ExternalSyntheticBackport0.m(fontRequest0), v, executor0, callbackWrapper0);
    }

    @Deprecated
    public static void requestFont(Context context0, FontRequest fontRequest0, FontRequestCallback fontsContractCompat$FontRequestCallback0, Handler handler0) {
        CallbackWrapper callbackWrapper0 = new CallbackWrapper(fontsContractCompat$FontRequestCallback0);
        Executor executor0 = RequestExecutor.createHandlerExecutor(handler0);
        FontRequestWorker.requestFontAsync(context0.getApplicationContext(), UByte..ExternalSyntheticBackport0.m(fontRequest0), 0, executor0, callbackWrapper0);
    }

    public static void requestFontWithFallbackChain(Context context0, List list0, int v, Executor executor0, Executor executor1, FontRequestCallback fontsContractCompat$FontRequestCallback0) {
        CallbackWrapper callbackWrapper0 = new CallbackWrapper(fontsContractCompat$FontRequestCallback0, executor1);
        FontRequestWorker.requestFontAsync(context0.getApplicationContext(), list0, v, executor0, callbackWrapper0);
    }

    @Deprecated
    public static void resetCache() {
        FontRequestWorker.resetTypefaceCache();
    }

    public static void resetTypefaceCache() {
        FontRequestWorker.resetTypefaceCache();
    }
}

