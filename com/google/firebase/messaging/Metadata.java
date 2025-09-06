package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import java.util.List;

class Metadata {
    private static final String ACTION_C2DM_REGISTER = "com.google.android.c2dm.intent.REGISTER";
    private static final String ACTION_IID_TOKEN_REQUEST = "com.google.iid.TOKEN_REQUEST";
    static final int GMSCORE_NOT_FOUND = 0;
    private static final String GMSCORE_SEND_PERMISSION = "com.google.android.c2dm.permission.SEND";
    static final String GMS_PACKAGE = "com.google.android.gms";
    static final int IID_VIA_RECEIVER = 2;
    static final int IID_VIA_SERVICE = 1;
    private String appVersionCode;
    private String appVersionName;
    private final Context context;
    private int gmsVersionCode;
    private int iidImplementation;

    Metadata(Context context0) {
        this.iidImplementation = 0;
        this.context = context0;
    }

    String getAppVersionCode() {
        synchronized(this) {
            if(this.appVersionCode == null) {
                this.populateAppVersionInfo();
            }
            return this.appVersionCode;
        }
    }

    String getAppVersionName() {
        synchronized(this) {
            if(this.appVersionName == null) {
                this.populateAppVersionInfo();
            }
            return this.appVersionName;
        }
    }

    static String getDefaultSenderId(FirebaseApp firebaseApp0) {
        String s = firebaseApp0.getOptions().getGcmSenderId();
        if(s != null) {
            return s;
        }
        String s1 = firebaseApp0.getOptions().getApplicationId();
        if(!s1.startsWith("1:")) {
            return s1;
        }
        String[] arr_s = s1.split(":");
        if(arr_s.length < 2) {
            return null;
        }
        String s2 = arr_s[1];
        return s2.isEmpty() ? null : s2;
    }

    int getGmsVersionCode() {
        synchronized(this) {
            if(this.gmsVersionCode == 0) {
                PackageInfo packageInfo0 = this.getPackageInfo("com.google.android.gms");
                if(packageInfo0 != null) {
                    this.gmsVersionCode = packageInfo0.versionCode;
                }
            }
            return this.gmsVersionCode;
        }
    }

    int getIidImplementation() {
        synchronized(this) {
            int v1 = this.iidImplementation;
            if(v1 != 0) {
                return v1;
            }
            PackageManager packageManager0 = this.context.getPackageManager();
            if(packageManager0.checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") == -1) {
                Log.e("FirebaseMessaging", "Google Play services missing or without correct permission.");
                return 0;
            }
            Intent intent0 = new Intent("com.google.iid.TOKEN_REQUEST");
            intent0.setPackage("com.google.android.gms");
            List list0 = packageManager0.queryBroadcastReceivers(intent0, 0);
            if(list0 != null && list0.size() > 0) {
                this.iidImplementation = 2;
                return 2;
            }
            Log.w("FirebaseMessaging", "Failed to resolve IID implementation package, falling back");
            this.iidImplementation = 2;
            return this.iidImplementation;
        }
    }

    private PackageInfo getPackageInfo(String s) {
        try {
            return this.context.getPackageManager().getPackageInfo(s, 0);
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            Log.w("FirebaseMessaging", "Failed to find package " + packageManager$NameNotFoundException0);
            return null;
        }
    }

    boolean isGmscorePresent() {
        return this.getIidImplementation() != 0;
    }

    private void populateAppVersionInfo() {
        synchronized(this) {
            PackageInfo packageInfo0 = this.getPackageInfo("com.ruliweb.www.ruliapp");
            if(packageInfo0 != null) {
                this.appVersionCode = Integer.toString(packageInfo0.versionCode);
                this.appVersionName = packageInfo0.versionName;
            }
        }
    }
}

