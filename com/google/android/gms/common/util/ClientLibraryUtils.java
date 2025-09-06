package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.google.android.gms.common.wrappers.Wrappers;

public class ClientLibraryUtils {
    public static int getClientVersion(Context context0, String s) {
        PackageInfo packageInfo0 = ClientLibraryUtils.getPackageInfo(context0, s);
        if(packageInfo0 != null && packageInfo0.applicationInfo != null) {
            Bundle bundle0 = packageInfo0.applicationInfo.metaData;
            return bundle0 == null ? -1 : bundle0.getInt("com.google.android.gms.version", -1);
        }
        return -1;
    }

    public static PackageInfo getPackageInfo(Context context0, String s) {
        try {
            return Wrappers.packageManager(context0).getPackageInfo(s, 0x80);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return null;
        }
    }

    public static boolean isPackageSide() [...] // Inlined contents
}

