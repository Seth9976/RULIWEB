package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.BundleCompat;
import androidx.core.content.FileProvider;
import java.io.File;

public class TrustedWebUtils {
    public static final String ACTION_MANAGE_TRUSTED_WEB_ACTIVITY_DATA = "android.support.customtabs.action.ACTION_MANAGE_TRUSTED_WEB_ACTIVITY_DATA";
    public static final String EXTRA_LAUNCH_AS_TRUSTED_WEB_ACTIVITY = "android.support.customtabs.extra.LAUNCH_AS_TRUSTED_WEB_ACTIVITY";

    public static boolean areSplashScreensSupported(Context context0, String s, String s1) {
        Intent intent0 = new Intent().setAction("android.support.customtabs.action.CustomTabsService").setPackage(s);
        ResolveInfo resolveInfo0 = context0.getPackageManager().resolveService(intent0, 0x40);
        return resolveInfo0 == null || resolveInfo0.filter == null ? false : resolveInfo0.filter.hasCategory(s1);
    }

    @Deprecated
    public static void launchAsTrustedWebActivity(Context context0, CustomTabsIntent customTabsIntent0, Uri uri0) {
        if(BundleCompat.getBinder(customTabsIntent0.intent.getExtras(), "android.support.customtabs.extra.SESSION") == null) {
            throw new IllegalArgumentException("Given CustomTabsIntent should be associated with a valid CustomTabsSession");
        }
        customTabsIntent0.intent.putExtra("android.support.customtabs.extra.LAUNCH_AS_TRUSTED_WEB_ACTIVITY", true);
        customTabsIntent0.launchUrl(context0, uri0);
    }

    public static void launchBrowserSiteSettings(Context context0, CustomTabsSession customTabsSession0, Uri uri0) {
        Intent intent0 = new Intent("android.support.customtabs.action.ACTION_MANAGE_TRUSTED_WEB_ACTIVITY_DATA");
        intent0.setPackage(customTabsSession0.getComponentName().getPackageName());
        intent0.setData(uri0);
        Bundle bundle0 = new Bundle();
        BundleCompat.putBinder(bundle0, "android.support.customtabs.extra.SESSION", customTabsSession0.getBinder());
        intent0.putExtras(bundle0);
        PendingIntent pendingIntent0 = customTabsSession0.getId();
        if(pendingIntent0 != null) {
            intent0.putExtra("android.support.customtabs.extra.SESSION_ID", pendingIntent0);
        }
        context0.startActivity(intent0);
    }

    public static boolean transferSplashImage(Context context0, File file0, String s, String s1, CustomTabsSession customTabsSession0) {
        Uri uri0 = FileProvider.getUriForFile(context0, s, file0);
        context0.grantUriPermission(s1, uri0, 1);
        return customTabsSession0.receiveFile(uri0, 1, null);
    }
}

