package androidx.browser.trusted;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent.Builder;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsSession;
import androidx.browser.trusted.sharing.ShareData;
import androidx.browser.trusted.sharing.ShareTarget;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrustedWebActivityIntentBuilder {
    public static final String EXTRA_ADDITIONAL_TRUSTED_ORIGINS = "android.support.customtabs.extra.ADDITIONAL_TRUSTED_ORIGINS";
    public static final String EXTRA_DISPLAY_MODE = "androidx.browser.trusted.extra.DISPLAY_MODE";
    public static final String EXTRA_SCREEN_ORIENTATION = "androidx.browser.trusted.extra.SCREEN_ORIENTATION";
    public static final String EXTRA_SHARE_DATA = "androidx.browser.trusted.extra.SHARE_DATA";
    public static final String EXTRA_SHARE_TARGET = "androidx.browser.trusted.extra.SHARE_TARGET";
    public static final String EXTRA_SPLASH_SCREEN_PARAMS = "androidx.browser.trusted.EXTRA_SPLASH_SCREEN_PARAMS";
    private List mAdditionalTrustedOrigins;
    private TrustedWebActivityDisplayMode mDisplayMode;
    private final Builder mIntentBuilder;
    private int mScreenOrientation;
    private ShareData mShareData;
    private ShareTarget mShareTarget;
    private Bundle mSplashScreenParams;
    private final Uri mUri;

    public TrustedWebActivityIntentBuilder(Uri uri0) {
        this.mIntentBuilder = new Builder();
        this.mDisplayMode = new DefaultMode();
        this.mScreenOrientation = 0;
        this.mUri = uri0;
    }

    public TrustedWebActivityIntent build(CustomTabsSession customTabsSession0) {
        if(customTabsSession0 == null) {
            throw new NullPointerException("CustomTabsSession is required for launching a TWA");
        }
        this.mIntentBuilder.setSession(customTabsSession0);
        Intent intent0 = this.mIntentBuilder.build().intent;
        intent0.setData(this.mUri);
        intent0.putExtra("android.support.customtabs.extra.LAUNCH_AS_TRUSTED_WEB_ACTIVITY", true);
        if(this.mAdditionalTrustedOrigins != null) {
            intent0.putExtra("android.support.customtabs.extra.ADDITIONAL_TRUSTED_ORIGINS", new ArrayList(this.mAdditionalTrustedOrigins));
        }
        Bundle bundle0 = this.mSplashScreenParams;
        if(bundle0 != null) {
            intent0.putExtra("androidx.browser.trusted.EXTRA_SPLASH_SCREEN_PARAMS", bundle0);
        }
        List list0 = Collections.EMPTY_LIST;
        ShareTarget shareTarget0 = this.mShareTarget;
        if(shareTarget0 != null && this.mShareData != null) {
            intent0.putExtra("androidx.browser.trusted.extra.SHARE_TARGET", shareTarget0.toBundle());
            intent0.putExtra("androidx.browser.trusted.extra.SHARE_DATA", this.mShareData.toBundle());
            if(this.mShareData.uris != null) {
                list0 = this.mShareData.uris;
            }
        }
        intent0.putExtra("androidx.browser.trusted.extra.DISPLAY_MODE", this.mDisplayMode.toBundle());
        intent0.putExtra("androidx.browser.trusted.extra.SCREEN_ORIENTATION", this.mScreenOrientation);
        return new TrustedWebActivityIntent(intent0, list0);
    }

    public CustomTabsIntent buildCustomTabsIntent() {
        return this.mIntentBuilder.build();
    }

    public TrustedWebActivityDisplayMode getDisplayMode() {
        return this.mDisplayMode;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public TrustedWebActivityIntentBuilder setAdditionalTrustedOrigins(List list0) {
        this.mAdditionalTrustedOrigins = list0;
        return this;
    }

    public TrustedWebActivityIntentBuilder setColorScheme(int v) {
        this.mIntentBuilder.setColorScheme(v);
        return this;
    }

    public TrustedWebActivityIntentBuilder setColorSchemeParams(int v, CustomTabColorSchemeParams customTabColorSchemeParams0) {
        this.mIntentBuilder.setColorSchemeParams(v, customTabColorSchemeParams0);
        return this;
    }

    public TrustedWebActivityIntentBuilder setDefaultColorSchemeParams(CustomTabColorSchemeParams customTabColorSchemeParams0) {
        this.mIntentBuilder.setDefaultColorSchemeParams(customTabColorSchemeParams0);
        return this;
    }

    public TrustedWebActivityIntentBuilder setDisplayMode(TrustedWebActivityDisplayMode trustedWebActivityDisplayMode0) {
        this.mDisplayMode = trustedWebActivityDisplayMode0;
        return this;
    }

    public TrustedWebActivityIntentBuilder setNavigationBarColor(int v) {
        this.mIntentBuilder.setNavigationBarColor(v);
        return this;
    }

    public TrustedWebActivityIntentBuilder setNavigationBarDividerColor(int v) {
        this.mIntentBuilder.setNavigationBarDividerColor(v);
        return this;
    }

    public TrustedWebActivityIntentBuilder setScreenOrientation(int v) {
        this.mScreenOrientation = v;
        return this;
    }

    public TrustedWebActivityIntentBuilder setShareParams(ShareTarget shareTarget0, ShareData shareData0) {
        this.mShareTarget = shareTarget0;
        this.mShareData = shareData0;
        return this;
    }

    public TrustedWebActivityIntentBuilder setSplashScreenParams(Bundle bundle0) {
        this.mSplashScreenParams = bundle0;
        return this;
    }

    public TrustedWebActivityIntentBuilder setToolbarColor(int v) {
        this.mIntentBuilder.setToolbarColor(v);
        return this;
    }
}

