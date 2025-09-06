package com.google.android.gms.common.wrappers;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.os.Process;
import androidx.core.util.Pair;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

public class PackageManagerWrapper {
    protected final Context zza;

    public PackageManagerWrapper(Context context0) {
        this.zza = context0;
    }

    public int checkCallingOrSelfPermission(String s) {
        return this.zza.checkCallingOrSelfPermission(s);
    }

    @ResultIgnorabilityUnspecified
    public int checkPermission(String s, String s1) {
        return this.zza.getPackageManager().checkPermission(s, s1);
    }

    @ResultIgnorabilityUnspecified
    public ApplicationInfo getApplicationInfo(String s, int v) throws PackageManager.NameNotFoundException {
        return this.zza.getPackageManager().getApplicationInfo(s, v);
    }

    public CharSequence getApplicationLabel(String s) throws PackageManager.NameNotFoundException {
        return this.zza.getPackageManager().getApplicationLabel(this.zza.getPackageManager().getApplicationInfo(s, 0));
    }

    @ResultIgnorabilityUnspecified
    public Pair getApplicationLabelAndIcon(String s) throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo0 = this.zza.getPackageManager().getApplicationInfo(s, 0);
        return Pair.create(this.zza.getPackageManager().getApplicationLabel(applicationInfo0), this.zza.getPackageManager().getApplicationIcon(applicationInfo0));
    }

    @ResultIgnorabilityUnspecified
    public PackageInfo getPackageInfo(String s, int v) throws PackageManager.NameNotFoundException {
        return this.zza.getPackageManager().getPackageInfo(s, v);
    }

    public boolean isCallerInstantApp() {
        if(Binder.getCallingUid() == Process.myUid()) {
            return InstantApps.isInstantApp(this.zza);
        }
        String s = this.zza.getPackageManager().getNameForUid(Binder.getCallingUid());
        return s == null ? false : this.zza.getPackageManager().isInstantApp(s);
    }

    public final boolean zza(int v, String s) {
        try {
            AppOpsManager appOpsManager0 = (AppOpsManager)this.zza.getSystemService("appops");
            if(appOpsManager0 == null) {
                throw new NullPointerException("context.getSystemService(Context.APP_OPS_SERVICE) is null");
            }
            appOpsManager0.checkPackage(v, s);
            return true;
        }
        catch(SecurityException unused_ex) {
            return false;
        }
    }
}

