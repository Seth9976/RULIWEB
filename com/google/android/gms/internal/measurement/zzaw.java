package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;

public abstract class zzaw {
    final List zza;

    protected zzaw() {
        this.zza = new ArrayList();
    }

    public abstract zzap zza(String arg1, zzg arg2, List arg3);

    final zzap zzb(String s) {
        zzbl zzbl0 = zzh.zze(s);
        if(!this.zza.contains(zzbl0)) {
            throw new IllegalArgumentException("Command not supported");
        }
        throw new UnsupportedOperationException("Command not implemented: " + s);
    }
}

