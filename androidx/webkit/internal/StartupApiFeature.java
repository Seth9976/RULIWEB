package androidx.webkit.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.ComponentInfoFlags;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import androidx.webkit.WebViewCompat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class StartupApiFeature {
    public static class NoFramework extends StartupApiFeature {
        NoFramework(String s, String s1) {
            super(s, s1);
        }

        @Override  // androidx.webkit.internal.StartupApiFeature
        public final boolean isSupportedByFramework() {
            return false;
        }
    }

    public static class P extends StartupApiFeature {
        P(String s, String s1) {
            super(s, s1);
        }

        @Override  // androidx.webkit.internal.StartupApiFeature
        public final boolean isSupportedByFramework() [...] // 潜在的解密器
    }

    static final boolean $assertionsDisabled = false;
    public static final String METADATA_HOLDER_SERVICE_NAME = "org.chromium.android_webview.services.StartupFeatureMetadataHolder";
    private final String mInternalFeatureValue;
    private final String mPublicFeatureValue;
    private static final Set sValues;

    static {
        StartupApiFeature.sValues = new HashSet();
    }

    StartupApiFeature(String s, String s1) {
        this.mPublicFeatureValue = s;
        this.mInternalFeatureValue = s1;
        StartupApiFeature.sValues.add(this);
    }

    private static Bundle getMetaDataFromWebViewManifestOrNull(Context context0) {
        PackageInfo packageInfo0 = WebViewCompat.getCurrentWebViewPackage(context0);
        if(packageInfo0 == null) {
            return null;
        }
        ComponentName componentName0 = new ComponentName(packageInfo0.packageName, "org.chromium.android_webview.services.StartupFeatureMetadataHolder");
        if(Build.VERSION.SDK_INT >= 33) {
            PackageManager.ComponentInfoFlags packageManager$ComponentInfoFlags0 = ApiHelperForTiramisu.of(640L);
            try {
                return ApiHelperForTiramisu.getServiceInfo(context0.getPackageManager(), componentName0, packageManager$ComponentInfoFlags0).metaData;
            }
            catch(PackageManager.NameNotFoundException unused_ex) {
                return null;
            }
        }
        try {
            return StartupApiFeature.getServiceInfo(context0, componentName0, (Build.VERSION.SDK_INT < 24 ? 0x80 : 640)).metaData;
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return null;
        }
    }

    public String getPublicFeatureName() {
        return this.mPublicFeatureValue;
    }

    private static ServiceInfo getServiceInfo(Context context0, ComponentName componentName0, int v) throws PackageManager.NameNotFoundException {
        return context0.getPackageManager().getServiceInfo(componentName0, v);
    }

    // 去混淆评级： 低(20)
    public boolean isSupported(Context context0) {
        return this.isSupportedByFramework() || this.isSupportedByWebView(context0);
    }

    public abstract boolean isSupportedByFramework();

    public boolean isSupportedByWebView(Context context0) {
        Bundle bundle0 = StartupApiFeature.getMetaDataFromWebViewManifestOrNull(context0);
        return bundle0 == null ? false : bundle0.containsKey(this.mInternalFeatureValue);
    }

    public static Set values() {
        return Collections.unmodifiableSet(StartupApiFeature.sValues);
    }
}

