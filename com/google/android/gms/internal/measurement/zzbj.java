package com.google.android.gms.internal.measurement;

import java.util.List;

public final class zzbj extends zzaw {
    @Override  // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String s, zzg zzg0, List list0) {
        if(s == null || s.isEmpty() || !zzg0.zzh(s)) {
            throw new IllegalArgumentException(String.format("Command not found: %s", s));
        }
        zzap zzap0 = zzg0.zzd(s);
        if(!(zzap0 instanceof zzai)) {
            throw new IllegalArgumentException(String.format("Function %s is not defined", s));
        }
        return ((zzai)zzap0).zza(zzg0, list0);
    }
}

