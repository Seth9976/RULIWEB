package com.google.android.gms.internal.measurement;

final class zziy extends zzis {
    private final zzja zza;

    zziy(zzja zzja0, int v) {
        super(zzja0.size(), v);
        this.zza = zzja0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzis
    protected final Object zza(int v) {
        return this.zza.get(v);
    }
}

