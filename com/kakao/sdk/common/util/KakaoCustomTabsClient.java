package com.kakao.sdk.common.util;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent.Builder;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0005H\u0002J\u0016\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000FJ\u0018\u0010\u0010\u001A\u0004\u0018\u00010\u00112\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000FJ\u001A\u0010\u0012\u001A\u0004\u0018\u00010\u00052\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000FH\u0002R\u0016\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/kakao/sdk/common/util/KakaoCustomTabsClient;", "", "()V", "chromePackageNames", "", "", "[Ljava/lang/String;", "isPackageNameChrome", "", "packageName", "open", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "openWithDefault", "Landroid/content/ServiceConnection;", "resolveCustomTabsPackage", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class KakaoCustomTabsClient {
    public static final KakaoCustomTabsClient INSTANCE;
    private static final String[] chromePackageNames;

    static {
        KakaoCustomTabsClient.INSTANCE = new KakaoCustomTabsClient();
        KakaoCustomTabsClient.chromePackageNames = new String[]{"com.android.chrome", "com.chrome.beta", "com.chrome.dev"};
    }

    private final boolean isPackageNameChrome(String s) {
        return ArraysKt.contains(KakaoCustomTabsClient.chromePackageNames, s);
    }

    public final void open(Context context0, Uri uri0) throws ActivityNotFoundException {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(uri0, "uri");
        new Builder().setUrlBarHidingEnabled(true).setShowTitle(true).build().launchUrl(context0, uri0);
    }

    public final ServiceConnection openWithDefault(Context context0, Uri uri0) throws UnsupportedOperationException {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(uri0, "uri");
        String s = this.resolveCustomTabsPackage(context0, uri0);
        if(s == null) {
            throw new UnsupportedOperationException();
        }
        SdkLog.Companion.d("Choosing " + s + " as custom tabs browser");
        com.kakao.sdk.common.util.KakaoCustomTabsClient.openWithDefault.connection.1 kakaoCustomTabsClient$openWithDefault$connection$10 = new CustomTabsServiceConnection() {
            @Override  // androidx.browser.customtabs.CustomTabsServiceConnection
            public void onCustomTabsServiceConnected(ComponentName componentName0, CustomTabsClient customTabsClient0) {
                Intrinsics.checkNotNullParameter(componentName0, "name");
                Intrinsics.checkNotNullParameter(customTabsClient0, "client");
                Builder customTabsIntent$Builder0 = new Builder().setUrlBarHidingEnabled(true).setShowTitle(true);
                Intrinsics.checkNotNullExpressionValue(customTabsIntent$Builder0, "Builder().setUrlBarHidingEnabled(true).setShowTitle(true)");
                CustomTabsIntent customTabsIntent0 = customTabsIntent$Builder0.build();
                Intrinsics.checkNotNullExpressionValue(customTabsIntent0, "builder.build()");
                customTabsIntent0.intent.setData(s);
                customTabsIntent0.intent.setPackage(context0);
                this.$context.startActivity(customTabsIntent0.intent);
            }

            @Override  // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName0) {
                SdkLog.Companion.d("onServiceDisconnected: " + componentName0);
            }
        };
        return CustomTabsClient.bindCustomTabsService(context0, s, kakaoCustomTabsClient$openWithDefault$connection$10) ? kakaoCustomTabsClient$openWithDefault$connection$10 : null;
    }

    private final String resolveCustomTabsPackage(Context context0, Uri uri0) {
        String s3;
        Intent intent0 = new Intent("android.intent.action.VIEW", uri0);
        ResolveInfo resolveInfo0 = Build.VERSION.SDK_INT < 33 ? context0.getPackageManager().resolveActivity(intent0, 0x10000) : LinkFollowing..ExternalSyntheticApiModelOutline0.m(context0.getPackageManager(), intent0, LinkFollowing..ExternalSyntheticApiModelOutline0.m(0x10000L));
        Intent intent1 = new Intent().setAction("android.support.customtabs.action.CustomTabsService");
        Intrinsics.checkNotNullExpressionValue(intent1, "Intent().setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION)");
        List list0 = Build.VERSION.SDK_INT < 33 ? context0.getPackageManager().queryIntentServices(intent1, 0) : LinkFollowing..ExternalSyntheticApiModelOutline0.m(context0.getPackageManager(), intent1, LinkFollowing..ExternalSyntheticApiModelOutline0.m(0L));
        Intrinsics.checkNotNullExpressionValue(list0, "if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {\n            context.packageManager.queryIntentServices(\n                serviceIntent,\n                PackageManager.ResolveInfoFlags.of(0)\n            )\n        } else {\n            @Suppress(\"DEPRECATION\")\n            context.packageManager.queryIntentServices(serviceIntent, 0)\n        }");
        String s = null;
        for(Object object0: list0) {
            ResolveInfo resolveInfo1 = (ResolveInfo)object0;
            if(s == null) {
                String s1 = resolveInfo1.serviceInfo.packageName;
                Intrinsics.checkNotNullExpressionValue(s1, "info.serviceInfo.packageName");
                if(this.isPackageNameChrome(s1)) {
                    s = resolveInfo1.serviceInfo.packageName;
                }
            }
            String s2 = resolveInfo1.serviceInfo.packageName;
            if(resolveInfo0 == null) {
                s3 = null;
            }
            else {
                ActivityInfo activityInfo0 = resolveInfo0.activityInfo;
                if(activityInfo0 != null) {
                    s3 = activityInfo0.packageName;
                }
            }
            if(Intrinsics.areEqual(s2, s3)) {
                if(resolveInfo0 == null) {
                    break;
                }
                ActivityInfo activityInfo1 = resolveInfo0.activityInfo;
                if(activityInfo1 == null) {
                    break;
                }
                return activityInfo1.packageName != null || s == null ? activityInfo1.packageName : s;
            }
            if(false) {
                break;
            }
        }
        return s == null ? null : s;
    }
}

