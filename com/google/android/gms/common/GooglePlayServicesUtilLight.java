package com.google.android.gms.common;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller.SessionInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzah;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.zza;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GooglePlayServicesUtilLight {
    static final int GMS_AVAILABILITY_NOTIFICATION_ID = 10436;
    static final int GMS_GENERAL_ERROR_NOTIFICATION_ID = 0x9B6D;
    public static final String GOOGLE_PLAY_GAMES_PACKAGE = "com.google.android.play.games";
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 12451000;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    @Deprecated
    static final AtomicBoolean sCanceledAvailabilityNotification = null;
    static boolean zza = false;
    private static boolean zzb = false;
    private static final AtomicBoolean zzc;

    static {
        GooglePlayServicesUtilLight.sCanceledAvailabilityNotification = new AtomicBoolean();
        GooglePlayServicesUtilLight.zzc = new AtomicBoolean();
    }

    @Deprecated
    public static void cancelAvailabilityErrorNotifications(Context context0) {
        if(GooglePlayServicesUtilLight.sCanceledAvailabilityNotification.getAndSet(true)) {
            return;
        }
        else {
            try {
                NotificationManager notificationManager0 = (NotificationManager)context0.getSystemService("notification");
                if(notificationManager0 != null) {
                    notificationManager0.cancel(10436);
                }
                return;
            }
            catch(SecurityException securityException0) {
            }
        }
        Log.d("GooglePlayServicesUtil", "Suppressing Security Exception %s in cancelAvailabilityErrorNotifications.", securityException0);
    }

    public static void enableUsingApkIndependentContext() {
        GooglePlayServicesUtilLight.zzc.set(true);
    }

    @Deprecated
    public static void ensurePlayServicesAvailable(Context context0, int v) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        int v1 = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context0, v);
        if(v1 != 0) {
            Intent intent0 = GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(context0, v1, "e");
            Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + v1);
            if(intent0 != null) {
                throw new GooglePlayServicesRepairableException(v1, "Google Play Services not available", intent0);
            }
            throw new GooglePlayServicesNotAvailableException(v1);
        }
        return;
    }

    @Deprecated
    public static int getApkVersion(Context context0) {
        try {
            return context0.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }

    @Deprecated
    public static int getClientVersion(Context context0) {
        Preconditions.checkState(true);
        return ClientLibraryUtils.getClientVersion(context0, "com.ruliweb.www.ruliapp");
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int v, Context context0, int v1) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionPendingIntent(context0, v, v1);
    }

    @Deprecated
    public static String getErrorString(int v) {
        return ConnectionResult.zza(v);
    }

    @Deprecated
    public static Intent getGooglePlayServicesAvailabilityRecoveryIntent(int v) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(null, v, null);
    }

    public static Context getRemoteContext(Context context0) {
        try {
            return context0.createPackageContext("com.google.android.gms", 3);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return null;
        }
    }

    public static Resources getRemoteResource(Context context0) {
        try {
            return context0.getPackageManager().getResourcesForApplication("com.google.android.gms");
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return null;
        }
    }

    public static boolean honorsDebugCertificates(Context context0) {
        if(!GooglePlayServicesUtilLight.zza) {
            try {
                PackageInfo packageInfo0 = Wrappers.packageManager(context0).getPackageInfo("com.google.android.gms", 0x40);
                GoogleSignatureVerifier.getInstance(context0);
                GooglePlayServicesUtilLight.zzb = packageInfo0 == null || GoogleSignatureVerifier.zzb(packageInfo0, false) || !GoogleSignatureVerifier.zzb(packageInfo0, true) ? false : true;
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", packageManager$NameNotFoundException0);
            }
            finally {
                GooglePlayServicesUtilLight.zza = true;
            }
        }
        return GooglePlayServicesUtilLight.zzb || !DeviceProperties.isUserBuild();
    }

    @ResultIgnorabilityUnspecified
    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context0) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context0, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context0, int v) {
        PackageInfo packageInfo1;
        PackageInfo packageInfo0;
        try {
            context0.getResources().getString(string.common_google_play_services_unknown_issue);
        }
        catch(Throwable unused_ex) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if(!GooglePlayServicesUtilLight.zzc.get()) {
            int v1 = zzah.zza(context0);
            if(v1 == 0) {
                throw new GooglePlayServicesMissingManifestValueException();
            }
            if(v1 != GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                throw new GooglePlayServicesIncorrectManifestValueException(v1);
            }
        }
        boolean z = !DeviceProperties.isWearableWithoutPlayStore(context0) && !DeviceProperties.zzb(context0);
        Preconditions.checkArgument(v >= 0);
        PackageManager packageManager0 = context0.getPackageManager();
        if(z) {
            try {
                packageInfo0 = packageManager0.getPackageInfo("com.android.vending", 0x2040);
            }
            catch(PackageManager.NameNotFoundException unused_ex) {
                Log.w("GooglePlayServicesUtil", "com.ruliweb.www.ruliapp requires the Google Play Store, but it is missing.");
                return 9;
            }
        }
        else {
            packageInfo0 = null;
        }
        try {
            packageInfo1 = packageManager0.getPackageInfo("com.google.android.gms", 0x40);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            Log.w("GooglePlayServicesUtil", "com.ruliweb.www.ruliapp requires Google Play services, but they are missing.");
            return 1;
        }
        GoogleSignatureVerifier.getInstance(context0);
        if(!GoogleSignatureVerifier.zzb(packageInfo1, true)) {
            Log.w("GooglePlayServicesUtil", "com.ruliweb.www.ruliapp requires Google Play services, but their signature is invalid.");
            return 9;
        }
        if(z) {
            Preconditions.checkNotNull(packageInfo0);
            if(!GoogleSignatureVerifier.zzb(packageInfo0, true)) {
                Log.w("GooglePlayServicesUtil", "com.ruliweb.www.ruliapp requires Google Play Store, but its signature is invalid.");
                return 9;
            }
        }
        if(z && packageInfo0 != null && !packageInfo0.signatures[0].equals(packageInfo1.signatures[0])) {
            Log.w("GooglePlayServicesUtil", "com.ruliweb.www.ruliapp requires Google Play Store, but its signature doesn\'t match that of Google Play services.");
            return 9;
        }
        if(zza.zza(packageInfo1.versionCode) < zza.zza(v)) {
            Log.w("GooglePlayServicesUtil", "Google Play services out of date for com.ruliweb.www.ruliapp.  Requires " + v + " but found " + packageInfo1.versionCode);
            return 2;
        }
        ApplicationInfo applicationInfo0 = packageInfo1.applicationInfo;
        if(applicationInfo0 == null) {
            try {
                return packageManager0.getApplicationInfo("com.google.android.gms", 0).enabled ? 0 : 3;
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.wtf("GooglePlayServicesUtil", "com.ruliweb.www.ruliapp requires Google Play services, but they\'re missing when getting application info.", packageManager$NameNotFoundException0);
                return 1;
            }
        }
        return applicationInfo0.enabled ? 0 : 3;
    }

    @Deprecated
    public static boolean isGooglePlayServicesUid(Context context0, int v) {
        return UidVerifier.isGooglePlayServicesUid(context0, v);
    }

    @Deprecated
    public static boolean isPlayServicesPossiblyUpdating(Context context0, int v) {
        switch(v) {
            case 1: {
                return GooglePlayServicesUtilLight.zza(context0, "com.google.android.gms");
            }
            case 18: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    @Deprecated
    public static boolean isPlayStorePossiblyUpdating(Context context0, int v) {
        return v == 9 ? GooglePlayServicesUtilLight.zza(context0, "com.android.vending") : false;
    }

    public static boolean isRestrictedUserProfile(Context context0) {
        Object object0 = context0.getSystemService("user");
        Preconditions.checkNotNull(object0);
        Bundle bundle0 = ((UserManager)object0).getApplicationRestrictions("com.ruliweb.www.ruliapp");
        return bundle0 != null && "true".equals(bundle0.getString("restricted_profile"));
    }

    @Deprecated
    public static boolean isSidewinderDevice(Context context0) {
        return DeviceProperties.isSidewinder(context0);
    }

    @Deprecated
    public static boolean isUserRecoverableError(int v) {
        return v == 1 || v == 2 || v == 3 || v == 9;
    }

    @Deprecated
    public static boolean uidHasPackageName(Context context0, int v, String s) {
        return UidVerifier.uidHasPackageName(context0, v, s);
    }

    static boolean zza(Context context0, String s) {
        boolean z = s.equals("com.google.android.gms");
        try {
            List list0 = context0.getPackageManager().getPackageInstaller().getAllSessions();
        }
        catch(Exception unused_ex) {
            return false;
        }
        for(Object object0: list0) {
            if(s.equals(((PackageInstaller.SessionInfo)object0).getAppPackageName())) {
                return true;
            }
            if(false) {
                break;
            }
        }
        PackageManager packageManager0 = context0.getPackageManager();
        try {
            ApplicationInfo applicationInfo0 = packageManager0.getApplicationInfo(s, 0x2000);
            if(z) {
                return applicationInfo0.enabled;
            }
            if(applicationInfo0.enabled && !GooglePlayServicesUtilLight.isRestrictedUserProfile(context0)) {
                return true;
            }
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
        }
        return false;
    }
}

