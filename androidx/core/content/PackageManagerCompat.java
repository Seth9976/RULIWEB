package androidx.core.content;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.core.os.UserManagerCompat;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executors;

public final class PackageManagerCompat {
    static class Api30Impl {
        static boolean areUnusedAppRestrictionsEnabled(Context context0) {
            return !context0.getPackageManager().isAutoRevokeWhitelisted();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface UnusedAppRestrictionsStatus {
    }

    public static final String ACTION_PERMISSION_REVOCATION_SETTINGS = "android.intent.action.AUTO_REVOKE_PERMISSIONS";
    public static final String LOG_TAG = "PackageManagerCompat";

    // 去混淆评级： 低(40)
    public static boolean areUnusedAppRestrictionsAvailable(PackageManager packageManager0) {
        return Build.VERSION.SDK_INT >= 30 || Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT < 30 && PackageManagerCompat.getPermissionRevocationVerifierApp(packageManager0) != null;
    }

    public static String getPermissionRevocationVerifierApp(PackageManager packageManager0) {
        String s = null;
        for(Object object0: packageManager0.queryIntentActivities(new Intent("android.intent.action.AUTO_REVOKE_PERMISSIONS").setData(Uri.fromParts("package", "com.example", null)), 0)) {
            String s1 = ((ResolveInfo)object0).activityInfo.packageName;
            if(packageManager0.checkPermission("android.permission.PACKAGE_VERIFICATION_AGENT", s1) == 0) {
                if(s != null) {
                    break;
                }
                s = s1;
            }
        }
        return s;
    }

    public static ListenableFuture getUnusedAppRestrictionsStatus(Context context0) {
        int v = 4;
        ListenableFuture listenableFuture0 = ResolvableFuture.create();
        if(!UserManagerCompat.isUserUnlocked(context0)) {
            ((ResolvableFuture)listenableFuture0).set(0);
            Log.e("PackageManagerCompat", "User is in locked direct boot mode");
            return listenableFuture0;
        }
        if(!PackageManagerCompat.areUnusedAppRestrictionsAvailable(context0.getPackageManager())) {
            ((ResolvableFuture)listenableFuture0).set(1);
            return listenableFuture0;
        }
        if(Build.VERSION.SDK_INT >= 0x1F) {
            if(Api30Impl.areUnusedAppRestrictionsEnabled(context0)) {
                ((ResolvableFuture)listenableFuture0).set(5);
                return listenableFuture0;
            }
            ((ResolvableFuture)listenableFuture0).set(2);
            return listenableFuture0;
        }
        if(Build.VERSION.SDK_INT == 30) {
            if(!Api30Impl.areUnusedAppRestrictionsEnabled(context0)) {
                v = 2;
            }
            ((ResolvableFuture)listenableFuture0).set(v);
            return listenableFuture0;
        }
        UnusedAppRestrictionsBackportServiceConnection unusedAppRestrictionsBackportServiceConnection0 = new UnusedAppRestrictionsBackportServiceConnection(context0);
        ((ResolvableFuture)listenableFuture0).addListener(new PackageManagerCompat..ExternalSyntheticLambda0(unusedAppRestrictionsBackportServiceConnection0), Executors.newSingleThreadExecutor());
        unusedAppRestrictionsBackportServiceConnection0.connectAndFetchResult(((ResolvableFuture)listenableFuture0));
        return listenableFuture0;
    }
}

