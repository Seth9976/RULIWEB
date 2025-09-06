package com.google.android.gms.common;

import java.util.Arrays;

final class zzk extends zzj {
    private final byte[] zza;

    zzk(byte[] arr_b) {
        super(Arrays.copyOfRange(arr_b, 0, 25));
        this.zza = arr_b;
    }

    @Override  // com.google.android.gms.common.zzj
    final byte[] zzf() {
        return this.zza;
    }
}

