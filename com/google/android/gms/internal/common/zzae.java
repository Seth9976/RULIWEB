package com.google.android.gms.internal.common;

final class zzae extends zzz {
    private final zzag zza;

    zzae(zzag zzag0, int v) {
        super(zzag0.size(), v);
        this.zza = zzag0;
    }

    @Override  // com.google.android.gms.internal.common.zzz
    protected final Object zza(int v) {
        return this.zza.get(v);
    }
}

