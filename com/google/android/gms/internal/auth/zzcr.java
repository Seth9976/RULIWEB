package com.google.android.gms.internal.auth;

import android.net.Uri;
import androidx.collection.ArrayMap;

public final class zzcr {
    private static final ArrayMap zza;

    static {
        zzcr.zza = new ArrayMap();
    }

    public static Uri zza(String s) {
        synchronized(zzcr.class) {
            ArrayMap arrayMap0 = zzcr.zza;
            Uri uri0 = (Uri)arrayMap0.get("com.google.android.gms.auth_account");
            if(uri0 == null) {
                Uri uri1 = Uri.parse(("content://com.google.android.gms.phenotype/" + Uri.encode("com.google.android.gms.auth_account")));
                arrayMap0.put("com.google.android.gms.auth_account", uri1);
                return uri1;
            }
            return uri0;
        }
    }
}

