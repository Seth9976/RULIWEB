package com.google.android.gms.internal.ads_identifier;

import android.os.Parcel;

public final class zzc {
    private static final ClassLoader zza;

    static {
        zzc.zza = zzc.class.getClassLoader();
    }

    public static void zza(Parcel parcel0, boolean z) {
        parcel0.writeInt(1);
    }

    public static boolean zzb(Parcel parcel0) {
        return parcel0.readInt() != 0;
    }
}

