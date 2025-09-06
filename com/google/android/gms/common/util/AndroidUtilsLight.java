package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.wrappers.Wrappers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AndroidUtilsLight {
    private static volatile int zza = -1;

    static {
    }

    @Deprecated
    public static byte[] getPackageCertificateHashBytes(Context context0, String s) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo0 = Wrappers.packageManager(context0).getPackageInfo(s, 0x40);
        if(packageInfo0.signatures != null && packageInfo0.signatures.length == 1) {
            MessageDigest messageDigest0 = AndroidUtilsLight.zza("SHA1");
            return messageDigest0 == null ? null : messageDigest0.digest(packageInfo0.signatures[0].toByteArray());
        }
        return null;
    }

    public static MessageDigest zza(String s) {
        for(int v = 0; v < 2; ++v) {
            try {
                MessageDigest messageDigest0 = MessageDigest.getInstance(s);
                if(messageDigest0 != null) {
                    return messageDigest0;
                }
            }
            catch(NoSuchAlgorithmException unused_ex) {
            }
        }
        return null;
    }
}

