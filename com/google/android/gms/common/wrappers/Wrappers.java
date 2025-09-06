package com.google.android.gms.common.wrappers;

import android.content.Context;

public class Wrappers {
    private static final Wrappers zza;
    private PackageManagerWrapper zzb;

    static {
        Wrappers.zza = new Wrappers();
    }

    public Wrappers() {
        this.zzb = null;
    }

    public static PackageManagerWrapper packageManager(Context context0) {
        return Wrappers.zza.zza(context0);
    }

    public final PackageManagerWrapper zza(Context context0) {
        synchronized(this) {
            if(this.zzb == null) {
                if(context0.getApplicationContext() != null) {
                    context0 = context0.getApplicationContext();
                }
                this.zzb = new PackageManagerWrapper(context0);
            }
            return this.zzb;
        }
    }
}

