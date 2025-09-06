package androidx.webkit.internal;

import android.content.Context;
import android.net.Uri;
import android.webkit.SafeBrowsingResponse;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import java.util.List;

public class ApiHelperForOMR1 {
    public static void backToSafety(SafeBrowsingResponse safeBrowsingResponse0, boolean z) {
        safeBrowsingResponse0.backToSafety(z);
    }

    public static Uri getSafeBrowsingPrivacyPolicyUrl() {
        return WebView.getSafeBrowsingPrivacyPolicyUrl();
    }

    public static void proceed(SafeBrowsingResponse safeBrowsingResponse0, boolean z) {
        safeBrowsingResponse0.proceed(z);
    }

    public static void setSafeBrowsingWhitelist(List list0, ValueCallback valueCallback0) {
        WebView.setSafeBrowsingWhitelist(list0, valueCallback0);
    }

    public static void showInterstitial(SafeBrowsingResponse safeBrowsingResponse0, boolean z) {
        safeBrowsingResponse0.showInterstitial(z);
    }

    public static void startSafeBrowsing(Context context0, ValueCallback valueCallback0) {
        WebView.startSafeBrowsing(context0, valueCallback0);
    }
}

