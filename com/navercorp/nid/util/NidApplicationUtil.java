package com.navercorp.nid.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build.VERSION;
import com.navercorp.nid.log.NidLog;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0004H\u0002J\u000E\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0007\u001A\u00020\bJ\u0016\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u000E0\r2\u0006\u0010\u0007\u001A\u00020\bH\u0002J\u000E\u0010\u000F\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bJ\u000E\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0007\u001A\u00020\bJ\u0016\u0010\u0012\u001A\u00020\u00112\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0004J\u000E\u0010\u0013\u001A\u00020\u00112\u0006\u0010\u0007\u001A\u00020\bJ\u001E\u0010\u0014\u001A\u00020\u00112\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00042\u0006\u0010\u0015\u001A\u00020\u0004J\u000E\u0010\u0016\u001A\u00020\u00112\u0006\u0010\u0007\u001A\u00020\bJ\u000E\u0010\u0017\u001A\u00020\u00112\u0006\u0010\u0007\u001A\u00020\bJ\u001E\u0010\u0018\u001A\u00020\u00112\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00042\u0006\u0010\u0015\u001A\u00020\u0004R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/navercorp/nid/util/NidApplicationUtil;", "", "()V", "TAG", "", "getApplicationVersion", "", "context", "Landroid/content/Context;", "packageName", "getConnectivityManager", "Landroid/net/ConnectivityManager;", "getCustomTabsPackageList", "", "Landroid/content/pm/PackageInfo;", "getNaverAppVersion", "isCustomTabsAvailable", "", "isExistApplication", "isExistChromeApp", "isExistIntentFilter", "intentName", "isExistNaverApp", "isNotCustomTabsAvailable", "isNotExistIntentFilter", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidApplicationUtil {
    public static final NidApplicationUtil INSTANCE = null;
    public static final String TAG = "NidApplicationUtil";

    static {
        NidApplicationUtil.INSTANCE = new NidApplicationUtil();
    }

    private final long getApplicationVersion(Context context0, String s) {
        PackageManager packageManager0 = context0.getPackageManager();
        try {
            PackageInfo packageInfo0 = packageManager0.getPackageInfo(s, 0);
            return Build.VERSION.SDK_INT < 28 ? ((long)packageInfo0.versionCode) : LinkFollowing..ExternalSyntheticApiModelOutline0.m(packageInfo0);
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            NidLog.d("NidApplicationUtil", packageManager$NameNotFoundException0);
            return -1L;
        }
    }

    public final ConnectivityManager getConnectivityManager(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Object object0 = context0.getSystemService("connectivity");
        if(object0 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        return (ConnectivityManager)object0;
    }

    private final List getCustomTabsPackageList(Context context0) {
        PackageManager packageManager0 = context0.getPackageManager();
        List list0 = packageManager0.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com")), 0);
        Intrinsics.checkNotNullExpressionValue(list0, "packageManager.queryIntentActivities(intent, 0)");
        List list1 = new ArrayList();
        for(Object object0: list0) {
            ResolveInfo resolveInfo0 = (ResolveInfo)object0;
            Intent intent0 = new Intent();
            intent0.setAction("android.support.customtabs.action.CustomTabsService");
            intent0.setPackage(resolveInfo0.activityInfo.packageName);
            NidLog.d("NidApplicationUtil", "getCustomTabsPackageList : " + resolveInfo0.activityInfo.packageName);
            if(packageManager0.resolveService(intent0, 0) != null) {
                try {
                    ApplicationInfo applicationInfo0 = packageManager0.getApplicationInfo(resolveInfo0.activityInfo.packageName, 0);
                    Intrinsics.checkNotNullExpressionValue(applicationInfo0, "packageManager.getApplic…ivityInfo.packageName, 0)");
                    if(!applicationInfo0.enabled) {
                        continue;
                    }
                    PackageInfo packageInfo0 = packageManager0.getPackageInfo(resolveInfo0.activityInfo.packageName, 0);
                    Intrinsics.checkNotNullExpressionValue(packageInfo0, "packageInfo");
                    list1.add(packageInfo0);
                }
                catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                    NidLog.d("NidApplicationUtil", packageManager$NameNotFoundException0);
                }
            }
        }
        return list1;
    }

    public final long getNaverAppVersion(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        return this.getApplicationVersion(context0, "com.nhn.android.search");
    }

    public final boolean isCustomTabsAvailable(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        return !this.getCustomTabsPackageList(context0).isEmpty();
    }

    public final boolean isExistApplication(Context context0, String s) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "packageName");
        return context0.getPackageManager().getLaunchIntentForPackage(s) != null;
    }

    public final boolean isExistChromeApp(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        return this.isExistApplication(context0, "com.android.chrome");
    }

    public final boolean isExistIntentFilter(Context context0, String s, String s1) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "packageName");
        Intrinsics.checkNotNullParameter(s1, "intentName");
        List list0 = context0.getPackageManager().queryIntentActivities(new Intent(s1), 0x80);
        Intrinsics.checkNotNullExpressionValue(list0, "context.packageManager.q…r.GET_META_DATA\n        )");
        for(Object object0: list0) {
            NidLog.d("NidApplicationUtil", "intent filter name : " + s1);
            NidLog.d("NidApplicationUtil", "resolveInfo.activityInfo.packageName : " + ((ResolveInfo)object0).activityInfo.packageName);
            if(StringsKt.equals(((ResolveInfo)object0).activityInfo.packageName, s, true)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public final boolean isExistNaverApp(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        return this.isExistApplication(context0, "com.nhn.android.search");
    }

    public final boolean isNotCustomTabsAvailable(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        return !this.isCustomTabsAvailable(context0);
    }

    public final boolean isNotExistIntentFilter(Context context0, String s, String s1) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "packageName");
        Intrinsics.checkNotNullParameter(s1, "intentName");
        return !this.isExistIntentFilter(context0, s, s1);
    }
}

