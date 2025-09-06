package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;

public final class zzu extends zzai {
    private final Callable zza;

    public zzu(String s, Callable callable0) {
        super("internal.appMetadata");
        this.zza = callable0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzg0, List list0) {
        try {
            return zzi.zzb(this.zza.call());
        }
        catch(Exception unused_ex) {
            return zzap.zzf;
        }
    }
}

