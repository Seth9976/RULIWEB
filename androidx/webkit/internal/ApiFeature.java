package androidx.webkit.internal;

import android.os.Build.VERSION;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public abstract class ApiFeature implements ConditionallySupportedFeature {
    static class LAZY_HOLDER {
        static final Set WEBVIEW_APK_FEATURES;

        static {
            LAZY_HOLDER.WEBVIEW_APK_FEATURES = new HashSet(Arrays.asList(WebViewGlueCommunicator.getFactory().getWebViewFeatures()));
        }
    }

    public static class M extends ApiFeature {
        M(String s, String s1) {
            super(s, s1);
        }

        @Override  // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() [...] // 潜在的解密器
    }

    public static class N extends ApiFeature {
        N(String s, String s1) {
            super(s, s1);
        }

        @Override  // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() [...] // 潜在的解密器
    }

    public static class NoFramework extends ApiFeature {
        NoFramework(String s, String s1) {
            super(s, s1);
        }

        @Override  // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() {
            return false;
        }
    }

    public static class O extends ApiFeature {
        O(String s, String s1) {
            super(s, s1);
        }

        @Override  // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() [...] // 潜在的解密器
    }

    public static class O_MR1 extends ApiFeature {
        O_MR1(String s, String s1) {
            super(s, s1);
        }

        @Override  // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() [...] // 潜在的解密器
    }

    public static class P extends ApiFeature {
        P(String s, String s1) {
            super(s, s1);
        }

        @Override  // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() [...] // 潜在的解密器
    }

    public static class Q extends ApiFeature {
        Q(String s, String s1) {
            super(s, s1);
        }

        @Override  // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() [...] // 潜在的解密器
    }

    public static class T extends ApiFeature {
        T(String s, String s1) {
            super(s, s1);
        }

        @Override  // androidx.webkit.internal.ApiFeature
        public final boolean isSupportedByFramework() {
            return Build.VERSION.SDK_INT >= 33;
        }
    }

    private final String mInternalFeatureValue;
    private final String mPublicFeatureValue;
    private static final Set sValues;

    static {
        ApiFeature.sValues = new HashSet();
    }

    ApiFeature(String s, String s1) {
        this.mPublicFeatureValue = s;
        this.mInternalFeatureValue = s1;
        ApiFeature.sValues.add(this);
    }

    @Override  // androidx.webkit.internal.ConditionallySupportedFeature
    public String getPublicFeatureName() {
        return this.mPublicFeatureValue;
    }

    public static Set getWebViewApkFeaturesForTesting() {
        return LAZY_HOLDER.WEBVIEW_APK_FEATURES;
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.webkit.internal.ConditionallySupportedFeature
    public boolean isSupported() {
        return this.isSupportedByFramework() || this.isSupportedByWebView();
    }

    public abstract boolean isSupportedByFramework();

    public boolean isSupportedByWebView() {
        return BoundaryInterfaceReflectionUtil.containsFeature(LAZY_HOLDER.WEBVIEW_APK_FEATURES, this.mInternalFeatureValue);
    }

    public static Set values() {
        return Collections.unmodifiableSet(ApiFeature.sValues);
    }
}

